package bomb;

import model.Counter;
import model.SpriteCoordinate;
import player.Player;

public abstract class SmallBomb extends Bomb{
    public SmallBomb(Player owner, SpriteCoordinate coordinate, int damage, int explode_range,
                     Counter before, Counter after){
        super(owner, coordinate, damage, explode_range, before, after);
        this.exploded = true;
    }
}
