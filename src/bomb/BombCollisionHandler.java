package bomb;

import model.CollisionHandler;
import model.Sprite;

import java.awt.*;

public class BombCollisionHandler implements CollisionHandler {
    @Override
    public boolean checkCollision(Sprite from) {
        return false;
    }

    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {

    }
}
