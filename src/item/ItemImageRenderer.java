package item;

import fsm.ImageRenderer;

import java.awt.*;

public class ItemImageRenderer implements ImageRenderer {
    protected Item item;
    public ItemImageRenderer(Item item){ this.item = item; }

    @Override
    public void render(Image image, Graphics g) {
        Rectangle range = this.item.getRange();
        Rectangle body = this.item.getBody();
        g.drawImage(image, range.x, range.y, range.width, range.height, null);
        g.setColor(Color.red);
        g.drawRect(body.x, body.y, body.width, body.height);
        g.drawRect(range.x, range.y, range.width, range.height);
    }
}
