package bomb;

import model.*;
import player.Player;

import java.awt.*;

import static utils.LocationUtils.coordinate_addition;
import static utils.LocationUtils.coordinate_to_location;

public class NormalBomb extends Bomb{
    private SpriteShape shape;
    public NormalBomb(Player owner, Point owner_location, int damage, int explode_range,
                Counter before, Counter after){
        super(owner, owner_location, damage, explode_range, before, after);
        this.shape = new SpriteShape(new Dimension(146, 176),
                new Dimension(33, 38), new Dimension(66, 105));
    }

    @Override
    public void add_smallBomb(int num_smallBomb){
        SpriteCoordinate[] directions = {new SpriteCoordinate(0, num_smallBomb), new SpriteCoordinate(num_smallBomb, 0),
                new SpriteCoordinate(0, -num_smallBomb), new SpriteCoordinate(-num_smallBomb, 0)};

        Direction[] smallBomb_directions = Direction.values();
        for(int i = 0; i < 4; i++) {
            SpriteCoordinate smallBomb_coordinate = coordinate_addition(this.coordinate, directions[i]);
            SmallBomb smallBomb = new_smallBomb(this.owner, coordinate_to_location(smallBomb_coordinate), this.damage, 0,
                    this.before_explode_counter, this.after_explode_counter, smallBomb_directions[i]);
            this.world.addSprite(smallBomb);
        }
    }

    @Override
    public SmallBomb new_smallBomb(Player owner, Point smallBomb_location, int damage, int explode_range,
                                   Counter before, Counter after, Direction face){
        return new NormalSmallBomb(owner, smallBomb_location, damage, explode_range, before, after, face);
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

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }

    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }

}
