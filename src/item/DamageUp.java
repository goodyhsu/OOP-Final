package item;

import model.SpriteCoordinate;
import player.Player;
import static utils.ImageStateUtils.readImage;

import java.io.File;

public class DamageUp extends Item{
    public DamageUp(SpriteCoordinate coordinate, Player owner){
        super(coordinate, owner);
        File imageFile = new File("sprites/item/damageUp/0.png");
        this.image = readImage(imageFile);
    }

    @Override
    public void update(){}

    @Override
    public void effect(){ this.owner.addDamage(); }
}
