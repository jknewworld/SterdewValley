package com.P.view.GameView;

import com.P.Main;
import com.P.controller.game.GameController;
import com.P.model.Basics.Player;
import com.P.model.Pair;
import com.P.model.game.GameModel;
import com.P.model.item.ItemDescriptionId;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameMenuInputAdapter extends InputAdapter {
    private final GameModel game;
    private final GameController gameController;
    private final Set<Integer> keysHeld = new HashSet<>();

    public GameMenuInputAdapter(GameModel game, GameController gameController) {
        this.game = game;
        this.gameController = gameController;
    }

    @Override
    public boolean keyDown(int keycode) {
        keysHeld.add(keycode);

        if (keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9) {
            int selectedSlot = keycode - Input.Keys.NUM_1;
            game.getPlayer().setSelectedSlot(selectedSlot);
            return true;
        }

        if (keycode == Input.Keys.ESCAPE) {
           // gameController.goToMain();
            return true;
        }

        if (keycode == Input.Keys.N) {
          //  gameController.advanceToNextDay();
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysHeld.remove(keycode);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        int current = game.getPlayer().getSelectedSlot();
        int size = game.getPlayer().getMaxInventorySize();
        int next = (current + (amountY > 0 ? 1 : -1) + size) % size;
        game.getPlayer().setSelectedSlot(next);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            performAction(screenX, screenY);
            return true;
        }
        return false;
    }

    public void update(float delta) {
        Player player = game.getPlayer();
        float vx = 0, vy = 0;
        int dir = 0;

        if (keysHeld.contains(Input.Keys.W)) {
            vy += 1;
            dir = 3;
        }
        if (keysHeld.contains(Input.Keys.S)) {
            vy -= 1;
            dir = 1;
        }
        if (keysHeld.contains(Input.Keys.A)) {
            vx -= 1;
            dir = 4;
        }
        if (keysHeld.contains(Input.Keys.D)) {
            vx += 1;
            dir = 2;
        }

        float length = (float) Math.sqrt(vx * vx + vy * vy);
        if (length > 0) {
            vx /= length;
            vy /= length;
            player.setMovingDirection(dir);
        } else {
            player.setMovingDirection(0);
        }

        float speed = player.getSpeed();
        player.setVelocity(vx * speed, vy * speed);
        player.update(delta, game.getTiles());
    }


    private void performAction(int screenX, int screenY) {
        OrthographicCamera camera = game.getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = game.getPlayer().getPlayerPosition();

        int tileX = (int) (worldCoordinates.x / Main.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / Main.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }

        ItemDescriptionId selectedItem = game.getPlayer().getSelectedItem();
        if (selectedItem != null) {
           // gameController.useItem(selectedItem, new Point(tileX, tileY), game);
        }
    }
}

