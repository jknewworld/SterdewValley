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

    private static final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

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

    // Fishing Pole
    public static final Texture BAMBOOPOLE = new Texture("game/Fishing_Pole/Bamboo_Pole.png");
    public static final Texture FIBERGLASSROD = new Texture("game/Fishing_Pole/Fiberglass_Rod.png");
    public static final Texture IRIDIUMROD = new Texture("game/Fishing_Pole/Iridium_Rod.png");
    public static final Texture TRAININGROD = new Texture("game/Fishing_Pole/Training_Rod.png");

    // Fish
    public static final Texture ALBACORE = new Texture("game/Fish/Albacore.png");
    public static final Texture ANCHOVY = new Texture("game/Fish/Anchovy.png");
    public static final Texture ANGLER = new Texture("game/Fish/Angler.png");
    public static final Texture BLOBFISH = new Texture("game/Fish/Blobfish.png");
    public static final Texture BLUE_DISCUS = new Texture("game/Fish/Blue_Discus.png");
    public static final Texture BREAM = new Texture("game/Fish/Bream.png");
    public static final Texture BULLHEAD = new Texture("game/Fish/Bullhead.png");
    public static final Texture CARP = new Texture("game/Fish/Carp.png");
    public static final Texture CATFISH = new Texture("game/Fish/Catfish.png");
    public static final Texture CHUB = new Texture("game/Fish/Chub.png");
    public static final Texture CRIMSONFISH = new Texture("game/Fish/Crimsonfish.png");
    public static final Texture DORADO = new Texture("game/Fish/Dorado.png");
    public static final Texture EEL = new Texture("game/Fish/Eel.png");
    public static final Texture FLOUNDER = new Texture("game/Fish/Flounder.png");
    public static final Texture GHOSTFISH = new Texture("game/Fish/Ghostfish.png");
    public static final Texture GLACIERFISH = new Texture("game/Fish/Glacierfish.png");
    public static final Texture GLACIERFISH_JR_ = new Texture("game/Fish/Glacierfish_Jr..png");
    public static final Texture GOBY = new Texture("game/Fish/Goby.png");
    public static final Texture HALIBUT = new Texture("game/Fish/Halibut.png");
    public static final Texture HERRING = new Texture("game/Fish/Herring.png");
    public static final Texture ICE_PIP = new Texture("game/Fish/Ice_Pip.png");
    public static final Texture LARGEMOUTH_BASS = new Texture("game/Fish/Largemouth_Bass.png");
    public static final Texture LAVA_EEL = new Texture("game/Fish/Lava_Eel.png");
    public static final Texture LEGEND = new Texture("game/Fish/Legend.png");
    public static final Texture LEGEND_II = new Texture("game/Fish/Legend_II.png");
    public static final Texture LINGCOD = new Texture("game/Fish/Lingcod.png");
    public static final Texture LIONFISH = new Texture("game/Fish/Lionfish.png");
    public static final Texture MIDNIGHT_CARP = new Texture("game/Fish/Midnight_Carp.png");
    public static final Texture MIDNIGHT_SQUID = new Texture("game/Fish/Midnight_Squid.png");
    public static final Texture MS_ANGLER = new Texture("game/Fish/Ms._Angler.png");
    public static final Texture MUTANT_CARP = new Texture("game/Fish/Mutant_Carp.png");
    public static final Texture OCTOPUS = new Texture("game/Fish/Octopus.png");
    public static final Texture PERCH = new Texture("game/Fish/Perch.png");
    public static final Texture PIKE = new Texture("game/Fish/Pike.png");
    public static final Texture PUFFERFISH = new Texture("game/Fish/Pufferfish.png");
    public static final Texture RADIOACTIVE_CARP = new Texture("game/Fish/Radioactive_Carp.png");
    public static final Texture RAINBOW_TROUT = new Texture("game/Fish/Rainbow_Trout.png");
    public static final Texture RED_MULLET = new Texture("game/Fish/Red_Mullet.png");
    public static final Texture RED_SNAPPER = new Texture("game/Fish/Red_Snapper.png");
    public static final Texture SALMON = new Texture("game/Fish/Salmon.png");
    public static final Texture SANDFISH = new Texture("game/Fish/Sandfish.png");
    public static final Texture SARDINE = new Texture("game/Fish/Sardine.png");
    public static final Texture SCORPION_CARP = new Texture("game/Fish/Scorpion_Carp.png");
    public static final Texture SEA_CUCUMBER = new Texture("game/Fish/Sea_Cucumber.png");
    public static final Texture SHAD = new Texture("game/Fish/Shad.png");
    public static final Texture SLIMEJACK = new Texture("game/Fish/Slimejack.png");
    public static final Texture SMALLMOUTH_BASS = new Texture("game/Fish/Smallmouth_Bass.png");
    public static final Texture SMOKED_FISH = new Texture("game/Fish/Smoked_Fish.png");
    public static final Texture SON_OF_CRIMSONFISH = new Texture("game/Fish/Son_of_Crimsonfish.png");
    public static final Texture SPOOK_FISH = new Texture("game/Fish/Spook_Fish.png");
    public static final Texture SQUID = new Texture("game/Fish/Squid.png");
    public static final Texture STINGRAY = new Texture("game/Fish/Stingray.png");
    public static final Texture STONEFISH = new Texture("game/Fish/Stonefish.png");
    public static final Texture STURGEON = new Texture("game/Fish/Sturgeon.png");
    public static final Texture SUNFISH = new Texture("game/Fish/Sunfish.png");
    public static final Texture SUPER_CUCUMBER = new Texture("game/Fish/Super_Cucumber.png");
    public static final Texture TIGER_TROUT = new Texture("game/Fish/Tiger_Trout.png");
    public static final Texture TILAPIA = new Texture("game/Fish/Tilapia.png");
    public static final Texture TUNA = new Texture("game/Fish/Tuna.png");
    public static final Texture VOID_SALMON = new Texture("game/Fish/Void_Salmon.png");
    public static final Texture WALLEYE = new Texture("game/Fish/Walleye.png");
    public static final Texture WOODSKIP = new Texture("game/Fish/Woodskip.png");

    public static final Texture MINI_GAME_BACKGROUND = new Texture("game/background.png");
    public static final Texture MINI_GAME_BAR = new Texture("game/barMini.png");

    // Crown
    public static final Texture CROWN = new Texture("game/crown.png");


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

    public static Skin getSkin() {
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
