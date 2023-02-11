package org.alan.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return moveX == movement.moveX && moveY == movement.moveY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveX, moveY);
    }
}
