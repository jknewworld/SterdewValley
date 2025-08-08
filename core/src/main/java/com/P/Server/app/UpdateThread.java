package com.P.Server.app;

import com.P.common.Message;

import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateThread extends Thread {
    private AtomicBoolean end;

    public UpdateThread() {
        this.end = new AtomicBoolean(false);
    }

    private void sendDiffToAll() {
        for(ClientConnectionThread connection : ServerApp.getConnections()) {
            //TODO : real condition
            if (true)
                connection.sendMessage(new Message(ServerApp.getDiff(), Message.MessageType.update));
        }
    }

    @Override
    public void run() {
        while(!end.get()) {
            //TODO : add time update to the message
            sendDiffToAll();
            ServerApp.getDiff().clear();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void end() {
        end.set(true);
    }
}
