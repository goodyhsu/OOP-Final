package player;

import bomb.Bomb;
import bomb.NormalBomb;
import fsm.FiniteStateMachine;

import model.Direction;
import model.HealthPointSprite;
import model.SpriteShape;
import model.GameWorld;
import utils.MusicUtils;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static player.Player.Event.*;

public abstract class Player extends HealthPointSprite {
    public Image bomb_image;
    public Image smallBomb_image;

    protected SpriteShape shape;
    protected final FiniteStateMachine fsm;
    private final Set<Direction> directions = new CopyOnWriteArraySet<>();

    private int index;
    private int damage_area;
    private int damage;
    protected int speed;
    private int num_bomb_max;
    private int num_bomb_current;

    private final MusicUtils musicUtils = new MusicUtils();
    String music_file;

    protected enum Event {
        WALK, STOP, ATTACK, DAMAGED
    }

    public Player(Point location, int index) {
        super(500);
        this.location = location;
        this.index = index;
        damage_area = 1;
        damage = 2;
        num_bomb_max = 1;
        num_bomb_current = 0;

        shape = new SpriteShape(new Dimension(50, 50),
                new Dimension(6, 6), new Dimension(36, 36));

        fsm = new FiniteStateMachine();
    }

    public int getIndex() {return index;}
    public int getDamage() {
        return damage;
    }
    public int getNum_bomb_current(){ return num_bomb_current; }
    public void setNum_bomb_current(int num){ num_bomb_current = num; }
    public int getSpeed() {return speed;}

    // for items
    public void addDamage() {damage += 1;}
    public void addDamageArea() {damage_area += 1;}
    public void addSpeed() {speed += 2;}
    public void addNum_bomb_max(){num_bomb_max += 1;}

    public void attack() {
        if (num_bomb_current < num_bomb_max) {
            fsm.trigger(ATTACK);
            GameWorld world = (GameWorld) getWorld();
            Bomb bomb = new NormalBomb(this, this.getLocation(), this.getDamage(), this.getDamageArea());
            world.setBomb(bomb);
            setNum_bomb_current(num_bomb_current + 1);
        }
    }

    public void damaged() {
        musicUtils.playMusic(music_file, false, false, false);
        fsm.trigger(DAMAGED);
    }

    public void move(Direction direction) {
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            face = direction;
        }
        if (!directions.contains(direction)) {
            this.directions.add(direction);
            fsm.trigger(WALK);
        }
    }

    public void stop(Direction direction) {
        directions.remove(direction);
        if (directions.isEmpty()) {
            fsm.trigger(STOP);
        }
    }

    public void update() {
        if (this.isStar && this.star_counter.time_up())
            this.endStar();
        fsm.update();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        fsm.render(g);
    }

    public Set<Direction> getDirections() {
        return directions;
    }

    @Override
    public Point getLocation() {
        return location;
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

    public int getDamageArea() {return damage_area;}

    public int getHP() {return this.hpBar.getHP();}
}