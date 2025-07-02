package com.P.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    private GameAssetManager() {

    }

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public static void setGameAssetManager(GameAssetManager gameAssetManager) {
        GameAssetManager.gameAssetManager = gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }
}
