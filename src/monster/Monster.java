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

    private final int speed = 4;
    private final int damage = 3;
    protected SpriteShape shape;
    public final FiniteStateMachine fsm;
    private Direction now_direction = getRandomDirection();

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
        Dimension offset = now_direction.translate(getSpeed());
        MonsterCollisionHandler collisionHandler = new MonsterCollisionHandler();
        boolean collision = collisionHandler.isCollision(this, offset);
        if (collision) {
            now_direction = getRandomDirection();
            stop();
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
        move(now_direction);
        fsm.update();
    }

    @Override
    public void render(Graphics g){
        fsm.render(g);
    }

    @Override
    public void damaged(int value) {
        this.getWorld().removeSprite(this);
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return now_direction;
    }

    public int getDamage() {
        return damage;
    }

    private Direction getRandomDirection() {
        return Direction.values()[new Random().nextInt(Direction.values().length)];
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

