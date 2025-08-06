package com.P.Server.controller;

import com.P.Client.view.LobbyView;
import com.P.Server.model.Lobby;
import com.P.common.model.Basics.App;

import java.util.Random;


public class LobbyController {
    private LobbyView view;

    public void setView(LobbyView view) {
        this.view = view;
    }

    public void creatLobby(){
        String name= view.getNameField().getText();
        String password= view.getPasswordField().getText();
        boolean isVisible = view.getIsVisible().getText().equals("true");
        boolean isPrivate = true;

        if(password.equals("0"))
            isPrivate = false;

        Random random = new Random();
        int ID = 10000000 + random.nextInt(90000000);

        Lobby lobby = new Lobby(name, isPrivate, password, App.getLoggedInUser(), ID);
    }

}
