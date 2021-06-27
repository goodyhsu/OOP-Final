package bomb;

import model.*;
import obstacle.DestroyableObstacle;
import obstacle.Obstacle;
import player.Player;
import utils.MusicUtils;

import static utils.LocationUtils.*;

import java.awt.*;

public abstract class Bomb extends Sprite {
    protected final Player owner;
    protected final SpriteCoordinate coordinate;
    protected final int damage;
    protected final int explode_range;
    protected int num_smallBomb;
    protected Counter before_explode_counter, after_explode_counter; // 5s, 2s
    protected boolean exploded;
    protected BombImageRenderer renderer;
    protected boolean[] direction_stop;
    private final MusicUtils musicUtils = new MusicUtils();

    public Bomb(Player owner, Point owner_location, int damage, int explode_range){
        this.owner = owner;
        this.coordinate = locationToCoordinate(owner_location);
        this.location = coordinateToLocation(this.coordinate);
        this.damage = damage;
        this.explode_range = explode_range;
        this.num_smallBomb = 0;
        this.before_explode_counter = new Counter(3000/15, true);
        this.after_explode_counter = new Counter(4000/15, true);
        this.exploded = false;
        this.renderer = new BombImageRenderer(this);
    }

    protected abstract void add_smallBomb(int num_smallBomb, boolean[] direction_stop);
    protected abstract SmallBomb new_smallBomb(Player owner, Point owner_location, int damage, int explode_range,
                                               Counter before, Counter after, Direction face);
    protected abstract void explode_effect();

    @Override
    public void update(){
        if(before_explode_counter.time_up()){
            this.exploded = true;
            musicUtils.playMusic("music/SE/explosion.wav", false, false, true);
        }
        if(this.exploded)
            explode_effect();
        if(this.exploded && this.num_smallBomb < this.explode_range){
            this.num_smallBomb++;
            add_smallBomb(this.num_smallBomb, this.direction_stop);
        }
        if(this.after_explode_counter.time_up()) {
            this.world.removeSprite(this);
            this.owner.setNum_bomb_current(this.owner.getNum_bomb_current()-1);
        }
    }

    @Override
    public void damaged(int i){ this.exploded = true; }

    @Override
    public void render(Graphics g){ this.renderer.render(this.owner.bomb_image, g); }

    @Override
    public Point getLocation() {
        return location;
    }

    protected boolean[] isSmallBombCollision(SpriteCoordinate coordinate){
        Point location = coordinateToLocation(coordinate);
        Rectangle range = locationToRange(location);
        var sprites = this.world.getSprites(range);
        for(Sprite s: sprites)
            if(s instanceof Obstacle) {
                if (!(s instanceof DestroyableObstacle))
                    return new boolean[]{true, true};
                else
                    return new boolean[]{false, true};
            }
        return new boolean[]{false, false};
    }
}