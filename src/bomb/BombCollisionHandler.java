package bomb;

import model.CollisionHandler;
import model.Sprite;
import model.SpriteCoordinate;
import obstacle.Obstacle;

import static utils.LocationUtils.locationToCoordinate;

import java.awt.*;

public class BombCollisionHandler implements CollisionHandler {
    @Override
    public boolean isCollision(Sprite from, Dimension offset) {
        SpriteCoordinate from_coordinate = locationToCoordinate(from.getLocation());
        for (Sprite to: from.getWorld().getSprites()) {
            SpriteCoordinate to_coordinate = locationToCoordinate(to.getLocation());
            if (to != from && to_coordinate == from_coordinate) {
                if (to instanceof Bomb && !(to instanceof SmallBomb))
                    return true;
                else if (to instanceof Obstacle)
                    return true;
            }
        }
        return false;
    }
}
