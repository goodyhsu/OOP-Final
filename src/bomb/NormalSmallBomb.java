package bomb;

import model.Counter;
import model.Direction;
import model.Sprite;
import model.SpriteShape;
import player.Player;

import java.awt.*;

public class NormalSmallBomb extends SmallBomb{
    private SpriteShape shape;
    public NormalSmallBomb(Player owner, Point location, int damage, int explode_range,
                           Direction face){
        super(owner, location, damage, explode_range, face);
        this.shape = new SpriteShape(new Dimension(176, 176),
                new Dimension(33, 38), new Dimension(66, 105));
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
    public SmallBomb new_smallBomb(Player owner, Point smallBomb_location, int damage, int explode_range,
                                   Counter before, Counter after, Direction face){
        return null;
    }

    @Override
    public void add_smallBomb(int num_smallBomb){}

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
