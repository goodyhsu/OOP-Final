package model;

import java.awt.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Dimension translate() {
        switch (this) {
            case UP:
                return new Dimension(0, -10);
            case DOWN:
                return new Dimension(0, 10);
            case LEFT:
                return new Dimension(-10, 0);
            case RIGHT:
                return new Dimension(10, 0);
            default:
                throw new IllegalStateException("Impossible");
        }
    }
}
