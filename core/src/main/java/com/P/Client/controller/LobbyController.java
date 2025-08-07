package com.P.Client.controller;

import com.P.Client.view.LobbyView;

public class LobbyController {
    private LobbyView view;

    public void setView(LobbyView view) {
        this.view = view;
    }

    public void createLobby() {
        String name= view.getNameField().getText();
        String password= view.getPasswordField().getText();
        boolean isVisible = view.getIsVisible().getText().equals("true");
        boolean isPrivate = true;

        if(password.equals("0"))
            isPrivate = false;


    }
}
