package com.P.view.GameView;

import com.P.Main;
import com.P.controller.game.GameController;
import com.P.model.Basics.App;
import com.P.model.game.GameModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameMenu implements Screen {
    private GameView gameView;

    private GameView gameView1;
    private GameView gameView2;
    private GameView gameView3;
    private GameView gameView4;

    public GameModel gameModel;

    public GameModel gameModel1;
    public GameModel gameModel2;
    public GameModel gameModel3;
    public GameModel gameModel4;

    private GameMenuInputAdapter gameMenuInputAdapter;
    private GameMenuInputAdapter gameMenuInputAdapter1;
    private GameMenuInputAdapter gameMenuInputAdapter2;
    private GameMenuInputAdapter gameMenuInputAdapter3;
    private GameMenuInputAdapter gameMenuInputAdapter4;
    private GameController gameController;
    private boolean isSleeping = false;
    private float sleepAlpha = 0f;
    private float sleepTimer = 0f;
    private static final float SLEEP_DURATION = 2f; // seconds
    private static final float FADE_SPEED = 1.5f;   // speed of fading
    private boolean advancingDay = false;


    public GameMenu(GameController gameController) {
        this.gameController = gameController;
        initializeGame();
    }

    private void initializeGame() {
        gameModel = App.loggedInUser.getCurrentGame().getCurrentPlayer().getGameModel();
        System.out.println(App.loggedInUser.getCurrentGame().getPlayers().getFirst().getUser().getUsername());
        System.out.println(App.loggedInUser.getCurrentGame().getPlayers().getLast().getUser().getUsername());

        gameModel1 = App.loggedInUser.getCurrentGame().getPlayers().get(0).getGameModel();
        gameModel2 = App.loggedInUser.getCurrentGame().getPlayers().get(1).getGameModel();
        gameModel3 = App.loggedInUser.getCurrentGame().getPlayers().get(2).getGameModel();
        gameModel4 = App.loggedInUser.getCurrentGame().getPlayers().get(3).getGameModel();

        gameView1 = new GameView(gameModel1);
        gameView2 = new GameView(gameModel2);
        gameView3 = new GameView(gameModel3);
        gameView4 = new GameView(gameModel4);

        gameView = new GameView(gameModel);
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel, gameController);
        gameMenuInputAdapter1 = new GameMenuInputAdapter(gameModel1, gameController);
        gameMenuInputAdapter2 = new GameMenuInputAdapter(gameModel2, gameController);
        gameMenuInputAdapter3 = new GameMenuInputAdapter(gameModel3, gameController);
        gameMenuInputAdapter4 = new GameMenuInputAdapter(gameModel4, gameController);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Main.getBatch().begin();
        if(App.loggedInUser.getCurrentGame().getCurrentPlayer().equals(App.loggedInUser.getCurrentGame().getPlayers().get(0))){
            Gdx.input.setInputProcessor(gameMenuInputAdapter1);
            gameModel1.update(delta);
            gameView1.render();
            gameMenuInputAdapter1.update(delta);
        }
        if(App.loggedInUser.getCurrentGame().getCurrentPlayer().equals(App.loggedInUser.getCurrentGame().getPlayers().get(1))){
            Gdx.input.setInputProcessor(gameMenuInputAdapter2);
            gameModel2.update(delta);
            gameView2.render();
            gameMenuInputAdapter2.update(delta);
        }
        if(App.loggedInUser.getCurrentGame().getCurrentPlayer().equals(App.loggedInUser.getCurrentGame().getPlayers().get(2))){
            Gdx.input.setInputProcessor(gameMenuInputAdapter3);
            gameModel3.update(delta);
            gameView3.render();
            gameMenuInputAdapter3.update(delta);
        }
        if(App.loggedInUser.getCurrentGame().getCurrentPlayer().equals(App.loggedInUser.getCurrentGame().getPlayers().get(3))){
            Gdx.input.setInputProcessor(gameMenuInputAdapter4);
            gameModel4.update(delta);
            gameView4.render();
            gameMenuInputAdapter4.update(delta);
        }
//        gameModel.update(delta);
//        gameView.render();
//        gameMenuInputAdapter.update(delta);
        gameController.update();
        Main.getBatch().end();

        if (isSleeping) {
            sleepTimer += delta;
            if (!advancingDay && sleepAlpha < 1f) {
                sleepAlpha = Math.min(1f, sleepAlpha + delta * FADE_SPEED);
                if (sleepAlpha >= 1f) {
                    gameModel.advanceToNextDay();
                    advancingDay = true;
                }
            } else if (advancingDay && sleepAlpha > 0f) {
                sleepAlpha = Math.max(0f, sleepAlpha - delta * FADE_SPEED);
                if (sleepAlpha <= 0f) {
                    isSleeping = false;
                    advancingDay = false;
                    sleepTimer = 0f;
                }
            }

            // Render black overlay
            gameView.getBatch().begin();
            gameView.getBatch().setColor(0f, 0f, 0f, sleepAlpha);
            gameView.getBatch().draw(gameView.getPixel(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            gameView.getBatch().setColor(1f, 1f, 1f, 1f);
            gameView.getBatch().end();
        }
    }

    public void startSleepTransition() {
        isSleeping = true;
        sleepAlpha = 0f;
        sleepTimer = 0f;
        advancingDay = false;
    }


    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    // Other Screen methods
}
