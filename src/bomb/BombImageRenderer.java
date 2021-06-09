package bomb;

import fsm.ImageRenderer;

import java.awt.*;

public class BombImageRenderer implements ImageRenderer {
    private Bomb bomb;
    public BombImageRenderer(Bomb bomb){ this.bomb = bomb; }

    @Override
    public void render(Image image, Graphics g) {
        Rectangle range = this.bomb.getRange();
        g.drawImage(image, range.x, range.y, range.width, range.height, null);
    }
}
