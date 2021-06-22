package item;

import model.SpriteCoordinate;
import player.Player;
import static utils.ImageStateUtils.readImage;

import java.io.File;

public class SpeedUp extends Item{
    public SpeedUp(SpriteCoordinate coordinate, Player owner){
        super(coordinate);
        File imageFile = new File("sprites/item/speedUp/0.png");
        this.image = readImage(imageFile);
    }

    @Override
    public void update(){}

    @Override
    public void effect(){ this.owner.addSpeed(); }
}
