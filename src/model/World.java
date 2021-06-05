package model;

import obstacle.Obstacle;
import views.GameView;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class World {
    private final List<Sprite> sprites = new CopyOnWriteArrayList<>();
//    private final CollisionHandler collisionHandler;
    private final int obstacle_type = 2;    // ?
    private final ArrayList<String> obstacle_list = new ArrayList<String>();    // ?

    public World(Sprite... sprites) {
        addSprites(sprites);
    }

    public void setObstacles(GameView view) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        int min = 20;    // ?
        int max = 50;    // ?
        int obstacle_num = getRandomNumberUsingNextInt(min, max);
        for (int i = 0; i < obstacle_num; i++) {
            String name = obstacle_list.get(getRandomNumberUsingNextInt(0, obstacle_type));
            File file = new File(".");  //?
            boolean legalObstacle = false;
            Obstacle new_obstacle = getObstacleFromName(name, file, getCoordinate(view));
            while (!legalObstacle) {
                if (!checkCollision(new_obstacle))
                    legalObstacle = true;
            }
            addSprite(new_obstacle);
        }
    }

    private SpriteCoordinate getCoordinate(GameView view) {
        int y = getRandomNumberUsingNextInt(0, view.HEIGHT);
        int x = getRandomNumberUsingNextInt(0, view.WIDTH);
        y %= view.BLOCK_HEIGHT;
        x %= view.BLOCK_WIDTH;
        return new SpriteCoordinate(x, y);
    }

    private int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void update() {
        for (Sprite sprite : sprites) {
            sprite.update();
        }
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

    private boolean checkCollision(Sprite current) {
        Rectangle body = current.getBody();
        for (Sprite other : sprites) {
            if (other != current && body.intersects(other.getBody()))
                return true;
        }
        return false;
    }

    public void move(Sprite from, Dimension offset) {
        Point originalLocation = new Point(from.getLocation());
        from.getLocation().translate(offset.width, offset.height);
        boolean collision = checkCollision(from);
        if (collision)
            from.setLocation(originalLocation);
    }

    public boolean setBomb(Sprite bomb) {
        boolean collision = checkCollision(bomb);
        if (collision)
            return false;
        addSprite(bomb);
        return true;
    }

    public void bombExplode(Sprite bomb) {
        Rectangle damageArea = bomb.getRange();
//          -> a player, player.
//          -> a obstacle, if destroyable, remove
//          -> a bomb, explode
//        Sprite sprites = getSprites(damageArea);
//        for(Sprite sprite : sprites){
//            sprite.onDamaged(damageArea, bomb.getDamage());
//        }
    }

    public Collection<Sprite> getSprites(Rectangle area) {
        return sprites.stream()
                .filter(s -> area.intersects(s.getBody()))
                .collect(toSet());
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    // Actually, directly couple your model with the class "java.awt.Graphics" is not a good design
    // If you want to decouple them, create an interface that encapsulates the variation of the Graphics.
    public void render(Graphics g) {
        // background ?

        // sprites
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }

    private Obstacle getObstacleFromName(String class_name, File file, SpriteCoordinate coordinate) throws
            NoSuchMethodException, ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        Class<?> clazz = Class.forName(class_name);
        Constructor<?> ctor = clazz.getConstructor(File.class, SpriteCoordinate.class);
        Obstacle the_class = (Obstacle) ctor.newInstance(file, coordinate);
        return the_class;
    }
}
