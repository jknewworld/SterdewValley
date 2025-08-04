package com.P.Client.view.GameView;

import com.P.Client.model.GameAssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Effect {
    private final float x;
    private float y;
    private float time = 0f;
    private final float duration = 2f;
    private boolean finished = false;

    public Effect(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float delta) {
        time += delta;
        if (time >= duration) {
            finished = true;
        }
        y += 30 * delta;
    }

    public void draw(SpriteBatch batch) {
        float alpha = 1f - (time / duration);
        Color old = batch.getColor();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(GameAssetManager.SECRETHEART, x, y, 32, 32);
        batch.setColor(old);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getDuration() {
        return duration;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
