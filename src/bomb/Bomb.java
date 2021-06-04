package bomb;

import model.Sprite;
import model.SpriteCoordinate;
import model.Counter;
import player.Player;
import static utils.LocationUtils.*;

import java.awt.*;

public abstract class Bomb extends Sprite {
    protected final Player owner;
    protected final SpriteCoordinate coordinate;
    protected final int damage;
    protected final int explode_range;
    protected int num_smallBomb;
    protected Image image;
    protected final Counter before_explode_counter, after_explode_counter; //3000, 4000
    protected boolean exploded;

    public Bomb(Player owner, Point owner_location, int damage, int explode_range,
                Counter before, Counter after){
        this.owner = owner;
        this.coordinate = calculate_coordinate(owner_location);
        this.location = coordinate_to_location(coordinate);
        this.damage = damage;
        this.explode_range = explode_range;
        this.num_smallBomb = 0;
        this.before_explode_counter = before;
        this.after_explode_counter = after;
        this.exploded = false;
    }

    private SpriteCoordinate calculate_coordinate(Point owner_location){}
    protected void add_smallBomb(){
        SpriteCoordinate[] directions = {new SpriteCoordinate(0, 1), new SpriteCoordinate(1, 0),
                new SpriteCoordinate(0, -1), new SpriteCoordinate(-1, 0)};
        for(int i = 0; i < 4; i++) {
            SpriteCoordinate smallbomb_coordinate = coordinate_addition(this.coordinate, directions[i]);
            SmallBomb smallBomb = new_smallBomb(this.owner, coordinate_to_location(smallbomb_coordinate), this.damage, 0,
                    this.before_explode_counter, this.after_explode_counter);
        }
    }
    protected abstract SmallBomb new_smallBomb(Player owner, Point owner_location, int damage, int explode_range,
                                               Counter before, Counter after);

    @Override
    public void update(){
        if(before_explode_counter.time_up()){ this.exploded = true; }
        if(this.exploded){
            // Deal damage
            Rectangle damageArea = getRange();
            var sprites = this.world.getSprites(damageArea);
            for(Sprite sprite: sprites){
                sprite.onDamaged(damageArea, this.damage);
            }
        }
        if(this.exploded && this.num_smallBomb < this.explode_range){
            this.num_smallBomb++;
            add_smallBomb();
        }
        if(this.after_explode_counter.time_up())
            this.world.removeSprite(this);
    }

    public void onDamaged(Rectangle r, int i){}
}
