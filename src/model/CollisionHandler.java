package model;

import java.awt.*;

public interface CollisionHandler {
    boolean isCollision(Sprite from, Dimension offset);
}
