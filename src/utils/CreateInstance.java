package utils;

import model.Sprite;
import model.SpriteCoordinate;
import player.PlayerType;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateInstance {
    public static Sprite createSpriteByName(String class_name, SpriteCoordinate coordinate) {
        Sprite sprite = null;
        try {
            Class<?> clazz = Class.forName(class_name);
            Constructor<?> ctor = clazz.getConstructor(SpriteCoordinate.class);
            sprite = (Sprite) ctor.newInstance(coordinate);
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return sprite;
    }

    public static PlayerType createPlayerTypeByName(String class_name, Point point, int idx, int type) {
        PlayerType playerType = null;
        try {
            Class<?> clazz = Class.forName(class_name);
            Constructor<?> ctor = clazz.getConstructor(Point.class, int.class, int.class);
            playerType = (PlayerType) ctor.newInstance(point, idx, type);
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return playerType;
    }
}
