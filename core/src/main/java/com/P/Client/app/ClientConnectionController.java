package com.P.Client.app;

import com.P.common.Message;

public class ClientConnectionController {
    public static Message handleCommand(Message command) {
        return null;
    }

    public static void handleUpdate(Message message) {
        if(message.getFromBody("Lets Play!") != null) {
            //TODO : set screen
            return;
        }
    }
}
