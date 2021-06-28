package item;

import model.CollisionHandler;
import model.Sprite;
import model.SpriteCoordinate;

import static utils.LocationUtils.locationToCoordinate;

import java.awt.*;

public class ItemCollisionHandler implements CollisionHandler {
    @Override
    public boolean isCollision(Sprite from, Dimension offset) {
        SpriteCoordinate from_coordinate = locationToCoordinate(from.getLocation());
        for (Sprite to: from.getWorld().getSprites()) {
            SpriteCoordinate to_coordinate = locationToCoordinate(to.getLocation());
            if (to != from && to_coordinate.equals(from_coordinate)) { // collides with all kinds of Sprite
                return true;
            }
        }
        return false;
    }
}
