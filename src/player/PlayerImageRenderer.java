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
        Rectangle body = player.getBody();
        if (face == Direction.LEFT) {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        } else {
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        }
        g.setColor(Color.RED);
        g.drawRect(body.x, body.y, body.width, body.height);
    }
}