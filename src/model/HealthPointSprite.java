package model;

import player.HealthPointBar;

import java.awt.*;

public abstract class HealthPointSprite extends Sprite {
    protected HealthPointBar hpBar;

    public HealthPointSprite(int hp) {
        this.hpBar = new HealthPointBar(hp);
        hpBar.setOwner(this);
    }

    @Override
    public void damaged(int value) {
        hpBar.damaged(value);
        if (hpBar.isDead()) {
            world.removeSprite(this);
        }
    }

    public void healed(int value) {
        hpBar.healed(value);
    }

    @Override
    public void render(Graphics g) {
        hpBar.render(g);
    }

}
