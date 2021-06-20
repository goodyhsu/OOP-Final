package bomb;

import model.Counter;
import model.Direction;
import player.Player;

import java.awt.*;

public abstract class SmallBomb extends Bomb{
    Direction face;
    public SmallBomb(Player owner, Point location, int damage, int explode_range,
                     Direction face, Counter after_counter){
        super(owner, location, damage, explode_range);
        this.exploded = true;
        this.face = face;
        this.renderer = new SmallBombImageRenderer(this);
        this.after_explode_counter = after_counter;
    }

    public Direction getFace() { return this.face; }

    @Override
    public void update() {
        if (before_explode_counter.time_up()) {
            this.exploded = true;
        }
        if (this.exploded)
            explode_effect();
        if (this.exploded && this.num_smallBomb < this.explode_range) {
            this.num_smallBomb++;
            add_smallBomb(this.num_smallBomb);
        }
        if (this.after_explode_counter.time_up()) {
            this.world.removeSprite(this);
        }
    }

    @Override
    public void render(Graphics g){ this.renderer.render(this.owner.smallBomb_image, g); }
}
