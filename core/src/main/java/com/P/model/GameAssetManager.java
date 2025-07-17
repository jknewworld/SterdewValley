package com.P.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

    // Storm
    public static final TextureRegion[][] STORM = TextureRegion.split(new Texture("game/tiles/storm.png"), 192, 384);

    // Faint
    // Elliott
    private final String character0_idle0 = "game/character/Elliott/faint/1.png";
    private final String character0_idle1 = "game/character/Elliott/faint/2.png";
    private final String character0_idle2 = "game/character/Elliott/faint/3.png";
    private final Texture character0_idle0_tex = new Texture(character0_idle0);
    private final Texture character0_idle1_tex = new Texture(character0_idle1);
    private final Texture character0_idle2_tex = new Texture(character0_idle2);
    private final Animation<TextureRegion> elliott_faint = new Animation<>(
        0.4f,
        new TextureRegion(character0_idle0_tex),
        new TextureRegion(character0_idle1_tex),
        new TextureRegion(character0_idle2_tex)
    );


    // Haley
    private final String character1_idle0 = "game/character/Haley/faint/1.png";
    private final String character1_idle1 = "game/character/Haley/faint/2.png";
    private final String character1_idle2 = "game/character/Haley/faint/3.png";
    private final Texture character1_idle0_tex = new Texture(character1_idle0);
    private final Texture character1_idle1_tex = new Texture(character1_idle1);
    private final Texture character1_idle2_tex = new Texture(character1_idle2);
    private final Animation<TextureRegion> haley_faint = new Animation<>(
        0.4f,
        new TextureRegion(character1_idle0_tex),
        new TextureRegion(character1_idle1_tex),
        new TextureRegion(character1_idle2_tex)
    );


    // Leah
    private final String character2_idle0 = "game/character/Leah/faint/1.png";
    private final String character2_idle1 = "game/character/Leah/faint/2.png";
    private final String character2_idle2 = "game/character/Leah/faint/3.png";
    private final Texture character2_idle0_tex = new Texture(character2_idle0);
    private final Texture character2_idle1_tex = new Texture(character2_idle1);
    private final Texture character2_idle2_tex = new Texture(character2_idle2);
    private final Animation<TextureRegion> leah_faint = new Animation<>(
        0.4f,
        new TextureRegion(character2_idle0_tex),
        new TextureRegion(character2_idle1_tex),
        new TextureRegion(character2_idle2_tex)
    );

    // Robin
    private final String character3_idle0 = "game/character/Robin/faint/1.png";
    private final String character3_idle1 = "game/character/Robin/faint/2.png";
    private final String character3_idle2 = "game/character/Robin/faint/3.png";
    private final Texture character3_idle0_tex = new Texture(character3_idle0);
    private final Texture character3_idle1_tex = new Texture(character3_idle1);
    private final Texture character3_idle2_tex = new Texture(character3_idle2);
    private final Animation<TextureRegion> robin_faint = new Animation<>(
        0.4f,
        new TextureRegion(character3_idle0_tex),
        new TextureRegion(character3_idle1_tex),
        new TextureRegion(character3_idle2_tex)
    );

    // Sebastian
    private final String character4_idle0 = "game/character/Sebastian/faint/1.png";
    private final String character4_idle1 = "game/character/Sebastian/faint/2.png";
    private final String character4_idle2 = "game/character/Sebastian/faint/3.png";
    private final Texture character4_idle0_tex = new Texture(character4_idle0);
    private final Texture character4_idle1_tex = new Texture(character4_idle1);
    private final Texture character4_idle2_tex = new Texture(character4_idle2);
    private final Animation<TextureRegion> sebastian_faint = new Animation<>(
        0.4f,
        new TextureRegion(character4_idle0_tex),
        new TextureRegion(character4_idle1_tex),
        new TextureRegion(character4_idle2_tex)
    );

    // Shane
    private final String character5_idle0 = "game/character/Shane/faint/1.png";
    private final String character5_idle1 = "game/character/Shane/faint/2.png";
    private final String character5_idle2 = "game/character/Shane/faint/3.png";
    private final Texture character5_idle0_tex = new Texture(character5_idle0);
    private final Texture character5_idle1_tex = new Texture(character5_idle1);
    private final Texture character5_idle2_tex = new Texture(character5_idle2);
    private final Animation<TextureRegion> shane_faint = new Animation<>(
        0.4f,
        new TextureRegion(character5_idle0_tex),
        new TextureRegion(character5_idle1_tex),
        new TextureRegion(character5_idle2_tex)
    );


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


    public Texture getCharacter3_idle0_tex() {
        return character3_idle0_tex;
    }

    public String getCharacter0_idle0() {
        return character0_idle0;
    }

    public String getCharacter0_idle1() {
        return character0_idle1;
    }

    public String getCharacter0_idle2() {
        return character0_idle2;
    }

    public Texture getCharacter0_idle0_tex() {
        return character0_idle0_tex;
    }

    public Texture getCharacter0_idle1_tex() {
        return character0_idle1_tex;
    }

    public Texture getCharacter0_idle2_tex() {
        return character0_idle2_tex;
    }

    public Animation<TextureRegion> getElliott_faint() {
        return elliott_faint;
    }

    public String getCharacter1_idle0() {
        return character1_idle0;
    }

    public String getCharacter1_idle1() {
        return character1_idle1;
    }

    public String getCharacter1_idle2() {
        return character1_idle2;
    }

    public Texture getCharacter1_idle0_tex() {
        return character1_idle0_tex;
    }

    public Texture getCharacter1_idle1_tex() {
        return character1_idle1_tex;
    }

    public Texture getCharacter1_idle2_tex() {
        return character1_idle2_tex;
    }

    public Animation<TextureRegion> getHaley_faint() {
        return haley_faint;
    }

    public String getCharacter2_idle0() {
        return character2_idle0;
    }

    public String getCharacter2_idle1() {
        return character2_idle1;
    }

    public String getCharacter2_idle2() {
        return character2_idle2;
    }

    public Texture getCharacter2_idle0_tex() {
        return character2_idle0_tex;
    }

    public Texture getCharacter2_idle1_tex() {
        return character2_idle1_tex;
    }

    public Texture getCharacter2_idle2_tex() {
        return character2_idle2_tex;
    }

    public Animation<TextureRegion> getLeah_faint() {
        return leah_faint;
    }

    public String getCharacter3_idle0() {
        return character3_idle0;
    }

    public String getCharacter3_idle1() {
        return character3_idle1;
    }

    public String getCharacter3_idle2() {
        return character3_idle2;
    }

    public Texture getCharacter3_idle1_tex() {
        return character3_idle1_tex;
    }

    public Texture getCharacter3_idle2_tex() {
        return character3_idle2_tex;
    }

    public Animation<TextureRegion> getRobin_faint() {
        return robin_faint;
    }

    public String getCharacter4_idle0() {
        return character4_idle0;
    }

    public String getCharacter4_idle1() {
        return character4_idle1;
    }

    public String getCharacter4_idle2() {
        return character4_idle2;
    }

    public Texture getCharacter4_idle0_tex() {
        return character4_idle0_tex;
    }

    public Texture getCharacter4_idle1_tex() {
        return character4_idle1_tex;
    }

    public Texture getCharacter4_idle2_tex() {
        return character4_idle2_tex;
    }

    public Animation<TextureRegion> getSebastian_faint() {
        return sebastian_faint;
    }

    public String getCharacter5_idle0() {
        return character5_idle0;
    }

    public String getCharacter5_idle1() {
        return character5_idle1;
    }

    public String getCharacter5_idle2() {
        return character5_idle2;
    }

    public Texture getCharacter5_idle0_tex() {
        return character5_idle0_tex;
    }

    public Texture getCharacter5_idle1_tex() {
        return character5_idle1_tex;
    }

    public Texture getCharacter5_idle2_tex() {
        return character5_idle2_tex;
    }

    public Animation<TextureRegion> getShane_faint() {
        return shane_faint;
    }
}
