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
                           Direction face, Counter after_counter){
        super(owner, location, damage, explode_range, face, after_counter);
        this.shape = new SpriteShape(new Dimension(50, 50),
                new Dimension(20, 20), new Dimension(10, 10));
    }

    @Override
    protected void explode_effect(){
        // Deal damage
        Rectangle damageArea = getRange();
        var sprites = this.world.getSprites(damageArea);
        for(Sprite sprite: sprites){
            sprite.damaged(this.damage);
        }
    }

    @Override
    protected SmallBomb new_smallBomb(Player owner, Point smallBomb_location, int damage, int explode_range,
                                   Counter before, Counter after, Direction face){
        return null;
    }

    @Override
    protected void add_smallBomb(int num_smallBomb, boolean[] direction_stop){}

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

    public Rectangle getBody(){return getArea(getBodyOffset(), getBodySize());}
}
