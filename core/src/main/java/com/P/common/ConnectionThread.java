package com.P.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

abstract public class ConnectionThread extends Thread {
    protected final DataInputStream dataInputStream;
    protected final DataOutputStream dataOutputStream;
    protected final BlockingQueue<Message> receivedMessagesQueue;
    protected Socket socket;
    protected AtomicBoolean end;

    protected ConnectionThread(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.receivedMessagesQueue = new LinkedBlockingQueue<>();
        this.end = new AtomicBoolean(false);
    }

    abstract protected boolean handleMessage(Message message);

    public synchronized void sendMessage(Message message) {
        String JSONString = JSONUtils.toJson(message);

        System.out.println("writing...");
        try {
            dataOutputStream.writeUTF(JSONString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message sendAndWaitForResponse(Message message, int timeoutMilli) {
        System.out.println("sending message...");
        sendMessage(message);
        try {
            System.out.println("response received");
            return receivedMessagesQueue.poll(timeoutMilli, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.err.println("Request Timed out.");
            System.out.println("request timed out!");
            return null;
        }
    }

    public void run() {
        while (!end.get()) {
            try {
                String receivedStr = dataInputStream.readUTF();
                System.out.println("message received!");
                Message message = JSONUtils.fromJson(receivedStr);
                boolean handled = handleMessage(message);
                if (!handled) try {
                    System.out.println("received message queue updated");
                    receivedMessagesQueue.put(message);
                } catch (InterruptedException e) {}
            } catch (Exception e) {
                break;
            }
        }

        end();
    }

    public void end() {
        end.set(true);
        try {
            socket.close();
        } catch (IOException e) {}
    }
}
