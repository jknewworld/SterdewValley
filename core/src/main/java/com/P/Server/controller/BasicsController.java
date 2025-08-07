package com.P.Server.controller;

import com.P.Server.model.Lobby;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Resualt;

import java.util.HashMap;

public class BasicsController {
    public static Message handleCommand(Message command) {
        String request = command.getFromBody("request");
        Resualt resualt = null;
        if(request.equals("getLobbyList")) {
            resualt = getLobbyList();
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("is", resualt.isAccept() ? "true" : "false");
        body.put("ok", resualt.getAnswer());
        return new Message(body, Message.MessageType.response);
    }

    private static Resualt getLobbyList() {
        StringBuilder list = new StringBuilder();
        for(Lobby lobby : App.getLobbies())
            if(lobby.isVisible)
                list.append(lobby.name).append('\n');
        return new Resualt(true, list.toString());
    }
}
