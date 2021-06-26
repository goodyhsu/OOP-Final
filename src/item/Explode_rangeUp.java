package item;

import model.SpriteCoordinate;
import static utils.ImageStateUtils.readImage;

import java.io.File;

public class Explode_rangeUp extends Item{
    public Explode_rangeUp(SpriteCoordinate coordinate){
        super(coordinate);
        File imageFile = new File("sprites/item/explode_rangeUp/0.png");
        this.image = readImage(imageFile);
    }

    @Override
    public void update(){}

    @Override
    public void effect(){ this.owner.addDamageArea(); }
}
