package controller;

import map.Map;
import model.Counter;
import model.Direction;
import model.World;
import player.Player;

import java.util.ArrayList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Game extends GameLoop {
    private Player p1;
    private Player p2;
    private final World world;
    private final ArrayList<map.Map> maps;

    public Game(World world, ArrayList<map.Map> maps) {
        this.world = world;
        this.maps = maps;
    }

    public void setPlayer(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
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

    @Override
    protected Map getMap(int round){
        return maps.get(round % maps.size());
    }

    @Override
    protected boolean isOver(Counter counter) {
        boolean over = false;
        if (!p1.isAlive() || !p2.isAlive() || counter.time_up()) {
            over = true;
        }
        return over;
    }
}
