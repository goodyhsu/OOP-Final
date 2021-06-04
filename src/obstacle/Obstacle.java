package obstacle;

import model.Sprite;
import static utils.ImageStateUtils.readImage;

import java.io.File;
import java.awt.*;
public abstract class Obstacle extends Sprite{
    Image image;
    ObstacleImageRenderer renderer;
    public Obstacle(File file){
        this.image = readImage(file);
        this.renderer = new ObstacleImageRenderer(this);
    }
    @Override
    public void render(Graphics g){ this.renderer.render(g, this.image); }
}
