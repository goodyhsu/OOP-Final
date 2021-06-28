package monster;

import fsm.FiniteStateMachine;
import model.Direction;
import model.Sprite;
import model.SpriteShape;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class Monster extends Sprite{

    protected SpriteShape shape;
    public final FiniteStateMachine fsm;

    public Monster(){
        this.shape = new SpriteShape(new Dimension(50, 50),
                new Dimension(5, 5), new Dimension(40, 40));
        fsm = new FiniteStateMachine();
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

