package model;

import player.HealthPointBar;
import player.Player;

import java.awt.*;

public abstract class HealthPointSprite extends Sprite {
    protected HealthPointBar hpBar;
    protected boolean isStar;
    protected Counter star_counter;
    private Player owner;

    public HealthPointSprite(int hp) {
        this.hpBar = new HealthPointBar(hp);
        hpBar.setOwner(this);
        this.isStar = false;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public void damaged(int value) {
        if (this.isStar)
            return;
        owner.damaged();
        hpBar.damaged(value);
        if (hpBar.isDead()) {
            world.removeSprite(this);
        }
    }

    public void healed(int value) {
        hpBar.healed(value);
    }

    public void setStar() {
        this.isStar = true;
        this.star_counter = new Counter(10000/15);
    }

    public void endStar() {
        this.isStar = false;
    }

    public boolean isStar() {
        return this.isStar;
    }

    @Override
    public void render(Graphics g) {
        hpBar.render(g);
    }

}
