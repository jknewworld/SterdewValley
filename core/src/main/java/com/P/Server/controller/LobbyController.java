package com.P.Server.controller;

import com.P.Server.model.Lobby;
import com.P.common.Message;
import com.P.common.model.Basics.App;

import java.util.Random;


public class LobbyController {

    public void createLobby(Message command){
        String name= command.getFromBody("name");
        String password= command.getFromBody("password");
        boolean isVisible = command.getFromBody("isVisible");
        boolean isPrivate = true;

        if(password.equals("0"))
            isPrivate = false;

        Random random = new Random();
        int ID = 10000000 + random.nextInt(90000000);

        Lobby lobby = new Lobby(name, isPrivate, password, App.getLoggedInUser(), ID);
    }

}
