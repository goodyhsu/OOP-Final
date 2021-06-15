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
    public void render(Graphics g){ this.renderer.render(this.owner.smallBomb_image, g); }
}
