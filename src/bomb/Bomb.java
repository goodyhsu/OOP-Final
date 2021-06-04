package bomb;

import model.Sprite;
import model.SpriteCoordinate;
import model.Counter;
import player.Player;
import static utils.ImageStateUtils.readImage;
import static utils.LocationUtils.*;

import java.awt.*;
import java.io.File;

public abstract class Bomb extends Sprite {
    protected final Player owner;
    protected final SpriteCoordinate coordinate;
    protected final int damage;
    protected final int explode_range;
    protected int num_smallBomb;
    protected final Counter before_explode_counter, after_explode_counter; //3000, 4000
    protected boolean exploded;
    protected BombImageRenderer renderer;

    public Bomb(Player owner, Point owner_location, int damage, int explode_range,
                Counter before, Counter after){
        this.owner = owner;
        this.coordinate = location_to_coordinate(owner_location);
        this.location = coordinate_to_location(this.coordinate);
        this.damage = damage;
        this.explode_range = explode_range;
        this.num_smallBomb = 0;
        this.before_explode_counter = before;
        this.after_explode_counter = after;
        this.exploded = false;
        this.renderer = new BombImageRenderer(this);
    }

    protected abstract void add_smallBomb();
    protected abstract SmallBomb new_smallBomb(Player owner, Point owner_location, int damage, int explode_range,
                                               Counter before, Counter after);
    protected abstract void explode_effect();

    @Override
    public void update(){
        if(before_explode_counter.time_up()){ this.exploded = true; }
        if(this.exploded)
            explode_effect();
        if(this.exploded && this.num_smallBomb < this.explode_range){
            this.num_smallBomb++;
            add_smallBomb();
        }
        if(this.after_explode_counter.time_up())
            this.world.removeSprite(this);
    }

    public void onDamaged(Rectangle r, int i){}
    public void render(Graphics g){ this.renderer.render(g, this.owner.bomb_image); }
}
