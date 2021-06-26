package item;

import model.SpriteCoordinate;
import static utils.ImageStateUtils.readImage;

import java.io.File;

public class Star extends Item{
    public Star(SpriteCoordinate coordinate){
        super(coordinate);
        File imageFile = new File("sprites/item/star/0.png");
        this.image = readImage(imageFile);
    }

    @Override
    public void update(){}

    @Override
    public void effect(){this.owner.setStar();}
}
