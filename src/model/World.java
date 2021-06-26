package model;

import bomb.BombCollisionHandler;
import item.Item;
import item.ItemCollisionHandler;
import map.Map;
import obstacle.Obstacle;
import player.PlayerCollisionHandler;
import views.GameView;
import obstacle.Wood;
import obstacle.Stone;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static utils.CreateInstanceUtils.createSpriteByName;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class World {
    private static final List<Sprite> sprites = new CopyOnWriteArrayList<>();
//    private ArrayList<String> obstacle_img_list;
    private PlayerCollisionHandler playerCollisionHandler = new PlayerCollisionHandler();
    private BombCollisionHandler bombCollisionHandler = new BombCollisionHandler();
    private Map map;
//    private ItemCollisionHandler itemCollisionHandler = new ItemCollisionHandler();
//    ArrayList<String> items = new ArrayList<String>();

    public World(Map map) {
//        this.obstacle_img_list = obstacle_img_list;
//        items.addAll(Arrays.asList("DamageUp", "Explode_rangeUp", "IncreaseBomb_num", "IncreaseHP", "SpeedUp", "Star"));
        this.map = map;
    }

    public Map getMap(){ return this.map; }
    /*public void setMap() {
        setMapBoundary();
        try {
            File file=new File("maps/files/map_2.txt");    //creates a new file instance
            FileReader fr= new FileReader(file);   //reads the file
            BufferedReader br= new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while((line = br.readLine())!=null) {
                String[] splitLine = line.split("\\s+");
                String name = splitLine[0];
                int x = Integer.parseInt(splitLine[1]);
                int y = Integer.parseInt(splitLine[2]);
                addObstacle(obstacle_img_list, name, new SpriteCoordinate(x, y));
            }
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setItems(int item_num) {
        int i = 0;
        int item_idx = 0;
        while (i < item_num) {
            if (i % items.size() == 0) {
                item_idx = getRandomNumber(0, items.size());
            }
            int x = getRandomNumber(0, GameView.WIDTH / GameView.BLOCK_WIDTH);
            int y = getRandomNumber(0, GameView.HEIGHT / GameView.BLOCK_HEIGHT);
            Item new_item = (Item) createSpriteByName("item." + items.get(item_idx), new SpriteCoordinate(x, y));
            if (!(itemCollisionHandler.isCollision(new_item, new_item.getBodyOffset()))) {
                addSprites(new_item);
                i++;
            }
            item_idx = (item_idx + 1) % items.size();
        }
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }*/

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

    /*private void setMapBoundary(){
        int num_block_w = GameView.WIDTH / GameView.BLOCK_WIDTH;
        int num_block_h = GameView.HEIGHT / GameView.BLOCK_HEIGHT;
        // boundary
        for (int x = 0; x < num_block_w; x++) {
            addObstacle(obstacle_img_list, "Stone", new SpriteCoordinate(x, 0));
            addObstacle(obstacle_img_list, "Stone", new SpriteCoordinate(x, num_block_h-1));
        }
        for (int y = 1; y < num_block_h-1; y++) {
            addObstacle(obstacle_img_list, "Stone", new SpriteCoordinate(0, y));
            addObstacle(obstacle_img_list, "Stone", new SpriteCoordinate(num_block_w-1, y));
        }
    }

    private void addObstacle(ArrayList<String> obstacle_img_list, String name, SpriteCoordinate coordinate) {
        File file;
        Obstacle obstacle;
        if (name.equals("Wood")) {
            file = new File(obstacle_img_list.get(0));
            obstacle = new Wood(file, coordinate);
        }
        else {
            file = new File(obstacle_img_list.get(1));
            obstacle = new Stone(file, coordinate);
        }
        addSprite(obstacle);
    }*/
}
