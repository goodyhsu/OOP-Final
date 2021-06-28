package monster;

import fsm.FiniteStateMachine;
import model.Direction;
import model.Sprite;
import model.SpriteCoordinate;
import model.SpriteShape;

import java.awt.*;
import java.util.Random;

import static monster.Monster.Event.*;
import static utils.LocationUtils.coordinateToLocation;

public abstract class Monster extends Sprite {

    protected SpriteCoordinate coordinate;

    private final int speed = 5;
    private int event_count = getEventCount();
    protected SpriteShape shape;
    public final FiniteStateMachine fsm;
    private Event now_event = WALK;
    private Direction now_direction = Direction.LEFT;
    private MonsterCollisionHandler collisionHandler = new MonsterCollisionHandler();

    public enum Event {
        WALK, STOP
    }

    public Monster(SpriteCoordinate coordinate) {
        this.coordinate = coordinate;
        location = coordinateToLocation(coordinate);
        shape = new SpriteShape(new Dimension(50, 50),
                new Dimension(5, 5), new Dimension(40, 40));
        fsm = new FiniteStateMachine();
    }

    private void move(Direction direction) {

        Dimension offset = now_direction.translate(speed);
        boolean collision = collisionHandler.isCollision(this, offset);
        System.out.print(collision);
        if (collision) {
            now_direction = Direction.values()[new Random().nextInt(Direction.values().length)];
        } else {
            if (direction == Direction.LEFT || direction == Direction.RIGHT)
                face = direction;
            fsm.trigger(WALK);
        }
    }

    private void stop() {
        fsm.trigger(STOP);
    }

    @Override
    public void update() {
        event_count--;
        switch(now_event) {
            case STOP:
                stop();
            case WALK:
                move(now_direction);
        }
        fsm.update();
        if (event_count < 0) {
            event_count = getEventCount();
            now_event = Event.values()[(now_event.ordinal()+1) % 2];
        }
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

    private int getEventCount() {
        return (new Random().nextInt(7) + 3);
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

