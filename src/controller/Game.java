package controller;

import model.Direction;
import model.World;
import player.Player;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Game extends GameLoop {
    private final Player p1;
    private final Player p2;
    private final World world;

    public Game(World world, Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.world = world;
    }

    public void movePlayer(int playerNumber, Direction direction) {
        getPlayer(playerNumber).move(direction);
    }

    public void stopPlayer(int playerNumber, Direction direction) {
        getPlayer(playerNumber).stop(direction);
    }

    public void attack(int playerNumber) {
        getPlayer(playerNumber).attack();
    }

    public Player getPlayer(int playerNumber) {
        return playerNumber == 1 ? p1 : p2;
    }

    @Override
    protected World getWorld() {
        return world;
    }
}
