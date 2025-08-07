package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.common.Message;
import com.P.common.model.Resualt;

import java.util.HashMap;

import static com.P.Client.app.ClientApp.TIMEOUT_MILLIS;

public class BasicsController {
    public Resualt getLobbyList() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "getLobbyList");

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public static Message sendCommand(Message message) {
        return ClientApp.getServerConnection().sendAndWaitForResponse(message, TIMEOUT_MILLIS);
    }
}
