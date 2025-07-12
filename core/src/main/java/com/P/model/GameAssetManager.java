package com.P.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    public static final Texture PIXEL;
   // public static final BitmapFont MAIN_FONT = new BitmapFont();
    public static final BitmapFont MAIN_FONT;

    static {
        // فونت سفارشی از فایل ttf
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/stardew-valley.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 29;
        parameter.color = Color.FIREBRICK;
        MAIN_FONT = generator.generateFont(parameter);
        generator.dispose();
    }

    private final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    static {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fill();
        PIXEL = new Texture(pixmap);
        pixmap.dispose();
    }

    // CLOCK
    public static final Texture CLOCK = new Texture("game/clock/clock.png");
    public static final Texture CLOCK_ALL = new Texture("game/clock/fullclock.png");
    public static final TextureRegion CLOCK_MAIN = new TextureRegion(CLOCK_ALL, 0, 0, 72, 59);
    public static final TextureRegion CLOCK_ARROW = new TextureRegion(CLOCK_ALL, 72, 0, 8, 18);
    public static final TextureRegion[] ClOCK_MANNERS = new TextureRegion[12];
    static {
        for (int i = 0; i < 12; i++) {
            ClOCK_MANNERS[i] = new TextureRegion(CLOCK_ALL, 80 + i % 4 * 13, i / 4 * 9, 13, 9);
        }
    }
    public static final Texture CLOCKWITHJOURNAL = new Texture("game/clock/journal.png");
    public static final Texture GOLDDISPLAY = new Texture("game/clock/golddisplay.png");
    public static final Texture WEDDINGDAYHEART = new Texture("game/clock/weddingclock.png");

    // Rain
    public static final TextureRegion[][] RAIN = TextureRegion.split(new Texture("game/tiles/rain.png"), 41, 130);

    // Snow
    public static final Texture SNOW = new Texture("game/tiles/snow.png");


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
