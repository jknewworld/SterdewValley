package com.P.view;

import com.P.controller.StartController;
import com.P.controller.TurnController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PreGameView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;
    private TextButton newGame;
    private TextButton loadGame;
    private TextButton thisGame;
    private TextButton exitGame;

    private TurnController controller;

    public PreGameView(TurnController controller, Skin skin) {
        this.controller = controller;
        this.backgroundTexture = new Texture(Gdx.files.internal("background/gamemenu.png"));
        this.backgroundImage = new Image(backgroundTexture);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.SKY;
        style.overFontColor = Color.BLUE;
        this.newGame = new TextButton("New Game", style);
        this.loadGame = new TextButton("Load Game", style);
        this.thisGame = new TextButton("To See The Current Game", style);
        this.exitGame = new TextButton("Exit & Delete Game", style);

        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        table.setFillParent(true);
        table.center();
        table.row().pad(40, 0, 40, 0);
        table.add(newGame).width(350).height(45);

        table.row().pad(40, 0, 40, 0);
        table.add(loadGame).width(350).height(45);

        table.row().pad(40, 0, 40, 0);
        table.add(thisGame).width(350).height(45);

        table.row().pad(40, 0, 40, 0);
        table.add(exitGame).width(350).height(45);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
