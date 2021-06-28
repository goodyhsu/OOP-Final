package monster;

import model.CollisionHandler;
import model.Sprite;
import model.World;
import obstacle.Obstacle;
import player.Player;

import java.awt.*;
import java.util.List;

public class MonsterCollisionHandler implements CollisionHandler {

    @Override
    public boolean isCollision(Sprite now, Dimension offset) {
        List<Sprite> sprites = World.getSprites();
        Rectangle originalBody = now.getBody();
        Point originalLocation = new Point(now.getLocation());
        for (Sprite other : sprites) {
            if (other instanceof bomb.NormalBomb || other instanceof Obstacle) {
                if (originalBody.intersects(other.getBody()))
                    continue;
                now.getLocation().translate(offset.width, offset.height);
                if (now.getBody().intersects(other.getBody())) {
                    now.setLocation(originalLocation);
                    return true;
                }
                now.getLocation().translate(-(offset.width), -(offset.height));
            } else if (other instanceof Player) {
                if (originalBody.intersects(other.getBody())) {
                    other.damaged(((Monster) now).getDamage());
                }
            }
        }
        return false;
    }
}
