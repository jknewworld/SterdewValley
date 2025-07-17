package com.P.model.game;

import com.P.Main;
import com.P.model.Basics.App;
import com.P.model.Basics.Game;
import com.P.model.Basics.Player;
import com.P.model.Basics.User;
import com.P.model.GameAssetManager;
import com.P.model.Maps.Farm;
import com.P.model.Pair;
import com.P.model.Resualt;
import com.P.model.item.GrowingCrop;
import com.P.model.item.TileDescriptionId;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;
import java.util.*;

import static com.badlogic.gdx.Gdx.app;

public class GameModel {
    private TileDescriptionId[][] tiles;
    private Map<Point, GrowingCrop> growingCrops;
    private Player player;
    private final int mapWidth;
    private final int mapHeight;
    private static OrthographicCamera camera; // Add camera field
    private ArrayList<SpriteMine> rainDrops = new ArrayList<>();
    private ArrayList<Sprite> snow = new ArrayList<>();
    private ArrayList<SpriteMine> storms = new ArrayList<>();
    float delta = 0f;


    public GameModel(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        //  tiles = new TileDescriptionId[mapWidth][mapHeight];
        tiles = new TileDescriptionId[50][75];
        initializeTiles();

        growingCrops = new HashMap<>();
        player = new Player(App.getLoggedInUser());// Check It
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(player.getPlayerPosition().first, player.getPlayerPosition().second, 0);
    }

    private void initializeTiles() {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Farm farm = game.getCurrentPlayer().getCurrentFarm(game);
        tiles = farm.getTiles();
        System.out.println(user.getNickname());

    }


    public void update(float deltaTime) {
        Pair<Float, Float> playerPos = player.getPlayerPosition();
        float playerX = playerPos.first * Main.TILE_SIZE;
        float playerY = playerPos.second * Main.TILE_SIZE;

        this.delta += deltaTime;

        float camX = camera.position.x;
        float camY = camera.position.y;

        float viewHalfWidth = camera.viewportWidth / 2;
        float viewHalfHeight = camera.viewportHeight / 2;

        float border = Main.TILE_SIZE * 5; // 2-tile margin from edge

        // Horizontal movement
        if (playerX < camX - viewHalfWidth + border) {
            camX = playerX + viewHalfWidth - border;
        } else if (playerX > camX + viewHalfWidth - border) {
            camX = playerX - viewHalfWidth + border;
        }

        // Vertical movement
        if (playerY < camY - viewHalfHeight + border) {
            camY = playerY + viewHalfHeight - border;
        } else if (playerY > camY + viewHalfHeight - border) {
            camY = playerY - viewHalfHeight + border;
        }

        camX = Math.max(viewHalfWidth, Math.min(camX, mapWidth * Main.TILE_SIZE - viewHalfWidth));
        camY = Math.max(viewHalfHeight, Math.min(camY, mapHeight * Main.TILE_SIZE - viewHalfHeight));


        camera.position.set(camX, camY, 0);
        camera.update();
    }


    public void advanceToNextDay() {
        growingCrops.forEach((point, growingCrop) -> {
            growingCrop.advance();
        });
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }

    public TileDescriptionId[][] getTiles() {
        return tiles;
    }

    public void addGrowingCrop(GrowingCrop growingCrop, Point position) {
        growingCrops.put(position, growingCrop);
    }

    public void water(Point position) {
        GrowingCrop growingCrop = growingCrops.get(position);
        if (growingCrop != null) {
            growingCrop.water();
        }
    }

    public void harvest(Point position) {
        GrowingCrop crop = growingCrops.get(position);
        if (crop != null && crop.isReady()) {
            growingCrops.remove(position);
            player.addItem(crop.getResult(), 1);
        }
    }

    public TileDescriptionId getTile(Point point) {
        return tiles[point.x][point.y];
    }

    public Map<Point, GrowingCrop> getGrowingCrops() {
        return growingCrops;
    }

    public void setTiles(TileDescriptionId[][] tiles) {
        this.tiles = tiles;
    }

    public void setGrowingCrops(Map<Point, GrowingCrop> growingCrops) {
        this.growingCrops = growingCrops;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void handleRainDrops() {
        Random rand = new Random();
        if (rand.nextInt(25) == 4) {
            Sprite sprite = new Sprite(GameAssetManager.RAIN[0][0]);
            sprite.setPosition(
                camera.position.x - camera.viewportWidth / 2f + rand.nextFloat(camera.viewportWidth + 1),
                camera.position.y + camera.viewportHeight / 2f
            );
            sprite.setScale(1.875f);
            rainDrops.add(new SpriteMine(sprite));
        }
        for (SpriteMine rainDrop : rainDrops.stream().toList()) {
            if (rand.nextInt(300) == 4) {
                rainDrop.setMoving(false);
                Pair<Float, Float> pair = new Pair<>(rainDrop.getSprite().getX(), rainDrop.getSprite().getY());
                for (int i = 0; i < 10; i++) {
                    int finalI = i;
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            rainDrop.setSprite(new Sprite(GameAssetManager.RAIN[0][finalI + 1]));
                            rainDrop.getSprite().setPosition(pair.first, pair.second);
                            rainDrop.getSprite().setScale(1.875f);
                        }
                    }, 0.3f * i + 0.1f);
                }
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        rainDrops.remove(rainDrop);
                    }
                }, 3.1f);
            } else if (rainDrop.getSprite().getY() + rainDrop.getSprite().getHeight() < camera.position.y - camera.viewportHeight / 2f - 20) {
                rainDrops.remove(rainDrop);
            } else if (rainDrop.isMoving()) {
                rainDrop.getSprite().setY(rainDrop.getSprite().getY() - 1000 * Gdx.graphics.getDeltaTime());
            }

            rainDrop.getSprite().draw(Main.getBatch());
        }
    }

    public void handleSnowDrops() {
        Random rand = new Random();
        if (snow.isEmpty()) {
            for (int i = 0; i < camera.viewportWidth / GameAssetManager.SNOW.getWidth(); i++) {
                for (int j = 0; j <= 6 * camera.viewportHeight / GameAssetManager.SNOW.getHeight(); j++) {
                    Sprite sprite = new Sprite(GameAssetManager.SNOW);
                    sprite.setScale(1);
                    sprite.setPosition(
                        i * sprite.getWidth(),
                        j * sprite.getHeight() + rand.nextInt((int) sprite.getHeight())
                    );
                    snow.add(sprite);
                }
            }
        }
        float offset = camera.position.x - camera.viewportWidth / 2f;
        for (Sprite snowDrop : snow.stream().toList()) {
            snowDrop.setY(snowDrop.getY() - 400 * Gdx.graphics.getDeltaTime());
            if (snowDrop.getY() + snowDrop.getHeight() < camera.position.y - camera.viewportHeight / 2f - 20) {

                snowDrop.setY(camera.position.y + camera.viewportHeight / 2f);

            }
            snowDrop.setX(snowDrop.getX() + offset);
            snowDrop.draw(Main.getBatch());
            snowDrop.setX(snowDrop.getX() - offset);

        }
    }

}
