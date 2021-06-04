package utils;

import model.SpriteCoordinate;
import static views.GameView.BLOCK_HEIGHT;
import static views.GameView.BLOCK_WIDTH;

import java.awt.*;
import java.awt.desktop.AboutEvent;

public class LocationUtils {
    public static Point coordinate_to_location(SpriteCoordinate coordinate){
        int x = coordinate.getX() * BLOCK_WIDTH + BLOCK_WIDTH / 2;
        int y = coordinate.getY() * BLOCK_HEIGHT + BLOCK_HEIGHT / 2;
        return new Point(x, y);
    }

    public static SpriteCoordinate location_to_coordinate(Point location){
        // Return the nearest coordinate
        double double_x = location.getX() / BLOCK_WIDTH;
        int x = (int)double_x;
        x = (double_x - x <= 0.5)? x : x+1;
        double double_y = location.getY() / BLOCK_HEIGHT;
        int y = (int)double_y;
        y = (double_y - y <= 0.5)? y : y+1;

        return new SpriteCoordinate(x, y);
    }

    public static SpriteCoordinate coordinate_addition(SpriteCoordinate original, SpriteCoordinate offset){
        int x = original.getX() + offset.getX();
        int y = original.getY() + offset.getY();
        return new SpriteCoordinate(x, y);
    }
}
