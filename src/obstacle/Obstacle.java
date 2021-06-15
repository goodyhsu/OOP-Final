package obstacle;

import model.Sprite;
import model.SpriteCoordinate;
import static utils.ImageStateUtils.readImage;

import java.io.File;
import java.awt.*;
public abstract class Obstacle extends Sprite{
    protected SpriteCoordinate coordinate;
    Image image;
    ObstacleImageRenderer renderer;
    public Obstacle(File file, SpriteCoordinate coordinate){
        this.coordinate = coordinate;
        this.image = readImage(file);
        this.renderer = new ObstacleImageRenderer(this);
        System.out.printf("x = %d, y = %d\n", coordinate.getX(), coordinate.getY());
    }
    @Override
    public void render(Graphics g){ this.renderer.render(this.image, g); }
    public void onDamaged(Rectangle r, int i) {}

}
