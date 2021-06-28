package map;

import item.Item;
import item.ItemCollisionHandler;
import model.SpriteCoordinate;
import model.World;
import obstacle.*;
import views.GameView;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static utils.CreateInstanceUtils.createSpriteByName;

public class GameMap extends Map{
    private File map_file;
    private ArrayList<String> obstacle_img_list;
    private ArrayList<String> items;
    private ArrayList<SpriteCoordinate> player_coordinates = new ArrayList<>();

    public GameMap(String background_file, String map_file, String music_file,
                   ArrayList<String> obstacle_img_list, ArrayList<String> items){
        super(background_file, music_file);
        this.map_file = new File(map_file);
        this.obstacle_img_list = obstacle_img_list;
        this.items = items;
    }

    @Override
    public Image getBackground_image(){ return this.background_image; }
    public File getMap_file(){ return this.map_file; }
    public String getMusic_file(){ return this.music_file; }

    @Override
    public void setMap() {
        player_coordinates.clear();
        setMapBoundary();
        try {
            FileReader fr= new FileReader(this.map_file);   //reads the file
            BufferedReader br= new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while((line = br.readLine())!=null) {
                String[] splitLine = line.split("\\s+");
                String name = splitLine[0];
                int x = Integer.parseInt(splitLine[1]);
                int y = Integer.parseInt(splitLine[2]);
                if (name.equals("p1") || (name.equals("p2"))) {
                    player_coordinates.add(new SpriteCoordinate(x, y));
                }
                else
                    addObstacle(obstacle_img_list, name, new SpriteCoordinate(x, y));
            }
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void setMapBoundary(){
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

    public void setItems(int item_num) {
        int i = 0;
        int item_idx = 0;
        ItemCollisionHandler itemCollisionHandler = new ItemCollisionHandler();
        while (i < item_num) {
            if (i % items.size() == 0) {
                item_idx = getRandomNumber(0, items.size());
            }
            int x = getRandomNumber(0, GameView.WIDTH / GameView.BLOCK_WIDTH);
            int y = getRandomNumber(0, GameView.HEIGHT / GameView.BLOCK_HEIGHT);
            Item new_item = (Item) createSpriteByName("item." + items.get(item_idx), new SpriteCoordinate(x, y));
            if (!(itemCollisionHandler.isCollision(new_item, new_item.getBodyOffset()))) {
                this.world.addSprites(new_item);
                i++;
            }
            item_idx = (item_idx + 1) % items.size();
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
        this.world.addSprite(obstacle);
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public ArrayList<SpriteCoordinate> getPlayerCoordinates() {
        return player_coordinates;
    }
}
