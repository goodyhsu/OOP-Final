package player;

import model.CollisionHandler;
import model.Sprite;
import bomb.Bomb;
import model.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class PlayerCollisionHandler implements CollisionHandler {

    @Override
    public boolean isCollision(Sprite now) {
        List<Sprite> sprites = World.getSprites();
        Rectangle body = now.getBody();
        for (Sprite other : sprites) {
            if (other instanceof Player)
                continue;
            if (other instanceof Bomb) {
                if (body.intersects(other.getBody()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {
    }
}