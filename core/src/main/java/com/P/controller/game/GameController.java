package com.P.controller.game;


import com.P.Main;
import com.P.controller.TurnController;
import com.P.model.game.GameModel;
import com.P.model.item.ItemDescriptionId;
import com.P.model.item.TileDescriptionId;
import com.P.view.GameView.GameMenu;

import java.awt.*;

// 6. Enhanced Game Controller
public class GameController {
    private boolean escapePressed = false;
    private final Main game;
    private final TurnController mainController;
    private GameMenu gameMenu;

    public GameController(Main game, TurnController mainController) {
        this.game = game;
        this.mainController = mainController;
    }

    public void init() {
        gameMenu = new GameMenu(this);
    }

    public void run() {
        game.setScreen(gameMenu);
    }

//    public void goToMain() {
//        gameMenu.dispose();
//        mainController.run();
//    }

    public void useItem(ItemDescriptionId selectedItem, Point point, GameModel game) {
        TileDescriptionId selectedTile = game.getTile(point);
        if (!selectedItem.getAllowedTiles().contains(selectedTile)) {
            return;
        }

        game.getPlayer().useSelectedItem();
        selectedItem.getFunction().invoke(game, point);
    }

    public void advanceToNextDay() {
        gameMenu.gameModel.advanceToNextDay();
        gameMenu.startSleepTransition();
    }
}
