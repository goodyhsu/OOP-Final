package map;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static utils.ImageStateUtils.readImage;

public class AnimationMap extends GameMap{
    private int current_idx;
    private int file_num;
    private File background_dir;
    private ArrayList<Image> background_images;
    private int speed;
    int count = 0;
    public AnimationMap(String background_file, String map_file, String music_file, ArrayList<String> obstacle_img_list, ArrayList<String> items, int speed) {
        super(background_file, map_file, music_file, obstacle_img_list, items);
        this.background_dir = new File(background_file);
        this.file_num = background_dir.list().length;
        this.current_idx = 0;
        this.speed = speed;
        setBackground_image(background_file);
    }

    @Override
    public void setBackground_image(String background_file) {
        this.background_images = new ArrayList<>();
        for (int i = 0; i < file_num; i++) {
            String filename = background_dir + "/" + i + ".png";
            background_images.add(readImage(new File(filename)));
        }
    }

    @Override
    public Image getBackground_image() {
        Image image = background_images.get(current_idx);
        count += 1;
        if (count % speed == 0) {
            count = 0;
            current_idx += 1;
            current_idx %= file_num;
        }
        return image;
    }
}
