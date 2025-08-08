package com.P.Server.controller;

import com.P.Server.model.Lobby;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.User;
import com.P.common.model.Resualt;

import java.util.ArrayList;
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
        } else if (request.equals("isLobbyPrivate")) {
            resualt = isLobbyPrivate(command);
        } else if (request.equals("isCorrectPassword")) {
            resualt = isCorrectPassword(command);
        } else if (request.equals("getLobbyInformation")) {
            resualt = getLobbyInformation();
        } else if (request.equals("back")) {
            resualt = back(command);
        } else if (request.equals("add")) {
            resualt = add(command);
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("is", resualt.isAccept() ? "true" : "false");
        body.put("ok", resualt.getAnswer());
        return new Message(body, Message.MessageType.response);
    }

    private static Resualt getLobbyList() {
        StringBuilder list = new StringBuilder();
        for (Lobby lobby : App.getLobbies())
            if (lobby.isVisible())
                list.append(lobby.getName()).append(" ").append(lobby.getPeopleCounter()).append('\n');

        return new Resualt(true, list.toString());
    }

    private static Resualt getLobbyInformation() {
        StringBuilder list = new StringBuilder();
        Lobby lobby = App.getCurrentLobby();
        list.append("Nickname: ").append(lobby.getName()).append('\n')
            .append("Player Counter: ").append(lobby.getPeopleCounter()).append('\n')
            .append("is Private: ").append(lobby.isPrivate()).append('\n')
            .append("is Visible: ").append(lobby.isVisible()).append('\n')
            .append("Password: ").append(lobby.getPassword()).append('\n')
            .append("Admin: ").append(lobby.getAdmin().getUsername()).append('\n')
            .append("ID: ").append(lobby.getID()).append('\n')
            .append("Players: ").append('\n');

        System.out.println(list.toString());

        for (User user : lobby.getPlayers()) {
            if (user != null)
                list.append(user.getNickname()).append('\n');
        }

        System.out.println(list.toString());

        return new Resualt(true, list.toString());
    }

    private static Resualt findLobbyWithName(Message command) {
        String lobbyName = command.getFromBody("lobbyName");
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyName.equals(lobby.getName())) {
                App.setCurrentLobby(lobby);
                return new Resualt(true, "");
            }
        }

        return new Resualt(false, "We don't have a lobby with that name");
    }

    private static Resualt findLobbyWithID(Message command) {
        int lobbyID = command.getIntFromBody("lobbyID");
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyID == lobby.getID()) {
                return new Resualt(true, "Your lobby name is " + lobby.getName());
            }
        }

        return new Resualt(false, "We don't have a lobby with that name");
    }

    private static Lobby findLobbyWithName(String lobbyName) {
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyName.equals(lobby.getName())) {
                return lobby;
            }
        }

        return null;
    }

    private static Resualt isLobbyPrivate(Message command) {
        Lobby lobby = findLobbyWithName((String) command.getFromBody("lobbyName"));
        assert lobby != null;
        if (lobby.isPrivate()) {
            return new Resualt(false, "Lobby Password");
        } else {
            return new Resualt(true, "Welcome to lobby");
        }

    }

    private static Resualt isCorrectPassword(Message command) {
        String password = command.getFromBody("password");
        Lobby lobby = App.getCurrentLobby();
        System.out.println(lobby.getName() + " " + lobby.getPassword());
        System.out.println(password);
        if (password.equals(lobby.getPassword())) {
            return new Resualt(true, "Correct Password");
        } else {
            App.setCurrentLobby(null);
            return new Resualt(false, "Incorrect Password");
        }

    }

    private static Resualt back(Message command) {
        String username = command.getFromBody("username");
        User user = UserRepo.findUserByUsername(username);
        App.getCurrentLobby().getPlayers().remove(user);
        App.setCurrentLobby(null);
        return new Resualt(true, "");
    }

    private static Resualt add(Message command) {
        if (App.getCurrentLobby().getPeopleCounter() == 4) {
            return new Resualt(false, "You can't add more than 4 players");
        }
        String username = command.getFromBody("username");
        User user = UserRepo.findUserByUsername(username);
        App.getCurrentLobby().getPlayers().add(user);
        App.getCurrentLobby().setPeopleCounter(App.getCurrentLobby().getPeopleCounter() + 1);
        return new Resualt(true, "");

    }
}
