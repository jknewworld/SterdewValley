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
        if (request.equals("getLobbyList")) {
            resualt = getLobbyList();
        } else if (request.equals("findLobbyWithName")) {
            resualt = findLobbyWithName(command);
        } else if (request.equals("findLobbyWithID")) {
            resualt = findLobbyWithID(command);
        } else if(request.equals("isLobbyPrivate")){
            resualt = isLobbyPrivate(command);
        }   else if(request.equals("isCorrectPassword")){
            resualt = isCorrectPassword(command);
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("is", resualt.isAccept() ? "true" : "false");
        body.put("ok", resualt.getAnswer());
        return new Message(body, Message.MessageType.response);
    }

    private static Resualt getLobbyList() {
        StringBuilder list = new StringBuilder();
        for (Lobby lobby : App.getLobbies())
            if (lobby.isVisible)
                list.append(lobby.name).append(" ").append(lobby.peopleCounter).append('\n');
        return new Resualt(true, list.toString());
    }

    private static Resualt findLobbyWithName(Message command) {
        String lobbyName = command.getFromBody("lobbyName");
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyName.equals(lobby.name)) {
                App.setCurrentLobby(lobby);
                return new Resualt(true, "");
            }
        }

        return new Resualt(false, "We don't have a lobby with that name");
    }

    private static Resualt findLobbyWithID(Message command) {
        int lobbyID = command.getFromBody("lobbyID");
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyID == lobby.ID) {
                return new Resualt(true, "Your lobby name is " + lobby.name);
            }
        }

        return new Resualt(false, "We don't have a lobby with that name");
    }

    private static Lobby findLobbyWithName(String lobbyName) {
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyName.equals(lobby.name)) {
                return lobby;
            }
        }

        return null;
    }

    private static Resualt isLobbyPrivate(Message command) {
        Lobby lobby = findLobbyWithName((String) command.getFromBody("lobbyName"));
        assert lobby != null;
        if (lobby.isPrivate) {
            return new Resualt(false, "Lobby Password");
        } else {
            return new Resualt(true, "Welcome to lobby");
        }

    }

    private static Resualt isCorrectPassword(Message command) {
        String password = command.getFromBody("password");
        Lobby lobby = App.getCurrentLobby();
        if(password.equals(lobby.password)) {
            return new Resualt(true, "Correct Password");
        } else {
            App.setCurrentLobby(null);
            return new Resualt(false, "Incorrect Password");
        }

    }
}
