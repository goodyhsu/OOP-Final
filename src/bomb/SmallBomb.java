package bomb;

import model.Counter;
import player.Player;

import java.awt.*;

public abstract class SmallBomb extends Bomb{
    public SmallBomb(Player owner, Point location, int damage, int explode_range,
                     Counter before, Counter after){
        super(owner, location, damage, explode_range, before, after);
        this.exploded = true;
    }

    @Override
    public void render(Graphics g){ this.renderer.render(this.owner.smallBomb_image, g); }
}
