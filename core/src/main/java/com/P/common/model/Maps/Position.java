package com.P.common.model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
public class Position {
    private int x;
    private int y;

    // Graphic
    private float fx;
    private float fy;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(float x, float y) {
        this.fx = x;
        this.fy = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getFx() {
        return fx;
    }

    public void setFx(float fx) {
        this.fx = fx;
    }

    public float getFy() {
        return fy;
    }

    public void setFy(float fy) {
        this.fy = fy;
    }

    public boolean isNextTo(Position position) {
        return (position.getX() == x - 1 || position.getX() == x || position.getX() == x + 1) &&
                (position.getY() == y - 1 || position.getY() == y || position.getY() == y + 1) &&
                !position.equals(this);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        Position position = (Position) o;
        return position.x == x && position.y == y;
    }
}
