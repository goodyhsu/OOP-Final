package bomb;

import fsm.ImageRenderer;

import java.awt.*;

public class BombImageRenderer implements ImageRenderer {
    private Bomb bomb;
    public BombImageRenderer(){}
    public BombImageRenderer(Bomb bomb){ this.bomb = bomb; }

    @Override
    public void render(Image image, Graphics g) {
        Rectangle range = this.bomb.getRange();
        g.drawImage(image, range.x, range.y, range.width, range.height, null);

        g.setColor(Color.BLUE);
        g.drawRect(range.x, range.y, range.width, range.height);
    }
}
