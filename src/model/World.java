package model;

import imageRenderer.ImageRenderer;
import map.GameMap;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class World {
    private static final List<Sprite> sprites = new CopyOnWriteArrayList<>();
    private ImageRenderer renderer;
    protected GameMap map;

    public World() {}

    public void setRenderer(ImageRenderer renderer) { this.renderer = renderer; }

    public GameMap getMap(){ return this.map; }

    public void setMap(GameMap map) {
        this.map = map;
    }
    public void update() {
        for (Sprite sprite : sprites) {
            sprite.update();
        }
    }

    public void reset() {
        sprites.clear();
    }

    public void addSprites(Sprite... sprites) {
        stream(sprites).forEach(this::addSprite);
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.setWorld(this);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
        sprite.setWorld(null);
    }

    public abstract void move(Sprite sprite, Dimension offset);

    public Collection<Sprite> getSprites(Rectangle area) {
        return sprites.stream()
                .filter(s -> area.intersects(s.getBody()))
                .collect(toSet());
    }

    public static List<Sprite> getSprites() {
        return sprites;
    }

    public void render(){ this.renderer.render(); }
}
