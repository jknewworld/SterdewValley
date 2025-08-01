package com.P.common.model.game;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.Client.model.GameAssetManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Clock {
    private Sprite mainClock = new Sprite(GameAssetManager.CLOCK_MAIN);
    private Sprite arrow = new Sprite(GameAssetManager.CLOCK_ARROW);
    private Sprite seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[1]);
    private Sprite weather = new Sprite(GameAssetManager.ClOCK_MANNERS[6]);
    private final Game game = App.getLoggedInUser().getCurrentGame();


    public Sprite updateBatch(SpriteBatch batch) {
        OrthographicCamera camera = GameModel.getCamera();
        float width = 366;
        float height = 300;
        mainClock.draw(batch);
        mainClock.setSize(width, height);
        mainClock.setPosition(camera.position.x + camera.viewportWidth/2f - mainClock.getWidth(),
            camera.position.y + camera.viewportHeight/2f - mainClock.getHeight());
        arrow.draw(batch);
        arrow.setSize(width * 0.1f, height * 0.28f);
        arrow.setRotation(180 - 180f / 60 / 13 * ((game.getDate().toLocalTime().getHour()) - 9)*60 + game.getDate().toLocalTime().getMinute());
        arrow.setPosition(mainClock.getX() + width * 0.3082f - arrow.getWidth()/2 * (float) Math.cos(arrow.getRotation()/180 * Math.PI),
            mainClock.getY() + height / 2 + arrow.getHeight()/2 - arrow.getWidth()/2 * (float) Math.sin(arrow.getRotation()/180 * Math.PI));
        GameAssetManager.MAIN_FONT.draw(batch, game.getDate().toLocalDate().getDayOfWeek() + " " +game.getDate().toLocalDate().getDayOfMonth() ,
            mainClock.getX() + width*0.43f, mainClock.getY() + height * 0.90f);
        GameAssetManager.MAIN_FONT.draw(batch, game.getDate().toLocalTime().getHour()%12 + ":" + game.getDate().toLocalTime().getMinute() + " " + (game.getDate().toLocalTime().getHour() > 11 ? "p.m." : "a.m."),
            mainClock.getX() + width*0.50f, mainClock.getY() + height * 0.50f);
        weather.draw(batch);
        weather.setSize(width * 0.180f, height * 0.200f);
        weather.setPosition(mainClock.getX() + 0.405f * width, mainClock.getY() + 0.55f * height);
        seasonSprite.draw(batch);
        seasonSprite.setSize(weather.getWidth(), weather.getHeight());
        seasonSprite.setPosition(weather.getX() + 0.33f * width, weather.getY());
       // int golds = game.getCurrentPlayer().getAccount().getGolds();
//        for (int i = 0; golds > 0; golds /= 10, i++) {
//            GameAsset.MAIN_FONT.draw(batch, "" + golds % 10,
//                mainClock.getX() + width * (0.826f - 0.082f*i), mainClock.getY() + height * 0.17f);
//        }
        return mainClock;
    }

    public void setWeatherSprite() {
//        if (App.getInstance().getCurrentGame().getCurrentPlayer().getIsWedding()) {
//            this.weather = new Sprite(GameAsset.ClOCK_MANNERS[8]);
//            return;
//        }
        String weather2 = game.getWeatherToday().string();
        switch (weather2) {
            case "Rain" -> this.weather = new Sprite(GameAssetManager.ClOCK_MANNERS[6]);
            case "Snow" -> this.weather = new Sprite(GameAssetManager.ClOCK_MANNERS[9]);
            case "Storm" -> this.weather = new Sprite(GameAssetManager.ClOCK_MANNERS[11]);
            case "Sunny" -> this.weather = new Sprite(GameAssetManager.ClOCK_MANNERS[7]);
        }
    }

    public void setSeasonSprite() {
//        if (App.getInstance().getCurrentGame().getCurrentPlayer().getIsWedding()) {
//            this.seasonSprite = new Sprite(GameAsset.ClOCK_MANNERS[3]);
//            return;
//        }
        String season = game.getSeason().toString();
        switch (season) {
            case "Spring" -> this.seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[0]);
            case "Summer" -> this.seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[1]);
            case "Autumn" -> this.seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[2]);
            case "Winter" -> this.seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[4]);
        }
    }
}
