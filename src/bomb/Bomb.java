package bomb;

import model.*;
import player.Player;
import static utils.ImageStateUtils.readImage;
import static utils.LocationUtils.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public abstract class Bomb extends Sprite {
    protected final Player owner;
    protected final SpriteCoordinate coordinate;
    protected final int damage;
    protected final int explode_range;
    protected int num_smallBomb;
    protected final Counter before_explode_counter, after_explode_counter; // 5s, 2s
    protected boolean exploded;
    protected BombImageRenderer renderer;

    public static ArrayList<Bomb> bomb_list = new ArrayList<Bomb>();

    public Bomb(Player owner, Point owner_location, int damage, int explode_range){
        this.owner = owner;
        this.coordinate = location_to_coordinate(owner_location);
        this.location = coordinate_to_location(this.coordinate);
        this.damage = damage;
        this.explode_range = explode_range;
        this.num_smallBomb = 0;
        this.before_explode_counter = new Counter(5000/15);
        this.after_explode_counter = new Counter(2000/15);
        this.exploded = false;
        this.renderer = new BombImageRenderer(this);
    }

    protected abstract void add_smallBomb(int num_smallBomb);
    protected abstract SmallBomb new_smallBomb(Player owner, Point owner_location, int damage, int explode_range,
                                               Counter before, Counter after, Direction face);
    protected abstract void explode_effect();

    @Override
    public void update(){
        if(before_explode_counter.time_up()){ this.exploded = true; }
        if(this.exploded)
            explode_effect();
        if(this.exploded && this.num_smallBomb < this.explode_range){
            this.num_smallBomb++;
            add_smallBomb(this.num_smallBomb);
        }
        if(this.after_explode_counter.time_up())
            this.world.removeSprite(this);
    }

    @Override
    public void onDamaged(Rectangle r, int i){ this.exploded = true; }

    @Override
    public void render(Graphics g){ this.renderer.render(this.owner.bomb_image, g); }

    @Override
    public Point getLocation() {
        return location;
    }
}
