package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.Client.view.PreGameView.NewGameView;
import com.P.Client.view.PreGameView.PreGameView;
import com.P.Server.model.Lobby;
import com.P.common.Message;
import com.P.common.model.Resualt;

import java.util.HashMap;

import static com.P.Client.app.ClientApp.TIMEOUT_MILLIS;

public class BasicsController {
    private PreGameView view;
    public void setView(PreGameView view) {
        this.view = view;
    }

    public Resualt getLobbyList() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "getLobbyList");

        Message message = new Message(body, Message.MessageType.command);

        return sendCommand(message).getResualt();

    }

    public Resualt getLobbyInformation() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "getLobbyInformation");

        Message message = new Message(body, Message.MessageType.command);

        return sendCommand(message).getResualt();

    }

    public Resualt findLobbyWithName() {
        HashMap<String, Object> body = new HashMap<>();
        String lobbyName = view.getSetLobby().getText();
        body.put("controller", "BasicsController");
        body.put("request", "findLobbyWithName");
        body.put("lobbyName", lobbyName);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }


    public Resualt findLobbyWithID() {
        HashMap<String, Object> body = new HashMap<>();
        int lobbyID = Integer.parseInt(view.getSearchLobby().getText());
        body.put("controller", "BasicsController");
        body.put("request", "findLobbyWithID");
        body.put("lobbyID", lobbyID);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public Resualt isLobbyPrivate() {
        HashMap<String, Object> body = new HashMap<>();
        String lobbyName = view.getSetLobby().getText();
        body.put("controller", "BasicsController");
        body.put("request", "isLobbyPrivate");
        body.put("lobbyName", lobbyName);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public Resualt isCorrectPassword() {
        HashMap<String, Object> body = new HashMap<>();
        String password = view.getPassword();
        body.put("controller", "BasicsController");
        body.put("request", "isCorrectPassword");
        body.put("password", password);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public static Message sendCommand(Message message) {
        return ClientApp.getServerConnection().sendAndWaitForResponse(message, TIMEOUT_MILLIS);
    }
}
