package com.P.controller.game;


import java.awt.*;

// 6. Enhanced Game Controller
public class GameController {
//    private boolean escapePressed = false;
//    private final Main game;
//    private final MainController mainController;
//    private GameMenu gameMenu;
//
//    public GameController(StardewMini game, MainController mainController) {
//        this.game = game;
//        this.mainController = mainController;
//    }
//
//    public void init() {
//        gameMenu = new GameMenu(this);
//    }
//
//    public void run() {
//        game.setScreen(gameMenu);
//    }
//
//    public void goToMain() {
//        gameMenu.dispose();
//        mainController.run();
//    }
//
//    public void useItem(ItemDescriptionId selectedItem, Point point, GameModel game) {
//        TileDescriptionId selectedTile = game.getTile(point);
//        if (!selectedItem.getAllowedTiles().contains(selectedTile)) {
//            return;
//        }
//
//        game.getPlayer().useSelectedItem();
//        selectedItem.getFunction().invoke(game, point);
//    }
//
//    public void advanceToNextDay() {
//        gameMenu.gameModel.advanceToNextDay();
//        gameMenu.startSleepTransition();
//    }
}
