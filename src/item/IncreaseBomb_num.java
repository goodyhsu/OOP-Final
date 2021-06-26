package item;

import model.SpriteCoordinate;
import static utils.ImageStateUtils.readImage;

import java.io.File;

public class IncreaseBomb_num extends Item{
    public IncreaseBomb_num(SpriteCoordinate coordinate){
        super(coordinate);
        File imageFile = new File("sprites/item/increaseBomb_num/0.png");
        this.image = readImage(imageFile);
    }

    @Override
    public void update(){}

    @Override
    public void effect(){ this.owner.addNum_bomb_max(); }
}
