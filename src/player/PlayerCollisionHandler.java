package player;

import model.CollisionHandler;
import model.Sprite;
import bomb.Bomb;
import obstacle.Obstacle;
import model.World;

import java.awt.*;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class PlayerCollisionHandler implements CollisionHandler {

    @Override
    public boolean isCollision(Sprite now, Dimension offset) {
        List<Sprite> sprites = World.getSprites();
        Rectangle originalBody = now.getBody();
        Point originalLocation = new Point(now.getLocation());
        for (Sprite other : sprites) {
            if (other instanceof Player)
                continue;
            if (other instanceof Bomb || other instanceof Obstacle) {
                if (originalBody.intersects(other.getBody()))
                    continue;
                now.getLocation().translate(offset.width, offset.height);
                if (now.getBody().intersects(other.getBody())) {
                    now.setLocation(originalLocation);
                    return true;
                }
                now.getLocation().translate(-(offset.width), -(offset.height));
            }
        }
        return false;
    }

    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {
    }
}