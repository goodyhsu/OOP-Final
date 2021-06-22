package item;

import model.Sprite;
import model.SpriteCoordinate;
import model.SpriteShape;
import player.Player;
import static utils.LocationUtils.coordinateToLocation;

import java.awt.*;

public abstract class Item extends Sprite {
    protected SpriteCoordinate coordinate;
    private SpriteShape shape;
    Image image;
    ItemImageRenderer renderer;
    Player owner;
    public Item(SpriteCoordinate coordinate, Player owner){
        this.coordinate = coordinate;
        this.location = coordinateToLocation(coordinate);
        this.renderer = new ItemImageRenderer(this);
        this.shape = new SpriteShape(new Dimension(75, 75),
                new Dimension(5, 5), new Dimension(60, 60));
        this.owner = owner;
    }

    public abstract void triggerEffect();
    public abstract void endEffect();

    @Override
    public void render(Graphics g){ this.renderer.render(this.image, g); }

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