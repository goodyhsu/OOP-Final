package bomb;

import model.Counter;
import model.Sprite;
import model.SpriteCoordinate;
import player.Player;

import java.awt.*;

import static utils.LocationUtils.coordinate_addition;
import static utils.LocationUtils.coordinate_to_location;

public class NormalBomb extends Bomb{
    public NormalBomb(Player owner, Point owner_location, int damage, int explode_range,
                Counter before, Counter after){
        super(owner, owner_location, damage, explode_range, before, after);
    }

    @Override
    public void add_smallBomb(){
        SpriteCoordinate[] directions = {new SpriteCoordinate(0, 1), new SpriteCoordinate(1, 0),
                new SpriteCoordinate(0, -1), new SpriteCoordinate(-1, 0)};
        for(int i = 0; i < 4; i++) {
            SpriteCoordinate smallbomb_coordinate = coordinate_addition(this.coordinate, directions[i]);
            SmallBomb smallBomb = new_smallBomb(this.owner, coordinate_to_location(smallbomb_coordinate), this.damage, 0,
                    this.before_explode_counter, this.after_explode_counter);
            this.world.addSprite(smallBomb);
        }
    }

    public SmallBomb new_smallBomb(Player owner, Point smallbomb_location, int damage, int explode_range,
                                   Counter before, Counter after){
        return new NormalSmallBomb(owner, smallbomb_location, damage, explode_range, before, after);
    }

    public void explode_effect(){
        // Deal damage
        Rectangle damageArea = getRange();
        var sprites = this.world.getSprites(damageArea);
        for(Sprite sprite: sprites){
            sprite.onDamaged(damageArea, this.damage);
        }
    }
}
