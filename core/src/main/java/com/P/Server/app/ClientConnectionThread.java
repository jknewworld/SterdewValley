package com.P.Server.app;

import com.P.common.ConnectionThread;
import com.P.common.Message;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends ConnectionThread {
    public ClientConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.MessageType.command) {
            System.out.println("handling command, in ClientConnectionThread");
            sendMessage(ServerConnectionController.handleCommand(message));
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        ServerApp.addClientConnection(this);
        super.run();
        ServerApp.removeClientConnection(this);
    }
}
