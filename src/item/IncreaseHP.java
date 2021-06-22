package item;

import model.SpriteCoordinate;
import player.Player;
import static utils.ImageStateUtils.readImage;

import java.io.File;

public class IncreaseHP extends Item{
    public IncreaseHP(SpriteCoordinate coordinate){
        super(coordinate);
        File imageFile = new File("sprites/item/increaseHP/0.png");
        this.image = readImage(imageFile);
    }

    @Override
    public void update(){}

    @Override
    public void effect(){ this.owner.healed(200); }
}
