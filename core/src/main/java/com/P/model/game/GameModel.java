package com.P.model.game;

import com.P.Main;
import com.P.model.Basics.App;
import com.P.model.Basics.Player;
import com.P.model.Pair;
import com.P.model.item.GrowingCrop;
import com.P.model.item.TileDescriptionId;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameModel {
    private TileDescriptionId[][] tiles;
    private Map<Point, GrowingCrop> growingCrops;
    private Player player;
    private final int mapWidth;
    private final int mapHeight;
    private OrthographicCamera camera; // Add camera field

    public GameModel(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        tiles = new TileDescriptionId[mapWidth][mapHeight];
      //  initializeTiles();
        growingCrops = new HashMap<>();
        player = new Player(App.getLoggedInUser());// Check It
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(player.getPlayerPosition().first, player.getPlayerPosition().second, 0);
    }

//    private void initializeTiles() {
//        for (int i = 0; i < mapWidth; i++) {
//            for (int j = 0; j < mapHeight; j++) {
//                if (j < 2) {
//                    tiles[i][j] = TileDescriptionId.WATER;
//                } else {
//                    tiles[i][j] = TileDescriptionId.GRASS;
//                }
//            }
//        }
//    }

    public void update(float deltaTime) {
        Pair<Float, Float> playerPos = player.getPlayerPosition();
        float playerX = playerPos.first * Main.TILE_SIZE;
        float playerY = playerPos.second * Main.TILE_SIZE;

        float camX = camera.position.x;
        float camY = camera.position.y;

        float viewHalfWidth = camera.viewportWidth / 2;
        float viewHalfHeight = camera.viewportHeight / 2;

        float border = Main.TILE_SIZE * 2; // 2-tile margin from edge

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

    public OrthographicCamera getCamera() {
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
}
