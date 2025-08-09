package com.P.Client.view.GameView;

import com.P.Client.controller.CookingController;
import com.P.Client.controller.NPCController;
import com.P.Client.model.Command;
//import com.P.Client.model.HitEffect;
import com.P.Main;
import com.P.common.model.Maps.Position;
import com.P.common.model.NPC.NPC;
import com.badlogic.gdx.graphics.Color;

import com.P.common.model.Animals.Fish;
import com.P.common.model.Animals.FishGame;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.Client.model.GameAssetManager;
import com.P.Client.model.Pair;
import com.P.common.model.Objects.Tool;
import com.P.common.model.enums.Avatar;
import com.P.common.model.enums.Season;
import com.P.common.model.game.GameModel;
import com.P.common.model.game.VillageModel;
import com.P.common.model.item.CarrotStages;
import com.P.common.model.item.GrowingCrop;
import com.P.common.model.item.ItemDescriptionId;
import com.P.common.model.item.TileDescriptionId;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import static com.P.Client.controller.InventoryFunctionsController.Effects;
import static com.P.Client.controller.InventoryFunctionsController.useTool;
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

    // NPC
    private TextureAtlas AbigailAtlas;
    private final ArrayList<Animation<TextureRegion>> AbigailAnimation = new ArrayList<>();
    private TextureAtlas HarveyAtlas;
    private final ArrayList<Animation<TextureRegion>> HarveyAnimations = new ArrayList<>();
    private TextureAtlas LeaAtlas;
    private final ArrayList<Animation<TextureRegion>> LeaAnimations = new ArrayList<>();
    private TextureAtlas RobinAtlas;
    private final ArrayList<Animation<TextureRegion>> RobinAnimations = new ArrayList<>();
    private TextureAtlas SebastianAtlas;
    private final ArrayList<Animation<TextureRegion>> SebastianAnimations = new ArrayList<>();

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

    private Texture iconTexture;


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
        iconTexture = new Texture(Gdx.files.internal("game/NPC.png"));
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


        // NPCS
        AbigailAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Abigail.atlas"));
        HarveyAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Harvey.atlas"));
        LeaAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Lea.atlas"));
        RobinAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Robin.atlas"));
        SebastianAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Sebastian.atlas"));

        // NPCS
        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(AbigailAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(AbigailAtlas.findRegion(region));
                }
            }
            AbigailAnimation.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(HarveyAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(HarveyAtlas.findRegion(region));
                }
            }
            HarveyAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(LeaAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(LeaAtlas.findRegion(region));
                }
            }
            LeaAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(RobinAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(RobinAtlas.findRegion(region));
                }
            }
            RobinAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(SebastianAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(SebastianAtlas.findRegion(region));
                }
            }
            SebastianAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }


        // Charecters
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

        textures.put("Huge", new TextureRegion(new Texture(Gdx.files.internal("game/Large_Milk_RU.png"))));
        textures.put("F_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/f.png"))));
        textures.put("F_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/f.png"))));
        textures.put("F_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/f.png"))));
        textures.put("F_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/f.png"))));
        // FISHING
        waterBounds = new Rectangle(12 * Main.TILE_SIZE,
            12 * Main.TILE_SIZE,
            3 * Main.TILE_SIZE,
            3 * Main.TILE_SIZE);

        initFishes();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();
    }

    Array<Fish> fishes = new Array<>();

    public void initFishes() {
        for (int i = 0; i < 4; i++) {
            float x = MathUtils.random(waterBounds.x, waterBounds.x + waterBounds.width);
            float y = MathUtils.random(waterBounds.y, waterBounds.y + waterBounds.height);

            FishGame type = FishGame.values()[i % 5];
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

    public void render(float delta) {
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


        if (game != null && game.isFlashActive()) {
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

            renderAbigail();
            renderHarvey();
            renderLea();
            renderRobin();
            renderSebastian();
        }

        renderPlayer();
        Hugeproducts();
        batch.end();

    }

    private void renderFish() {
        for (Fish fish : fishes) {
            float camX = game.getCamera().position.x;
            float camY = game.getCamera().position.y;
            float viewportWidth = game.getCamera().viewportWidth;
            float viewportHeight = game.getCamera().viewportHeight;

            float cameraLeft = camX - viewportWidth / 2;
            float cameraBottom = camY - viewportHeight / 2;

            float drawX = fish.getPosition().y - cameraLeft;
            float drawY = fish.getPosition().x - cameraBottom;
            fish.update(Gdx.graphics.getDeltaTime(), waterBounds);
            batch.draw(fish.getFishTexture(), drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE);
        }
    }

    private void handleAltKey() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
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
                    if (id == TileDescriptionId.F) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "F_SPRING";
                            case SUMMER -> "F_SUMMER";
                            case AUTUMN -> "F_FALL";
                            case WINTER -> "F_WINTER";

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

    // NPCS
    // NPCS
    private Stage animalMenuStage = null;
    private boolean isAnimalMenuOpen = false;
    private float abigalIconTimer = 0;
    private boolean showAbigailIcon = false;
    private float abigailIconVisibleTime = 0;
    private String currentDialogAbigail = null;
    String dialog = " ";
    private BitmapFont font = new BitmapFont();

    private void renderAbigail() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(1);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = AbigailAnimation.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        abigalIconTimer += Gdx.graphics.getDeltaTime();

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }

        if (abigalIconTimer >= 10f) {

            if (abigalIconTimer >= 10f) { // هر دو دقیقه

                showAbigailIcon = true;
                abigailIconVisibleTime = 0;
                abigalIconTimer = 0;
                dialog = NPCController.getDialogue(npc);
            }

            if (showAbigailIcon) {
                abigailIconVisibleTime += Gdx.graphics.getDeltaTime();


                float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px

                float iconY = drawY + Main.TILE_SIZE * 2 + 10;

                batch.draw(iconTexture, iconX, iconY, 32, 32);


                if (abigailIconVisibleTime >= 5f) {

                    showAbigailIcon = false;


                }

                if (Gdx.input.justTouched()) {
                    Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                    VillageModel.getCamera().unproject(mousePos);

                    if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                        mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                        currentDialogAbigail = dialog;
                    }
                }
            }

            if (currentDialogAbigail != null) {
                float dialogWidth = 150;
                float dialogHeight = 50;
                float dialogX = drawX;
                float dialogY = drawY - dialogHeight - 5;

                font.setColor(Color.WHITE);
                font.draw(batch, dialog, dialogX + 10, dialogY + dialogHeight - 10);


                if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                    currentDialogAbigail = null;
                }
            }
        }
    }


    private void WindowNPC(NPC npc) {
        Stage animalStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(animalStage);

        Group menuGroup = new Group();

        com.badlogic.gdx.scenes.scene2d.ui.Window window = new com.badlogic.gdx.scenes.scene2d.ui.Window("NPC Menu", GameAssetManager.LABI_SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.setFillParent(true);
        table.top().pad(20).defaults().pad(10).left();

        final com.badlogic.gdx.scenes.scene2d.ui.Label responseLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("", GameAssetManager.LABI_SKIN);
        responseLabel.setAlignment(Align.center);
        responseLabel.setWrap(true);
        responseLabel.setColor(Color.DARK_GRAY);
        responseLabel.setText(npc.getName());
        table.add(responseLabel).colspan(2).width(600).left();

        table.row().pad(10, 0, 10, 0);
        final com.badlogic.gdx.scenes.scene2d.ui.Label friendshipLabel = new Label("", GameAssetManager.LABI_SKIN);
        friendshipLabel.setAlignment(Align.center);
        friendshipLabel.setWrap(true);
        friendshipLabel.setColor(Color.BROWN);
        friendshipLabel.setText(NPCController.ShowFriendship(npc).getAnswer());
        table.add(friendshipLabel).width(600);

        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                animalStage.clear();
                isAnimalMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        Group group = new Group() {
            @Override
            public void act(float delta) {
                super.act(delta);
                window.setPosition(
                    (animalStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
                    (animalStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
                );
                exitButton.setPosition(
                    window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
                    window.getY() + window.getHeight() - exitButton.getHeight() / 2f
                );
            }
        };

        group.addActor(window);
        group.addActor(exitButton);
        menuGroup.addActor(group);
        animalStage.addActor(menuGroup);

        this.animalMenuStage = animalStage;
        this.isAnimalMenuOpen = true;
    }

    private float harveyIconTimer = 0;
    private boolean showHarveyIcon = false;
    private float harveyIconVisibleTime = 0;
    private String currentDialogHarvey = null;
    String dialogHarvey = " ";

    private void renderHarvey() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(2);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = HarveyAnimations.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);


        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }


        harveyIconTimer += Gdx.graphics.getDeltaTime();

        if (harveyIconTimer >= 15f) {
            showHarveyIcon = true;
            harveyIconVisibleTime = 0;
            harveyIconTimer = 0;
            dialogHarvey = NPCController.getDialogue(npc);
        }


        if (showHarveyIcon) {
            harveyIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);

            if (harveyIconVisibleTime >= 5f) {
                showHarveyIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogHarvey = dialogHarvey;
                }
            }
        }

        if (currentDialogHarvey != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.WHITE);
            font.draw(batch, dialogHarvey, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogHarvey = null;
            }
        }
    }

    private float leaIconTimer = 0;
    private boolean showLeaIcon = false;
    private float leaIconVisibleTime = 0;
    private String currentDialogLea = null;
    String dialogLea = " ";

    private void renderLea() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(3);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = LeaAnimations.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }

        leaIconTimer += Gdx.graphics.getDeltaTime();

        if (leaIconTimer >= 20f) {
            showLeaIcon = true;
            leaIconVisibleTime = 0;
            leaIconTimer = 0;
            dialogLea = NPCController.getDialogue(npc);
        }


        if (showLeaIcon) {
            leaIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);

            if (leaIconVisibleTime >= 5f) {
                showLeaIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogLea = dialogLea;
                }
            }
        }

        if (currentDialogLea != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.WHITE);
            font.draw(batch, dialogLea, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogLea = null;
            }
        }
    }

    private float robinIconTimer = 0;
    private boolean showRobinIcon = false;
    private float robinIconVisibleTime = 0;
    private String currentDialogRobin = null;
    String dialogRobin = " ";

    private void renderRobin() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(4);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = RobinAnimations.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }


        robinIconTimer += Gdx.graphics.getDeltaTime();

        if (robinIconTimer >= 25f) {
            showRobinIcon = true;
            robinIconVisibleTime = 0;
            leaIconTimer = 0;
            dialogRobin = NPCController.getDialogue(npc);
        }


        if (showRobinIcon) {
            leaIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);

            if (robinIconVisibleTime >= 5f) {
                showRobinIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogRobin = dialogRobin;
                }
            }
        }

        if (currentDialogRobin != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.WHITE);
            font.draw(batch, dialogRobin, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogRobin = null;
            }
        }
    }

    private float sebastianIconTimer = 0;
    private boolean showSebastianIcon = false;
    private float sebastianIconVisibleTime = 0;
    private String currentDialogSebastian = null;
    String dialogSebastian = " ";

    private void renderSebastian() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(0);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = SebastianAnimations.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }


        sebastianIconTimer += Gdx.graphics.getDeltaTime();

        if (sebastianIconTimer >= 25f) {
            showSebastianIcon = true;
            sebastianIconVisibleTime = 0;
            sebastianIconTimer = 0;
            dialogSebastian = NPCController.getDialogue(npc);
        }


        if (showSebastianIcon) {
            sebastianIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);

            if (sebastianIconVisibleTime >= 5f) {
                showSebastianIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogSebastian = dialogSebastian;
                }
            }
        }

        if (currentDialogSebastian != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.WHITE);
            font.draw(batch, dialogSebastian, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogSebastian = null;
            }
        }
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


        Tool toll = o.getInHandTool();
        Texture tool = null;
        if (toll != null) {
            tool = GameAssetManager.getTexture(o.getInHandTool());
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

        if (tool != null) {
            float playerX = pos.first * Main.TILE_SIZE;
            float playerY = pos.second * Main.TILE_SIZE;

            float toolOffsetX = 50;
            float toolOffsetY = 20;

            batch.draw(tool, playerX + toolOffsetX, playerY + toolOffsetY, 44, 44);
        }
        if (game != null)
            renderInventory();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Bahu.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        int tileSize = Main.TILE_SIZE;

        Matrix4 originalProjection = batch.getProjectionMatrix().cpy();

        batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        font.setColor(Color.BLACK);
        font.draw(batch, "Energy: " + o.getEnergy() + " / 200", 10, Gdx.graphics.getHeight() - 10);

        batch.setProjectionMatrix(originalProjection);
    }


    private void Hugeproducts() {
        TextureRegion houseTexture = textures.get("Huge");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 2;
        int houseTileY = 3;

        float camX = GameModel.getCamera().position.x;
        float camY = GameModel.getCamera().position.y;
        float viewportWidth = GameModel.getCamera().viewportWidth;
        float viewportHeight = GameModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize, tileSize);

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
