package com.P.Server.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenerThread extends Thread {
    private final ServerSocket serverSocket;

    public ListenerThread(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    private void handleConnection(Socket socket) {
        if (socket == null) return;
        try {
            new ClientConnectionThread(socket).start();
        } catch (IOException e) {
            try {socket.close();} catch (IOException ex) {}
        }
    }

    @Override
    public void run() {
        while (!ServerApp.isEnded()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("connected to a new client!");
                handleConnection(socket);
            } catch (Exception e) {
                break;
            }
        }

        try {serverSocket.close();} catch (Exception ignored) {}
    }

    public void end() {
        try {
            serverSocket.close();
        } catch (IOException e) {}
    }
}
