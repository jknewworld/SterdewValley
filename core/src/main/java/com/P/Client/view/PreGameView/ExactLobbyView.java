package com.P.Client.view.PreGameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ExactLobbyView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;

    public ExactLobbyView() {
        this.backgroundTexture = new Texture(Gdx.files.internal("background/lobby.png"));
        this.backgroundImage = new Image(backgroundTexture);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

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
}
