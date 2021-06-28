package monster;

import fsm.FiniteStateMachine;
import model.Direction;
import model.Sprite;
import model.SpriteCoordinate;
import model.SpriteShape;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static monster.Monster.Event.*;
import static utils.LocationUtils.coordinateToLocation;

public abstract class Monster extends Sprite{

    protected SpriteCoordinate coordinate;

    private final int speed = 5;
    protected SpriteShape shape;
    public final FiniteStateMachine fsm;
    private Event now_event;
    private Direction now_direction;

    public enum Event {
        WALK, STOP
    }

    public Monster(SpriteCoordinate coordinate) {
        this.coordinate = coordinate;
        this.location = coordinateToLocation(coordinate);
        this.shape = new SpriteShape(new Dimension(50, 50),
                new Dimension(5, 5), new Dimension(40, 40));
        fsm = new FiniteStateMachine();
    }

    private void move(Direction direction) {
        MonsterCollisionHandler collisionHandler = new MonsterCollisionHandler();
        Dimension offset = now_direction.translate(speed);
        boolean collision = collisionHandler.isCollision(this, offset);
        if (collision) {
            return;
        }

        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            face = direction;
        }
        fsm.trigger(WALK);
    }

    private void stop() {
        fsm.trigger(STOP);
    }

    @Override
    public void update() {
        //move(Direction.LEFT);
        fsm.update();
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

    public Direction getDirections() {
        return now_direction;
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

