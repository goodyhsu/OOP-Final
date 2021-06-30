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
    protected Image image;
    protected ItemImageRenderer renderer;
    protected Player owner;

    public Item(SpriteCoordinate coordinate){
        this.coordinate = coordinate;
        this.location = coordinateToLocation(coordinate);
        this.renderer = new ItemImageRenderer(this);
        this.shape = new SpriteShape(new Dimension(50, 50),
                new Dimension(5, 5), new Dimension(40, 40));
    }

    public abstract void effect();

    public void remove_item(){ this.owner.getWorld().removeSprite(this); }

    public void setOwner(Player player) {this.owner = player;}

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
