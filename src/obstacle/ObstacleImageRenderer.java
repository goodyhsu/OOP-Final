package obstacle;

import fsm.ImageRenderer;

import java.awt.*;

public class ObstacleImageRenderer implements ImageRenderer{
    protected Obstacle obstacle;
    public ObstacleImageRenderer(Obstacle obstacle){ this.obstacle = obstacle; }

    @Override
    public void render(Image image, Graphics g) {
        Rectangle range = this.obstacle.getRange();
        Rectangle body = this.obstacle.getBody();
        g.drawImage(image, range.x, range.y, range.width, range.height, null);
        g.setColor(Color.red);
        g.drawRect(body.x, body.y, body.width, body.height);
        g.drawRect(range.x, range.y, range.width, range.height);
    }
}

