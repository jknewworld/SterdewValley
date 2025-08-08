package com.P.Client.app;

import com.P.Client.controller.TurnController;
import com.P.Client.model.GameAssetManager;
import com.P.Client.view.PreGameView.NewGameView;
import com.P.Main;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;

import java.util.ArrayList;

public class ClientConnectionController {
    public static Message handleCommand(Message command) {
        return null;
    }

    public static void handleUpdate(Message message) {
        if(message.getFromBody("Lets Play!") != null) {
            letsPlay();
            //TODO : set screen

            return;
        }
    }

    private static void letsPlay() {
        ClientApp.setStartGame(true);
        Player player = new Player(App.getLoggedInUser());
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        App.getLoggedInUser().setCurrentGame(new Game(players, player));
    }
}
