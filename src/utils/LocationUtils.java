package utils;

import model.SpriteCoordinate;

import java.awt.*;

public class LocationUtils {
    public static Point coordinate_to_location(SpriteCoordinate coordinate){
        return new Point(coordinate.getX(), coordinate.getY());
    }

    public static SpriteCoordinate coordinate_addition(SpriteCoordinate original, SpriteCoordinate offset){
        int x = original.getX() + offset.getX();
        int y = original.getY() + offset.getY();
        return new SpriteCoordinate(x, y);
    }
}
