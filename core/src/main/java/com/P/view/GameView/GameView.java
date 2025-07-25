package com.P.view.GameView;

import com.P.Main;
import com.P.model.Animals.Fish;
import com.P.model.Animals.FishType;
import com.P.model.Basics.App;
import com.P.model.Basics.Game;
import com.P.model.Basics.Player;
import com.P.model.GameAssetManager;
import com.P.model.Pair;
import com.P.model.enums.Avatar;
import com.P.model.enums.Season;
import com.P.model.game.GameModel;
import com.P.model.game.VillageModel;
import com.P.model.item.CarrotStages;
import com.P.model.item.GrowingCrop;
import com.P.model.item.ItemDescriptionId;
import com.P.model.item.TileDescriptionId;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static javax.swing.JColorChooser.showDialog;

public class GameView {
    private GameModel game;
    private VillageModel village;
    private SpriteBatch batch;
    private TextureRegion[][] tileTextures;
    private Map<String, TextureRegion> textures;
    private BitmapFont smallFont;
    private GlyphLayout layout = new GlyphLayout();

    // Charecters
    private TextureAtlas playerAtlas;
    private final ArrayList<Animation<TextureRegion>> playerAnimations = new ArrayList<>();
    private TextureAtlas haleyAtlas;
    private final ArrayList<Animation<TextureRegion>> haleyAnimations = new ArrayList<>();
    private TextureAtlas leahAtlas;
    private final ArrayList<Animation<TextureRegion>> leahAnimations = new ArrayList<>();
    private TextureAtlas ElliottAtlas;
    private final ArrayList<Animation<TextureRegion>> elliottAnimations = new ArrayList<>();
    private TextureAtlas sebastianAtlas;
    private final ArrayList<Animation<TextureRegion>> sebastianAnimations = new ArrayList<>();
    private TextureAtlas shaneAtlas;
    private final ArrayList<Animation<TextureRegion>> shaneAnimations = new ArrayList<>();
    private TextureAtlas robinAtlas;
    private final ArrayList<Animation<TextureRegion>> robinAnimations = new ArrayList<>();

    private float stateTime = 0f;
    private int moveDirection = 0;
    private Texture pixel; // Add this
    private float faintStateTime = 0f;
    private boolean isVillage = false;
    private boolean isGreenHouseOkey = false;
    private Stage stage;

    // Fishinf
    private ArrayList<Fish> fishList;
    private Rectangle waterBounds;
    Texture fishMixedTexture = new Texture(Gdx.files.internal("game/animals/fish/mixed.png"));
    Texture fishSmoothTexture = new Texture(Gdx.files.internal("game/animals/fish/smooth.png"));
    Texture fishSinkerTexture = new Texture(Gdx.files.internal("game/animals/fish/sinker.png"));
    Texture fishFloaterTexture = new Texture(Gdx.files.internal("game/animals/fish/floater.png"));
    Texture fishDartTexture = new Texture(Gdx.files.internal("game/animals/fish/dart.png"));


    private Texture scarecrowTexture;
    private Texture scarecrowInfoTexture;
    private boolean isScarecrowInfoVisible = false;

    private final int scarecrowTileX = 2;
    private final int scarecrowTileY = 22;


    private void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/stardew-valley.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        smallFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public GameView(GameModel game) {
        this.game = game;
        batch = new SpriteBatch();
        loadTextures();
        loadFont();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public GameView(VillageModel village) {
        this.village = village;
        batch = new SpriteBatch();
        loadTextures();
        loadFont();
        isVillage = true;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }


    private void loadTextures() {
        textures = new HashMap<>();

        for (TileDescriptionId id : TileDescriptionId.values()) {
            String path = id.getIconPath();
            textures.put(id.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
        }
        for (ItemDescriptionId id : ItemDescriptionId.values()) {
            String path = id.getIconPath();
            textures.put(id.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
        }
        for (CarrotStages cs : CarrotStages.values()) {
            String path = cs.getIconPath();
            textures.put(cs.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
        }

        // Charecters
        playerAtlas = new TextureAtlas(Gdx.files.internal("game/character/sprites_player.atlas"));
        haleyAtlas = new TextureAtlas(Gdx.files.internal("game/character/Haley/sprites_Haley.atlas"));
        leahAtlas = new TextureAtlas(Gdx.files.internal("game/character/Leah/sprites_Leah.atlas"));
        ElliottAtlas = new TextureAtlas(Gdx.files.internal("game/character/Elliott/sprites_Elliott.atlas"));
        sebastianAtlas = new TextureAtlas(Gdx.files.internal("game/character/Sebastian/sprites_Sebastian.atlas"));
        shaneAtlas = new TextureAtlas(Gdx.files.internal("game/character/Shane/sprites_Shane.atlas"));
        robinAtlas = new TextureAtlas(Gdx.files.internal("game/character/Robin/sprites_Robin.atlas"));


        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(haleyAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(haleyAtlas.findRegion(region));
                }
            }
            haleyAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(playerAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(playerAtlas.findRegion(region));
                }
            }
            playerAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(leahAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(leahAtlas.findRegion(region));
                }
            }
            leahAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(ElliottAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(ElliottAtlas.findRegion(region));
                }
            }
            elliottAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(sebastianAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(sebastianAtlas.findRegion(region));
                }
            }
            sebastianAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(shaneAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(shaneAtlas.findRegion(region));
                }
            }
            shaneAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(robinAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(robinAtlas.findRegion(region));
                }
            }
            robinAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        // HOUSE
        textures.put("SPRING_HOUSE", new TextureRegion(new Texture(Gdx.files.internal("game\\house\\house\\1.png"))));
        textures.put("SUMMER_HOUSE", new TextureRegion(new Texture(Gdx.files.internal("game\\house\\house\\2.png"))));
        textures.put("FALL_HOUSE", new TextureRegion(new Texture(Gdx.files.internal("game\\house\\house\\3.png"))));
        textures.put("WINTER_HOUSE", new TextureRegion(new Texture(Gdx.files.internal("game\\house\\house\\4.png"))));

        // TREE1
        textures.put("TREE1_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/tree1.png"))));
        textures.put("TREE1_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/tree1.png"))));
        textures.put("TREE1_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/tree1.png"))));
        textures.put("TREE1_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/tree1.png"))));

        // TREE2
        textures.put("TREE2_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/tree2.png"))));
        textures.put("TREE2_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/tree2.png"))));
        textures.put("TREE2_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/tree2.png"))));
        textures.put("TREE2_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/tree2.png"))));

        // GRASS
        textures.put("GRASS_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/grass.png"))));
        textures.put("GRASS_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/grass.png"))));
        textures.put("GRASS_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/grass.png"))));
        textures.put("GRASS_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/grass.png"))));

        // WOOD
        textures.put("WOOD_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/wood.png"))));
        textures.put("WOOD_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/wood.png"))));
        textures.put("WOOD_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/wood.png"))));
        textures.put("WOOD_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/wood.png"))));

        // STONE
        textures.put("STONE_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/stone.png"))));
        textures.put("STONE_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/stone.png"))));
        textures.put("STONE_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/stone.png"))));
        textures.put("STONE_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/stone.png"))));

        // VILLAGE
        textures.put("SMITH", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/blackSmith.png"))));
        textures.put("RANCH", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/marnieRanch.png"))));
        textures.put("SALOON", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/stardropSaloon.png"))));
        textures.put("CARPENTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/carpenterShop.png"))));
        textures.put("JOJA", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/jojaMart.png"))));
        textures.put("STORE", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/pierreGeneralStore.png"))));

        // GREENHOUSE
        textures.put("BROKEN_GREENHOUSE", new TextureRegion(new Texture(Gdx.files.internal("game/greenhouse/Broken_Greenhouse.png"))));
        textures.put("SCARECROW", new TextureRegion(new Texture(Gdx.files.internal("game/greenhouse/question.png"))));
        textures.put("SCARECROW_INFO", new TextureRegion(new Texture(Gdx.files.internal("game/greenhouse/command.png"))));

        // FISHING
        waterBounds = new Rectangle(21 * Main.TILE_SIZE,
            21 * Main.TILE_SIZE,
            8 * Main.TILE_SIZE,
            4 * Main.TILE_SIZE);

        initFishes();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();
    }

    Array<Fish> fishes = new Array<>();

    public void initFishes() {
        for (int i = 0; i < 10; i++) {
            float x = MathUtils.random(waterBounds.x, waterBounds.x + waterBounds.width - 32);
            float y = MathUtils.random(waterBounds.y, waterBounds.y + waterBounds.height - 32);

            FishType type = FishType.values()[i % 5];
            Texture texture;
            switch (type) {
                case MIXED:
                    texture = fishMixedTexture;
                    break;
                case SMOOTH:
                    texture = fishSmoothTexture;
                    break;
                case SINKER:
                    texture = fishSinkerTexture;
                    break;
                case FLOATER:
                    texture = fishFloaterTexture;
                    break;
                case DART:
                    texture = fishDartTexture;
                    break;
                default:
                    texture = fishMixedTexture;
            }

            fishes.add(new Fish(texture, x, y, type, texture));
        }
    }

    public void render() {

        batch.setProjectionMatrix(game.getCamera().combined);
        handleAltKey();
        batch.begin();

        if (!isVillage) {
            renderTiles();
            renderHouse();
            if (!isGreenHouseOkey) {
                renderBrokenGreenHouse();
            }
            renderScarecrow();
            renderScarecrowInfo();
            renderFish();
        }

        if (game.isFlashActive()) {
            batch.end();

            batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            batch.begin();

            batch.setColor(1f, 1f, 1f, game.getFlashAlpha());
            batch.draw(pixel, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.setColor(1f, 1f, 1f, 1f);

            game.setFlashAlpha(game.getFlashAlpha() - Gdx.graphics.getDeltaTime() * 2f);
            if (game.getFlashAlpha() <= 0f) {
                game.setFlashAlpha(0f);
                game.setFlashActive(false);
            }

            batch.end();

            // 🔁 برگرداندن پروجکشن اصلی دوربین برای ادامه رسم:
            batch.setProjectionMatrix(game.getCamera().combined);
            batch.begin();
        }

        if (isVillage) {
            renderVillegeTiles();
            smithRender();
            ranchRender();
            saloonRender();
            carpenterRender();
            jojaRender();
            storeRender();
        }

        renderPlayer();
        batch.end();

    }

    private void renderFish(){
        for (Fish fish : fishes) {
            float camX = game.getCamera().position.x;
            float camY = game.getCamera().position.y;
            float viewportWidth = game.getCamera().viewportWidth;
            float viewportHeight = game.getCamera().viewportHeight;

            float cameraLeft = camX - viewportWidth / 2;
            float cameraBottom = camY - viewportHeight / 2;

            float drawX = fish.getPosition().x  - cameraLeft;
            float drawY = fish.getPosition().y - cameraBottom;
            fish.update(Gdx.graphics.getDeltaTime(), waterBounds);
            batch.draw(fish.getFishTexture(), drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE);
        }
    }

    private void handleAltKey() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
            isScarecrowInfoVisible = !isScarecrowInfoVisible;
        }
    }




    private void renderScarecrow() {
        float camX = game.getCamera().position.x;
        float camY = game.getCamera().position.y;
        float viewportWidth = game.getCamera().viewportWidth;
        float viewportHeight = game.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = scarecrowTileX * Main.TILE_SIZE - cameraLeft;
        float drawY = scarecrowTileY * Main.TILE_SIZE - cameraBottom;
        TextureRegion houseTexture = textures.get("SCARECROW");

        batch.draw(houseTexture, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);
    }

    private void renderScarecrowInfo() {
        if (!isScarecrowInfoVisible) return;
        float camX = game.getCamera().position.x;
        float camY = game.getCamera().position.y;
        float viewportWidth = game.getCamera().viewportWidth;
        float viewportHeight = game.getCamera().viewportHeight;
        int x = 2; // جای دلخواه روی صفحه
        int y = 24;
        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = x * Main.TILE_SIZE - cameraLeft;
        float drawY = y * Main.TILE_SIZE - cameraBottom;

        TextureRegion houseTexture = textures.get("SCARECROW_INFO");
        batch.draw(houseTexture, drawX, drawY, 300, 200); // اندازه دلخواه
    }


    private void renderVillegeTiles() {
        TileDescriptionId[][] tiles = village.getTiles();

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        int tileSize = Main.TILE_SIZE;


        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        int startX = Math.max(0, (int) (cameraLeft / tileSize) - 300);
        int startY = Math.max(0, (int) (cameraBottom / tileSize) - 300);
        int endX = Math.min(tiles.length, (int) ((camX + viewportWidth / 2) / tileSize) + 300);
        int endY = Math.min(tiles[0].length, (int) ((camY + viewportHeight / 2) / tileSize) + 300);

        // Render base tiles
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                TileDescriptionId id = tiles[x][y];
                if (tiles[x][y] == TileDescriptionId.WATER) {
                    System.out.print(x + " " + y);
                }
                if (id != null) {
                    float drawX = x * tileSize - cameraLeft;
                    float drawY = y * tileSize - cameraBottom;

                    TextureRegion texture = textures.get(id.name());
                    if (texture != null) {
                        batch.draw(texture, drawX, drawY, tileSize, tileSize);
                    }


                }


            }
        }
    }

    private void renderHouse() {
        TextureRegion houseTexture = textures.get("HOUSE");
        Game g = App.loggedInUser.getCurrentGame();

        if (g.getSeason().equals(Season.SPRING)) {
            houseTexture = textures.get("SPRING_HOUSE");
        } else if (g.getSeason().equals(Season.SUMMER)) {
            houseTexture = textures.get("SUMMER_HOUSE");
        } else if (g.getSeason().equals(Season.AUTUMN)) {
            houseTexture = textures.get("FALL_HOUSE");
        } else if (g.getSeason().equals(Season.WINTER)) {
            houseTexture = textures.get("WINTER_HOUSE");
        }

        if (houseTexture != null) {
            int tileSize = Main.TILE_SIZE;

            int houseTileX = 3;
            int houseTileY = 3;

            float camX = game.getCamera().position.x;
            float camY = game.getCamera().position.y;
            float viewportWidth = game.getCamera().viewportWidth;
            float viewportHeight = game.getCamera().viewportHeight;

            float cameraLeft = camX - viewportWidth / 2;
            float cameraBottom = camY - viewportHeight / 2;

            float drawX = houseTileX * tileSize - cameraLeft;
            float drawY = houseTileY * tileSize - cameraBottom;

            batch.draw(houseTexture, drawX, drawY, tileSize * 5, tileSize * 7);
        }
    }

    // GREENHOUSE
    private void renderBrokenGreenHouse() {
        TextureRegion houseTexture = textures.get("BROKEN_GREENHOUSE");

        if (houseTexture != null) {
            int tileSize = Main.TILE_SIZE;

            int houseTileX = 3;
            int houseTileY = 22;

            float camX = game.getCamera().position.x;
            float camY = game.getCamera().position.y;
            float viewportWidth = game.getCamera().viewportWidth;
            float viewportHeight = game.getCamera().viewportHeight;

            float cameraLeft = camX - viewportWidth / 2;
            float cameraBottom = camY - viewportHeight / 2;

            float drawX = houseTileX * tileSize - cameraLeft;
            float drawY = houseTileY * tileSize - cameraBottom;

            batch.draw(houseTexture, drawX, drawY, tileSize * 4, tileSize * 4);
        }

    }

    // VILLAGE
    private void smithRender() {
        TextureRegion houseTexture = textures.get("SMITH");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 16;
        int houseTileY = 16;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 5, tileSize * 4);

    }

    private void ranchRender() {
        TextureRegion houseTexture = textures.get("RANCH");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 9;
        int houseTileY = 9;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 5, tileSize * 4);

    }

    private void saloonRender() {
        TextureRegion houseTexture = textures.get("SALOON");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 9;
        int houseTileY = 9;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 4, tileSize * 5);

    }

    private void carpenterRender() {
        TextureRegion houseTexture = textures.get("CARPENTER");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 25;
        int houseTileY = 25;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 6, tileSize * 7);

    }

    private void jojaRender() {
        TextureRegion houseTexture = textures.get("JOJA");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 35;
        int houseTileY = 35;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 6, tileSize * 3); // فرض شده خانه 2x2 کاشی است

    }

    private void storeRender() {
        TextureRegion houseTexture = textures.get("STORE");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 45;
        int houseTileY = 35;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 5, tileSize * 3); // فرض شده خانه 2x2 کاشی است

    }

    // TILES
    private void renderTiles() {
        TileDescriptionId[][] tiles = game.getTiles();

        float camX = game.getCamera().position.x;
        float camY = game.getCamera().position.y;
        float viewportWidth = game.getCamera().viewportWidth;
        float viewportHeight = game.getCamera().viewportHeight;

        int tileSize = Main.TILE_SIZE;


        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        int startX = Math.max(0, (int) (cameraLeft / tileSize) - 300);
        int startY = Math.max(0, (int) (cameraBottom / tileSize) - 300);
        int endX = Math.min(tiles.length, (int) ((camX + viewportWidth / 2) / tileSize) + 300);
        int endY = Math.min(tiles[0].length, (int) ((camY + viewportHeight / 2) / tileSize) + 300);

        // Render base tiles
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                TileDescriptionId id = tiles[x][y];
                if (id != null) {
                    float drawX = x * tileSize - cameraLeft;
                    float drawY = y * tileSize - cameraBottom;

                    GrowingCrop crop = game.getGrowingCrops().get(new Point(x, y));
                    if (crop != null && crop.watered()) {
                        batch.setColor(0.7f, 0.7f, 0.7f, 1f);
                    } else {
                        batch.setColor(1f, 1f, 1f, 1f);
                    }

                    TextureRegion texture = textures.get(id.name());
                    if (texture != null) {
                        batch.draw(texture, drawX, drawY, tileSize, tileSize);
                    }
                    if (id == TileDescriptionId.TREE1) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "TREE1_SPRING";
                            case SUMMER -> "TREE1_SUMMER";
                            case AUTUMN -> "TREE1_FALL";
                            case WINTER -> "TREE1_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.TREE2) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "TREE2_SPRING";
                            case SUMMER -> "TREE2_SUMMER";
                            case AUTUMN -> "TREE2_FALL";
                            case WINTER -> "TREE2_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.STONE) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "STONE_SPRING";
                            case SUMMER -> "STONE_SUMMER";
                            case AUTUMN -> "STONE_FALL";
                            case WINTER -> "STONE_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.WOOD) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "WOOD_SPRING";
                            case SUMMER -> "WOOD_SUMMER";
                            case AUTUMN -> "WOOD_FALL";
                            case WINTER -> "WOOD_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.GRASS) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "GRASS_SPRING";
                            case SUMMER -> "GRASS_SUMMER";
                            case AUTUMN -> "GRASS_FALL";
                            case WINTER -> "GRASS_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                }


            }
        }


        // Render crops on top
        for (Map.Entry<Point, GrowingCrop> entry : game.getGrowingCrops().entrySet()) {
            Point point = entry.getKey();
            GrowingCrop crop = entry.getValue();

            int x = point.x;
            int y = point.y;

            if (x >= startX && x < endX && y >= startY && y < endY) {
                float drawX = x * tileSize - cameraLeft;
                float drawY = y * tileSize - cameraBottom;

                int growth = crop.getGrowth();
                CarrotStages cs;

                if (growth < 2) cs = CarrotStages.CARROT_STAGE_1;
                else if (growth < 4) cs = CarrotStages.CARROT_STAGE_2;
                else if (growth < 6) cs = CarrotStages.CARROT_STAGE_3;
                else cs = CarrotStages.CARROT_STAGE_4;

                TextureRegion cropTexture = textures.get(cs.name());
                if (cropTexture != null) {
                    batch.setColor(1f, 1f, 1f, 1f);
                    batch.draw(cropTexture, drawX, drawY, tileSize, tileSize);
                }
            }
        }

        batch.setColor(1f, 1f, 1f, 1f);
    }

    // PLAYER
    private void renderPlayer() {
        Pair<Float, Float> pos;
        if (!isVillage)
            pos = game.getPlayer().getPlayerPosition();
        else
            pos = village.getPlayer().getPlayerPosition();


        if (!isVillage)
            moveDirection = game.getPlayer().getMovingDirection();
        else
            moveDirection = village.getPlayer().getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Player o = App.loggedInUser.getCurrentGame().getCurrentPlayer();

        boolean isFainting = (o.getEnergy() == 0);

        if (isFainting) {
            faintStateTime += Gdx.graphics.getDeltaTime();
        } else {
            faintStateTime = 0f; // ریست بشه اگر انرژی برگشته
        }


        Animation<TextureRegion> currentAnimation = null;


        if (o.getUser().getAvatar().equals(Avatar.HALEY)) {
            if (o.getEnergy() != 0)
                currentAnimation = haleyAnimations.get(moveDirection);
            else {
                currentAnimation = GameAssetManager.getGameAssetManager().getHaley_faint();
            }
        } else if (o.getUser().getAvatar().equals(Avatar.LEAH)) {
            if (o.getEnergy() != 0)
                currentAnimation = leahAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getLeah_faint();
        } else if (o.getUser().getAvatar().equals(Avatar.ELLIOTT)) {
            if (o.getEnergy() != 0)
                currentAnimation = elliottAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getElliott_faint();
        } else if (o.getUser().getAvatar().equals(Avatar.SEBASTIAN)) {
            if (o.getEnergy() != 0)
                currentAnimation = sebastianAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getSebastian_faint();
        } else if (o.getUser().getAvatar().equals(Avatar.ROBIN)) {
            if (o.getEnergy() != 0)
                currentAnimation = robinAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getRobin_faint();
        } else if (o.getUser().getAvatar().equals(Avatar.SHANE)) {
            if (o.getEnergy() != 0)
                currentAnimation = shaneAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getShane_faint();
        } else {
            currentAnimation = playerAnimations.get(moveDirection);
        }

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        if (isFainting) {
            currentFrame = currentAnimation.getKeyFrame(faintStateTime, false); // فقط یک بار اجرا بشه
        }

        if (o.getEnergy() == 0) {
            batch.draw(currentFrame, pos.first * Main.TILE_SIZE, pos.second * Main.TILE_SIZE, 100, 100);
        } else
            batch.draw(currentFrame, pos.first * Main.TILE_SIZE, pos.second * Main.TILE_SIZE, 60, 120);

        if (game != null)
            renderInventory();
    }

    // INVENTORY
    private void renderInventory() {
        Player player = game.getPlayer();
        Map<ItemDescriptionId, Pair<Integer, Integer>> inventoryForMap = player.getInventoryForMap();
        int selectedSlot = player.getSelectedSlot(); // Assuming you have this method

        int screenWidth = Gdx.graphics.getWidth();
        int slotSize = Main.TILE_SIZE / 2;
        int numSlots = player.getMaxInventorySize();
        int startX = (screenWidth - numSlots * slotSize) / 2;
        int y = Main.TILE_SIZE / 2;

        for (int i = 0; i < numSlots; i++) {
            int x = startX + i * slotSize;

            batch.draw(textures.get(TileDescriptionId.SLOT.name()), x, y, slotSize, slotSize);

            String slotNum = String.valueOf(i + 1);
            smallFont.draw(batch, slotNum, x + 2, y + slotSize - 2);
        }

        // Highlight selected slot
        if (selectedSlot >= 0 && selectedSlot < numSlots) {
            int highlightX = startX + selectedSlot * slotSize;
            batch.draw(textures.get(TileDescriptionId.HIGHLIGHT.name()), highlightX, y, slotSize, slotSize);
        }

        for (Map.Entry<ItemDescriptionId, Pair<Integer, Integer>> entry : inventoryForMap.entrySet()) {
            ItemDescriptionId id = entry.getKey();
            int quantity = entry.getValue().first;
            int index = entry.getValue().second;

            if (index < 0 || index >= numSlots) continue;

            TextureRegion itemTex = textures.get(id.name());
            if (itemTex != null) {
                int x = startX + index * slotSize;
                batch.draw(itemTex, x, y, slotSize, slotSize);

                // Draw item quantity at bottom-right corner
                String count = String.valueOf(quantity);
                layout.setText(smallFont, count);
                smallFont.draw(batch, count, x + slotSize - layout.width - 2, y + layout.height + 2);
            }
        }
    }

    public Batch getBatch() {
        return batch;
    }


    public Texture getPixel() {
        return pixel;
    }

    public boolean isGreenHouseOkey() {
        return isGreenHouseOkey;
    }

    public void setGreenHouseOkey(boolean greenHouseOkey) {
        isGreenHouseOkey = greenHouseOkey;
    }
}
