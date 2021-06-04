package obstacle;

import java.awt.*;

public class ObstacleImageRenderer implements ImageRenderer{
    private Obstacle obstacle;
    public ObstacleImageRenderer(Obstacle obstacle){ this.obstacle = obstacle; }
    @Override
    void render(Graphics g, Image image) {
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
