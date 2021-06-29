package model;

import controller.GameLoop;
import imageRenderer.ImageRenderer;
import map.Map;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

public abstract class World {
    private static final List<Sprite> sprites = new CopyOnWriteArrayList<>();
    private ImageRenderer renderer;
    protected Map map;
    private GameLoop game;

    public World() {}

    public void setRenderer(ImageRenderer renderer) { this.renderer = renderer; }

    public Map getMap(){ return this.map; }

    public void setMap(Map map) {
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

    public void setGame(GameLoop game) {
        this.game = game;
    }

    public GameLoop getGame() {
        return this.game;
    }

    public void render(){ this.renderer.render(); }
}
