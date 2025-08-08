package com.P.Client.app;

import com.P.Client.controller.TurnController;
import com.P.Client.model.GameAssetManager;
import com.P.Client.view.PreGameView.NewGameView;
import com.P.Main;
import com.P.common.Message;

public class ClientConnectionController {
    public static Message handleCommand(Message command) {
        return null;
    }

    public static void handleUpdate(Message message) {
        if(message.getFromBody("Lets Play!") != null) {
            ClientApp.setStartGame(true);
            //TODO : set screen

            return;
        }
    }
}
