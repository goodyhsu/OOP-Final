package obstacle;

import model.SpriteCoordinate;
import model.SpriteShape;

import java.awt.*;
import java.io.File;

public class Wood extends DestroyableObstacle{
    private SpriteShape shape;
    public Wood(File file, SpriteCoordinate coordinate){
        super(file, coordinate);
        this.shape = new SpriteShape(new Dimension(75, 75),
                new Dimension(coordinate.getX()*75, coordinate.getY()*75), new Dimension(75, 75));
        Dimension offset = this.shape.bodyOffset;
        this.getLocation().translate(offset.width, offset.height);
    }


    @Override
    public void update(){}

    @Override
    public Rectangle getRange(){ return new Rectangle(location, shape.size); }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }

    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }

}
