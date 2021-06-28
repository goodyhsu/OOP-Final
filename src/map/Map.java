package map;

import model.World;
import static utils.ImageStateUtils.readImage;

import java.awt.*;
import java.io.File;

public abstract class Map {
    protected World world;
    protected Image background_image;
    public String music_file;

    public Map(String background_file, String music_file){
        setBackground_image(background_file);
        this.music_file = music_file;
    }

    public void setWorld(World world) { this.world = world; }

    public abstract void setMap();
    public abstract void setBackground_image(String background_file);
    public abstract Image getBackground_image();
}
