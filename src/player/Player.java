package player;

import fsm.FiniteStateMachine;
import model.Direction;
import model.HealthPointSprite;
import model.SpriteShape;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static player.Player.Event.*;
import static model.Direction.LEFT;

public abstract class Player extends HealthPointSprite {
    public Image bomb_image;
    public Image smallBomb_image;

    public static final int HP = 10;
    public static int damage_area = 1;
    private final SpriteShape shape;
    public final FiniteStateMachine fsm;
    private final Set<Direction> directions = new CopyOnWriteArraySet<>();
    public static int damage;

    public enum Event {
        WALK, STOP, ATTACK, DAMAGED
    }

    public Player() {
        super(HP);

        shape = new SpriteShape(new Dimension(70, 80),
                new Dimension(10, 15), new Dimension(50, 50));

        fsm = new FiniteStateMachine();
    }

    public void attack() {
        fsm.trigger(ATTACK);
    }

    public int getDamage() {
        return damage;
    }

    public void move(Direction direction) {
        if (direction == LEFT || direction == Direction.RIGHT) {
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
}