package obstacle;

import model.SpriteCoordinate;
import model.SpriteShape;

import java.awt.*;
import java.io.File;

public class Wood extends DestroyableObstacle{
    private SpriteShape shape;
    public Wood(File file, SpriteCoordinate coordinate){
        super(file, coordinate);
        this.shape = new SpriteShape(new Dimension(146, 176),
                new Dimension(33, 38), new Dimension(66, 105));
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
