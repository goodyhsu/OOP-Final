package utils;

import model.SpriteCoordinate;
import static views.GameView.BLOCK_HEIGHT;
import static views.GameView.BLOCK_WIDTH;

import java.awt.*;
import java.awt.desktop.AboutEvent;

public class LocationUtils {
    public static Point coordinate_to_location(SpriteCoordinate coordinate){
        // A coordinate lies on the center of a block
        int x = coordinate.getX() * BLOCK_WIDTH + BLOCK_WIDTH / 2;
        int y = coordinate.getY() * BLOCK_HEIGHT + BLOCK_HEIGHT / 2;
        return new Point(x, y);
    }

    public static SpriteCoordinate location_to_coordinate(Point location){
        // Returns the nearest coordinate of the given location
        double double_x = location.getX() / BLOCK_WIDTH;
        int x = (int)double_x;
        double double_y = location.getY() / BLOCK_HEIGHT;
        int y = (int)double_y;

        return new SpriteCoordinate(x, y);
    }

    public static SpriteCoordinate coordinate_addition(SpriteCoordinate original, SpriteCoordinate offset){
        int x = original.getX() + offset.getX();
        int y = original.getY() + offset.getY();
        return new SpriteCoordinate(x, y);
    }
}
