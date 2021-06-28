package model;

import bomb.BombCollisionHandler;
import imageRenderer.ImageRenderer;
import map.Map;
import player.PlayerCollisionHandler;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class World {
    private static final List<Sprite> sprites = new CopyOnWriteArrayList<>();
    private final PlayerCollisionHandler playerCollisionHandler = new PlayerCollisionHandler();
    private final BombCollisionHandler bombCollisionHandler = new BombCollisionHandler();
    private Map map = null;
    private ImageRenderer renderer;

    public World() {}

    public void setRenderer(ImageRenderer renderer) { this.renderer = renderer; }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap(){ return this.map; }

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

    public void move(Sprite player, Dimension offset) {
        PlayerCollisionHandler collisionHandler = getPlayerCollisionHandler();
        boolean collision = collisionHandler.isCollision(player, offset);
        if (!collision)
            player.getLocation().translate(offset.width, offset.height);
    }

    public boolean setBomb(Sprite bomb) {
        if (getBombCollisionHandler().isCollision(bomb, bomb.getBodyOffset())) {
            return false;
        }
        addSprite(bomb);
        return true;
    }

    public Collection<Sprite> getSprites(Rectangle area) {
        return sprites.stream()
                .filter(s -> area.intersects(s.getBody()))
                .collect(toSet());
    }

    public static List<Sprite> getSprites() {
        return sprites;
    }

    // Actually, directly couple your model with the class "java.awt.Graphics" is not a good design
    // If you want to decouple them, create an interface that encapsulates the variation of the Graphics.

    public void render(Graphics g) {
        // background
        this.map.render(g);
        // sprites
//        System.out.println("in world render");
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }

    public void render(){ this.renderer.render(); }

    public PlayerCollisionHandler getPlayerCollisionHandler() {
        return playerCollisionHandler;
    }

    public BombCollisionHandler getBombCollisionHandler() {
        return bombCollisionHandler;
    }
}
