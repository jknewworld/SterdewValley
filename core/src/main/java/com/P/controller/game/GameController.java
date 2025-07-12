package com.P.controller.game;


import com.P.Main;
import com.P.controller.TurnController;
import com.P.model.Basics.App;
import com.P.model.enums.Weather;
import com.P.model.game.GameModel;
import com.P.model.item.ItemDescriptionId;
import com.P.model.item.TileDescriptionId;
import com.P.view.GameView.GameMenu;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.awt.*;

// 6. Enhanced Game Controller
public class GameController {
    private boolean escapePressed = false;
    private final Main game;
    private final TurnController mainController;
    private static GameMenu gameMenu;
    private Table table;

    public GameController(Main game, TurnController mainController) {
        this.game = game;
        this.mainController = mainController;
        table = new Table();
        table.setFillParent(true);
        table.left();
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

    public static void advanceToNextDay() {
        gameMenu.gameModel.advanceToNextDay();
        gameMenu.startSleepTransition();
    }

    public void update() {
        Sprite clock = App.getLoggedInUser().getCurrentGame().getClock().updateBatch(Main.getBatch());
        table.setPosition(clock.getX() + clock.getWidth()*0.3f, clock.getY()-table.getHeight());

        switch (App.loggedInUser.getCurrentGame().getWeatherToday().string()) {
          //  case Weather.SNOW -> handleSnowDrops();
            case  "Rain" -> App.loggedInUser.getCurrentGame().getCurrentPlayer().getGameModel().handleRainDrops();
           // case Weather.STORM -> handleStorms();
        }
    }


}
