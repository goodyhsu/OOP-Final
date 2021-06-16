package obstacle;

import model.Sprite;
import model.SpriteCoordinate;
import model.SpriteShape;

import static utils.ImageStateUtils.readImage;
import static utils.LocationUtils.coordinate_to_location;

import java.io.File;
import java.awt.*;

public abstract class Obstacle extends Sprite{
    protected SpriteCoordinate coordinate;
    private SpriteShape shape;
    Image image;
    ObstacleImageRenderer renderer;
    public Obstacle(File file, SpriteCoordinate coordinate){
        this.coordinate = coordinate;
        this.location = coordinate_to_location(coordinate);
        this.image = readImage(file);
        this.renderer = new ObstacleImageRenderer(this);
        this.shape = new SpriteShape(new Dimension(75, 75),
                new Dimension(5, 5), new Dimension(60, 60));
    }

    @Override
    public void render(Graphics g){ this.renderer.render(this.image, g); }

    @Override
    public void onDamaged(Rectangle r, int i) {}

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }

    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }
}

