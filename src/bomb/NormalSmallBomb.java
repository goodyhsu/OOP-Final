package bomb;

import model.Counter;
import model.Sprite;
import player.Player;

import java.awt.*;

public class NormalSmallBomb extends SmallBomb{
    public NormalSmallBomb(Player owner, Point location, int damage, int explode_range,
                     Counter before, Counter after){
        super(owner, location, damage, explode_range, before, after);
    }

    @Override
    public void explode_effect(){
        // Deal damage
        Rectangle damageArea = getRange();
        var sprites = this.world.getSprites(damageArea);
        for(Sprite sprite: sprites){
            sprite.onDamaged(damageArea, this.damage);
        }
    }
}
