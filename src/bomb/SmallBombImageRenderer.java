package bomb;

import model.Direction;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SmallBombImageRenderer extends BombImageRenderer{
    SmallBomb smallBomb;
    public SmallBombImageRenderer(SmallBomb smallBomb){
        this.smallBomb = smallBomb;
    }

    @Override
    public void render(Image image, Graphics g) {
        Direction face = this.smallBomb.getFace();
        Rectangle range = this.smallBomb.getRange();
        if (face == Direction.LEFT) {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        }
        else if(face == Direction.RIGHT){
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        }
        else if(face == Direction.UP){
            rotate_drawImage(-90, (Graphics2D) g, image, range.x, range.y, range.width, range.height);
        }
        else{
            rotate_drawImage(90, (Graphics2D) g, image, range.x, range.y, range.width, range.height);
        }
    }

    private void rotate_drawImage( int angle, Graphics2D g, Image image, int x, int y,
                                   int width, int height) {
        AffineTransform tr = new AffineTransform();
        tr.translate(x, y);
        tr.rotate(
                Math.toRadians(angle),
                width / 2,
                height / 2
        );
        g.drawImage(image, tr, null);
    }
}
