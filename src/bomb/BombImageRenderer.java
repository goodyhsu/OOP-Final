package bomb;

import java.awt.*;

public class BombImageRenderer implements ImageRenderer{
    private Bomb bomb;
    public BombImageRenderer(Bomb bomb){ this.bomb = bomb; }

    @Override
    void render(Graphics g, Image image) {
        //Coordinate +- range

        /*Direction face = knight.getFace();
        Rectangle range = knight.getRange();
        Rectangle body = knight.getBody();
        if (face == Direction.LEFT) {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        } else {
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        }
        g.setColor(Color.RED);
        g.drawRect(body.x, body.y, body.width, body.height);*/
    }
}
