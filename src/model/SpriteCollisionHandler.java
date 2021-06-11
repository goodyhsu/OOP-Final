package model;


import obstacle.Obstacle;
import player.Player;

import java.awt.*;

public class SpriteCollisionHandler implements CollisionHandler {
    @Override
    public boolean checkCollision(Sprite from) {
        return false;
    }

    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {
//        if (from instanceof Player && (to instanceof Obstacle or to instanceof ))
    }
}

//        if (from instanceof Knight && to instanceof Knight) {
//        Rectangle body = from.getBody();
//        int offsetLeft = to.getX() - body.x;
//        int offsetRight = body.x + body.width - to.getX();
//        if (offsetLeft < 0) {
//        to.setLocation(new Point(to.getX() - (to.getRange().width + offsetLeft) / 3, to.getY()));
//        } else {
//        to.setLocation(new Point(to.getX() + offsetRight / 3, to.getY()));
//        }