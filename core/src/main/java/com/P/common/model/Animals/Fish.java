package com.P.common.model.Animals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Fish {
    private Vector2 position;
    private Texture texture;
    private FishGame type;

    private float elapsedTime = 0f;
    private float moveCooldown = 0.5f; // نیم ثانیه
    private int lastMove = 0;
    private Texture fishTexture;

    private Random random = new Random();

    public Fish(Texture texture, float x, float y, FishGame type, Texture fishTexture) {
        this.texture = texture;
        this.position = new Vector2(x, y);
        this.type = type;
        this.fishTexture = fishTexture;
    }

    public void update(float delta, Rectangle waterBounds) {
        elapsedTime += delta;
        if (elapsedTime >= moveCooldown) {
            elapsedTime = 0f;

            int move = decideMovement();
            float newY = position.y + move;

            // محدود به محدوده آب
            if (newY >= waterBounds.y && newY + texture.getHeight() <= waterBounds.y + waterBounds.height) {
                position.y = newY;
                lastMove = move;
            }
        }
    }

    private int decideMovement() {
        int[] basic = { -4, 0, 4 };
        switch (type) {
            case MIXED:
                return basic[random.nextInt(3)];
            case SMOOTH:
                return random.nextFloat() < 0.6f ? lastMove : basic[random.nextInt(3)];
            case SINKER:
                return lastMove == 0 ? 10 : basic[random.nextInt(3)];
            case FLOATER:
                return lastMove == 0 ? -10 : basic[random.nextInt(3)];
            case DART:
                int[] dartMoves = { -9, 0, 9 };
                return dartMoves[random.nextInt(3)];
            default:
                return 0;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public FishGame getType() {
        return type;
    }

    public void setType(FishGame type) {
        this.type = type;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public float getMoveCooldown() {
        return moveCooldown;
    }

    public void setMoveCooldown(float moveCooldown) {
        this.moveCooldown = moveCooldown;
    }

    public int getLastMove() {
        return lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Texture getFishTexture() {
        return fishTexture;
    }

    public void setFishTexture(Texture fishTexture) {
        this.fishTexture = fishTexture;
    }
}
