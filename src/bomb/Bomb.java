package bomb;

import model.Sprite;
import player.Player;
import model.SpriteCoordinate;
import model.Counter;
import static utils.ImageStateUtils.readImage;

import java.awt.*;

public abstract class Bomb extends Sprite {
    final Player owner;
    final SpriteCoordinate coordinate;
    final int damage;
    final int explode_range;
    Image image;
    final Counter counter;
    final int exploding_time = 3000;

    public Bomb(Player owner, SpriteCoordinate coordinate, int damage, int explode_range){
        this.owner = owner;
        this.coordinate = coordinate;
        this.damage = damage;
        this.explode_range = explode_range;
        this.counter = new Counter();
    }

    public void explode(){ }

    @Override
    public void update(){}
}
