package com.P.Client.view.PreGameView;

import com.P.Client.controller.BasicsController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ExactLobbyView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;
    public Label label;

    public TextButton startGame;

    private BasicsController controller;

    public ExactLobbyView(BasicsController controller, Skin skin) {
        this.backgroundTexture = new Texture(Gdx.files.internal("background/lobby.png"));
        this.backgroundImage = new Image(backgroundTexture);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/stardew-valley.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.VIOLET;
        style.downFontColor = Color.FOREST;
        this.startGame = new TextButton("^_^ LET'S START NEW GAME", style);

        this.label = new Label(" ", skin);

        this.table = new Table();
        this.controller = controller;
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);
        table.add(startGame).width(600).height(70);

        stage.addActor(table);

        String forLabel = controller.getLobbyInformation().getAnswer();
        label.setText(forLabel);
        label.setPosition(100,100);
        stage.addActor(label);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();

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
