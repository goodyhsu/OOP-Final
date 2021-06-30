package bomb;

import model.*;
import player.Player;

import static utils.LocationUtils.isInBoundary;

import java.awt.*;

import static utils.LocationUtils.coordinateAddition;
import static utils.LocationUtils.coordinateToLocation;

public class NormalBomb extends Bomb{
    private SpriteShape shape;

    public NormalBomb(Player owner, Point owner_location, int damage, int explode_range){
        super(owner, owner_location, damage, explode_range);
        this.shape = new SpriteShape(new Dimension(50, 50),
                new Dimension(10, 15), new Dimension(35, 35));
        this.direction_stop = new boolean[]{false, false, false, false};
    }

    @Override
    protected void add_smallBomb(int num_smallBomb, boolean[] direction_stop){
        Direction[] smallBomb_directions = Direction.values(); // UP, DOWN, LEFT, RIGHT
        SpriteCoordinate[] coordinate_offset = {new SpriteCoordinate(0, -num_smallBomb), new SpriteCoordinate(0, num_smallBomb),
                new SpriteCoordinate(-num_smallBomb, 0), new SpriteCoordinate(num_smallBomb, 0)};

        for(int i = 0; i < 4; i++) {
            SpriteCoordinate smallBomb_coordinate = coordinateAddition(this.coordinate, coordinate_offset[i]);
            // check if the smallBomb should be stopped
            boolean[] smallBomb_collision = isSmallBombCollision(smallBomb_coordinate);
            if(smallBomb_collision[0])
                direction_stop[i] = true;

            if (isInBoundary(smallBomb_coordinate) && !direction_stop[i]) { // okay to add a smallBomb
                SmallBomb smallBomb = new_smallBomb(this.owner, coordinateToLocation(smallBomb_coordinate), this.damage, 0,
                        this.before_explode_counter, this.after_explode_counter, smallBomb_directions[i]);
                this.world.addSprite(smallBomb);
            }

            if(smallBomb_collision[1])
                direction_stop[i] = true;
        }
    }

    @Override
    protected SmallBomb new_smallBomb(Player owner, Point smallBomb_location, int damage, int explode_range,
                                   Counter before, Counter after, Direction face){
        return new NormalSmallBomb(owner, smallBomb_location, damage, explode_range, face, this.after_explode_counter);
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
