package model;

import bomb.BombCollisionHandler;
import obstacle.Obstacle;
import player.PlayerCollisionHandler;
import views.GameView;
import obstacle.Wood;
import obstacle.Stone;

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
    private static final List<Sprite> sprites = new CopyOnWriteArrayList<>();
    private final int obstacle_type = 2;
    private ArrayList<String> obstacle_list;
    private PlayerCollisionHandler playerCollisionHandler;
    private BombCollisionHandler bombCollisionHandler;

    public World(PlayerCollisionHandler playerCollisionHandler, BombCollisionHandler bombCollisionHandler, Sprite... sprites) {
        this.playerCollisionHandler = playerCollisionHandler;
        this.bombCollisionHandler = bombCollisionHandler;
        addSprites(sprites);
    }

    public void setObstacles(GameView view, ArrayList<String> obstacle_list, ArrayList<String> obstacle_img_list){
        int min = 20;    // ?
        int max = 30;    // ?
//        int obstacle_num = getRandomNumberUsingNextInt(min, max);
//        System.out.printf("obstacle_num = %d\n", obstacle_num);
        this.obstacle_list = obstacle_list;
        int num_block_w = view.WIDTH / view.BLOCK_WIDTH;
        int num_block_h = view.HEIGHT / view.BLOCK_HEIGHT;
//        System.out.printf("num_block_w = %d\n", num_block_w);
        for (int x = 0; x < num_block_w; x++) {
            addObstacle(obstacle_list, obstacle_img_list, new SpriteCoordinate(x, 0), 0);
            addObstacle(obstacle_list, obstacle_img_list, new SpriteCoordinate(x, num_block_h-1), 0);
        }
        for (int y = 1; y < num_block_h-1; y++) {
            addObstacle(obstacle_list, obstacle_img_list, new SpriteCoordinate(0, y), 0);
            addObstacle(obstacle_list, obstacle_img_list, new SpriteCoordinate(num_block_w-1, y), 0);
        }
//        for (int i = 0; i < obstacle_num; i++) {
//            int idx = getRandomNumberUsingNextInt(0, obstacle_type);
//            String name = obstacle_list.get(idx);
//            File file = new File(obstacle_img_list.get(idx));  //?
////            System.out.println(name);
//            boolean legalObstacle = false;
//            try {
//                Obstacle new_obstacle = getObstacleFromName(name, file, getCoordinate(view));
//                while (!legalObstacle) {
////                    if (!isCollision(new_obstacle))
//                    legalObstacle = true;
//                }
//                addSprite(new_obstacle);
//            } catch (ClassNotFoundException | NoSuchMethodException |
//                    InvocationTargetException | InstantiationException | IllegalAccessException e) {
//                System.out.println("exception");
//                continue;
//            }
//        }
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

//    private boolean isCollision(Sprite current) { /*之後刪掉*/
//        Rectangle body = current.getBody();
//        for (Sprite other : sprites) {
//            if (other != current && body.intersects(other.getBody()))
//                return true;
//        }
//        return false;
//    }

    public void move(Sprite player, Dimension offset) {
        PlayerCollisionHandler collisionHandler = getPlayerCollisionHandler();
        boolean collision = collisionHandler.isCollision(player, offset);
        if (!collision)
            player.getLocation().translate(offset.width, offset.height);
    }

    public boolean setBomb(Sprite bomb) {
        //boolean collision = isCollision(bomb);
        //if (collision)
        //    return false;
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

    public static List<Sprite> getSprites() {
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

    public PlayerCollisionHandler getPlayerCollisionHandler() {
        return playerCollisionHandler;
    }

    public BombCollisionHandler getBombCollisionHandler() {
        return bombCollisionHandler;
    }

    private void addObstacle(ArrayList<String> obstacle_list, ArrayList<String> obstacle_img_list, SpriteCoordinate coordinate, int specific) {
        int idx;
        if (specific == -1)
            idx = getRandomNumberUsingNextInt(0, obstacle_type);
        else idx = specific;
        String name = obstacle_list.get(idx);
        File file = new File(obstacle_img_list.get(idx));
        Obstacle obstacle;
        if (name == "Wood")
            obstacle = (Obstacle) new Wood(file, coordinate);
        else
            obstacle = (Obstacle) new Stone(file, coordinate);
        addSprite(obstacle);
    }
}
