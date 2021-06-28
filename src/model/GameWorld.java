package model;

import bomb.BombCollisionHandler;
import controller.Game;
import player.PlayerCollisionHandler;

import java.awt.*;

public class GameWorld extends World{
    private final PlayerCollisionHandler playerCollisionHandler;
    private final BombCollisionHandler bombCollisionHandler;

    public GameWorld(){
        this.playerCollisionHandler = new PlayerCollisionHandler();
        this.bombCollisionHandler = new BombCollisionHandler();
        this.map = null;
    }

    @Override
    public void move(Sprite player, Dimension offset) {
        PlayerCollisionHandler collisionHandler = getPlayerCollisionHandler();
        boolean collision = collisionHandler.isCollision(player, offset);
        if (!collision)
            player.getLocation().translate(offset.width, offset.height);
    }

    public boolean setBomb(Sprite bomb) {
        if (getBombCollisionHandler().isCollision(bomb, bomb.getBodyOffset())) {
            return false;
        }
        addSprite(bomb);
        return true;
    }

    public PlayerCollisionHandler getPlayerCollisionHandler() {
        return playerCollisionHandler;
    }

    public BombCollisionHandler getBombCollisionHandler() {
        return bombCollisionHandler;
    }
}
