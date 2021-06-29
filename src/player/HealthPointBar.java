package player;

import model.HealthPointSprite;
import model.Sprite;

import java.awt.*;

    public class HealthPointBar extends Sprite {
        private final int maxHp;
        private Sprite owner;
        private int hp;

        public HealthPointBar(int hp) {
            this.maxHp = this.hp = hp;
        }

        public void setOwner(Sprite owner) {
            this.owner = owner;
        }

        @Override
        public void update() {
        }

        @Override
        public void render(Graphics g) {
            Rectangle range = getRange();
            int width = (int) (hp * owner.getRange().getWidth() / maxHp);
            g.setColor(new Color(134, 112, 112, 255));
            g.fillRect(range.x - 1, range.y - 1, (int) owner.getRange().getWidth() + 2, range.height + 2);
            if (((HealthPointSprite) owner).isStar())
                g.setColor(new Color(232, 214, 108));
            else if (((Player) owner).getIndex() == 0)
                g.setColor(new Color(105, 192, 192));
            else
                g.setColor(new Color(183, 176, 247, 255));
            g.fillRect(range.x, range.y, width, range.height);
        }

        @Override
        public void damaged(int value) {
            this.hp = Math.max(hp - value, 0);
        }

        public void healed(int value) {
            this.hp = Math.min(hp + value, maxHp);
        }

        public int getHP() {
            return hp;
        }

        @Override
        public Rectangle getRange() {
            return new Rectangle(owner.getX(), owner.getY() - 10, (int) owner.getRange().getWidth(), 7);
        }

        @Override
        public Dimension getBodyOffset() {
            return new Dimension();
        }

        @Override
        public Dimension getBodySize() {
            return new Dimension();
        }

        public boolean isDead() {
            return hp <= 0;
        }
    }
