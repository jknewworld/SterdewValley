package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.Client.view.LobbyView;
import com.P.common.Message;

import java.util.HashMap;

import static com.P.Client.app.ClientApp.TIMEOUT_MILLIS;

public class LobbyController {
    private LobbyView view;

    public void setView(LobbyView view) {
        this.view = view;
    }

    public void createLobby() {
        String name= view.getNameField().getText();
        String password= view.getPasswordField().getText();
        boolean isVisible = view.getIsVisible().getText().equals("true");

        HashMap<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("password", password);
        body.put("isVisible", isVisible);

        Message message = new Message(body, Message.MessageType.command);
        Message response = sendCommand(message);
        //return response.getFromBody("result");
    }

    private Message sendCommand(Message message) {
        return ClientApp.getServerConnection().sendAndWaitForResponse(message, TIMEOUT_MILLIS);
    }
}
