package player;

import model.CollisionHandler;
import model.Sprite;
import item.Item;
import obstacle.Obstacle;
import model.World;
import utils.MusicUtils;

import java.awt.*;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class PlayerCollisionHandler implements CollisionHandler {

    private final MusicUtils musicUtils = new MusicUtils();

    @Override
    public boolean isCollision(Sprite now, Dimension offset) {
        List<Sprite> sprites = World.getSprites();
        Rectangle originalBody = now.getBody();
        Point originalLocation = new Point(now.getLocation());
        for (Sprite other : sprites) {
            if (other instanceof Player)
                continue;
            if (other instanceof bomb.NormalBomb || other instanceof Obstacle) {
                if (originalBody.intersects(other.getBody()))
                    continue;
                now.getLocation().translate(offset.width, offset.height);
                if (now.getBody().intersects(other.getBody())) {
                    now.setLocation(originalLocation);
                    return true;
                }
                now.getLocation().translate(-(offset.width), -(offset.height));
            }
            else if (other instanceof Item) {
                if (originalBody.intersects(other.getBody())) {
                    musicUtils.playMusic("music/SE/item_get.wav", false, true, false);
                    ((Item) other).setOwner((Player) now);
                    ((Item) other).effect();
                    ((Item) other).remove_item();
                }
            }

        }
        return false;
    }

    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {
    }
}