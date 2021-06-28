package model;

import java.awt.*;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Dimension translate(int speed) {
        switch (this) {
            case UP:
                return new Dimension(0, -(speed));
            case DOWN:
                return new Dimension(0, speed);
            case LEFT:
                return new Dimension(-(speed), 0);
            case RIGHT:
                return new Dimension(speed, 0);
            default:
                throw new IllegalStateException("Impossible");
        }
    }
}
