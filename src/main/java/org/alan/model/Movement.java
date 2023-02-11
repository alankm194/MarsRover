package org.alan.model;

public class Movement {
    private final int moveX;

    private final int moveY;
    public Movement(int moveX, int moveY) {
        this.moveX = moveX;
        this.moveY = moveY;
    }
    public int getMoveY() {
        return moveY;
    }

    public int getMoveX() {
        return moveX;
    }
}
