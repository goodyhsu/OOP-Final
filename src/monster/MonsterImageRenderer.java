package monster;

import fsm.ImageRenderer;
import model.Direction;

import java.awt.*;

public class MonsterImageRenderer implements ImageRenderer{
    protected Monster monster;
    public MonsterImageRenderer(Monster monster){ this.monster = monster; }

    @Override
    public void render(Image image, Graphics g) {
        Direction face = monster.getFace();
        Rectangle range = monster.getRange();
        if (face == Direction.RIGHT) {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        } else {
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        }
    }
}

