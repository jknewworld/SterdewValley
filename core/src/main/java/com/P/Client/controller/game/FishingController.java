package com.P.Client.controller.game;

import com.P.Main;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Player;
import com.P.common.model.Objects.Fish;
import com.P.common.model.Objects.FishingPole;
import com.P.common.model.Objects.Tool;
import com.P.common.model.enums.ToolType;
import com.P.common.model.game.GameModel;
import com.P.Client.view.GameView.MiniGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FishingController {
    private MiniGame miniGame;
    private float miniGameEndTimer = -1f;
    private boolean isMiniGameFinished = false;

    public void update() {
        handleMiniGameLifecycle();
        handleFishingInput();
        handleMiniGameEndTimer();
    }

    private void handleMiniGameLifecycle() {
        if (miniGame == null) return;
        if (Gdx.input.isKeyJustPressed(Input.Keys.I))
            miniGame.setShowLegendaryFish(true);

        miniGame.draw(Main.getBatch(), Gdx.graphics.getDeltaTime());

        if (miniGame.isGameOver()) {
            handleGameResult();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            cancelMiniGame();
        }
    }

    private void handleFishingInput() {
        if (miniGame == null && Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT)) {
            attemptToStartFishing();
        }
    }

    private void handleMiniGameEndTimer() {
        if (!isMiniGameFinished || miniGameEndTimer <= 0) return;

        miniGameEndTimer -= Gdx.graphics.getDeltaTime();
        if (miniGameEndTimer <= 0) {
            miniGame = null;
            isMiniGameFinished = false;
        }
    }

    private void handleGameResult() {
        Player player = App.loggedInUser.getCurrentGame().getCurrentPlayer();

        if (miniGame.isHasPlayerWon()) {
            player.increaseFishingSkill(1); // TODO: Adjust XP logic if needed

            if (!miniGame.isShowLegendaryFish()) {
                if (miniGame.isWasPerfectCatch()) {
                    renderImage("game/perfectfish.png");
                } else {
                    renderImage("game/fish.png");
                }

                isMiniGameFinished = true;
                miniGameEndTimer = 3f;
                player.setFishing(false);
            }
        } else {
            renderImage("game/lose.png");
            isMiniGameFinished = true;
            miniGameEndTimer = 3f;
        }
    }

    private void cancelMiniGame() {
        App.loggedInUser.getCurrentGame().getCurrentPlayer().setFishing(false);
        miniGame = null;
    }

    private void attemptToStartFishing() {
        Player player = App.loggedInUser.getCurrentGame().getCurrentPlayer();
        Tool fishingRod = getPlayerFishingRod(player);

        if (fishingRod != null) {
            startFishingWithTool(player, fishingRod);
        } else {
            // fallback for no fishing rod (maybe temporary or training mode)
            FishingPole fallbackPole = FishingPole.BAMBOO;
            Fish fish = fallbackPole.fishing(player);
            miniGame = new MiniGame(fish, fallbackPole, GameModel.getCamera());
        }
    }

    private Tool getPlayerFishingRod(Player player) {
        return player.getInventory().getTools().keySet().stream()
            .filter(tool -> tool.getToolType() == ToolType.FishingRod)
            .findFirst()
            .orElse(null);
    }

    private void startFishingWithTool(Player player, Tool tool) {
        FishingPole fishingPole = switch (tool.getToolLevel()) {
            case Bambou -> FishingPole.BAMBOO;
            case FiberGlass -> FishingPole.FIBER_GLASS;
            case Iridium -> FishingPole.IRIDIUM;
            default -> FishingPole.TRAINING;
        };

        player.setEnergy(player.getEnergy() - fishingPole.getEnergy(player));

        Fish fish = fishingPole.fishing(player);

        if (fishingPole.canFish(player, fish)) {
            miniGame = new MiniGame(fish, fishingPole, GameModel.getCamera());
        } else {
            renderImage("game/noenergy.png");
        }
    }

    private void renderImage(String texturePath) {
        float[] position = calculateDrawPosition(17, 24); // screen tile location
        TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal(texturePath)));
        Main.getBatch().draw(texture, position[0], position[1], 300, 200); // desired size
    }

    private float[] calculateDrawPosition(int tileX, int tileY) {
        float camX = GameModel.getCamera().position.x;
        float camY = GameModel.getCamera().position.y;
        float viewportWidth = GameModel.getCamera().viewportWidth;
        float viewportHeight = GameModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = tileX * Main.TILE_SIZE - cameraLeft;
        float drawY = tileY * Main.TILE_SIZE - cameraBottom;

        return new float[]{drawX, drawY};
    }
}
