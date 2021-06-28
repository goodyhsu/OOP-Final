package monster;

import fsm.FiniteStateMachine;
import model.Direction;
import model.Sprite;
import model.SpriteShape;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static monster.Monster.Event.*;

public abstract class Monster extends Sprite{

    private final int speed = 5;
    protected SpriteShape shape;
    public final FiniteStateMachine fsm;
    private final Set<Direction> directions = new CopyOnWriteArraySet<>();

    public enum Event {
        WALK, STOP
    }

    public Monster(Point location) {
        this.shape = new SpriteShape(new Dimension(50, 50),
                new Dimension(5, 5), new Dimension(40, 40));
        fsm = new FiniteStateMachine();
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

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g){
        fsm.render(g);
    }

    @Override
    public void damaged(int i) {}

    public int getSpeed() {
        return speed;
    }

    public Set<Direction> getDirections() {
        return directions;
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
}

