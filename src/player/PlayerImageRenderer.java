package player;

import fsm.ImageRenderer;
import model.Direction;

import java.awt.*;

public class PlayerImageRenderer implements ImageRenderer {
    protected Player player;

    public PlayerImageRenderer(Player player) {
        this.player = player;
    }

    @Override
    public void render(Image image, Graphics g) {
        Direction face = player.getFace();
        Rectangle range = player.getRange();
        if (face == Direction.RIGHT) {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        } else {
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        }
    }
}