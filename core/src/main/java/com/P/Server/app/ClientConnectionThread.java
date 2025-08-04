package com.P.Server.app;

import com.P.common.ConnectionThread;

import java.io.IOException;
import java.net.Socket;
import com.P.common.Message;

public class ClientConnectionThread extends ConnectionThread {
    public ClientConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    protected boolean handleMessage(Message message) {
        if (message.getType() != Message.MessageType.response) {
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
