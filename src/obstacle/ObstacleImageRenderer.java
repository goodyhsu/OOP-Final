package obstacle;

import fsm.ImageRenderer;

import java.awt.*;

public class ObstacleImageRenderer implements ImageRenderer{
    protected Obstacle obstacle;
    public ObstacleImageRenderer(Obstacle obstacle){ this.obstacle = obstacle; }

    @Override
    public void render(Image image, Graphics g) {
        Rectangle range = this.obstacle.getRange();
        g.drawImage(image, range.x, range.y, range.width, range.height, null);
    }
}

