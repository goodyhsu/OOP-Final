package map;

import item.Item;
import model.SpriteCoordinate;
import model.World;
import views.GameView;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static utils.CreateInstanceUtils.createSpriteByName;
import static utils.ImageStateUtils.readImage;

public class Map {
    World world;
    Image background_image;
    File map_file;
    String music_file;

    public Map(World world, String background_file, String map_file, String music_file){
        this.world = world;
        this.background_image = readImage(new File("maps/backgrounds", background_file));
        this.map_file = new File(map_file);
        this.music_file = music_file;
    }

    /*public void setMap() {
        setMapBoundary();
        try {
            File file=new File("maps/map_2.txt");    //creates a new file instance
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
    }*/

}
