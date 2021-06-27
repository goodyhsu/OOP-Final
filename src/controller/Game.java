package controller;

import map.Map;
import model.Counter;
import model.Direction;
import model.World;
import player.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Game extends GameLoop {
    private Player p1;
    private Player p2;
    private final World world;
    private final ArrayList<map.Map> maps;
    private Map map;
    private ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(0, 0));
    private int winner = -1;

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
    protected Map getMap(){
        return map;
    }

    @Override
    protected void setMapAndWorld(int round) {
        world.reset();
        map = maps.get((round-1)%maps.size());
        world.setMap(map);
        map.setWorld(world);
        map.setMap();   // obstacles & ...
    }

    @Override
    public ArrayList<Integer> getScores() {
        return scores;
    }

    @Override
    protected int getWinner() {
        return winner;
    }

    private void setWinner() {
        if (p1.isAlive() && !p2.isAlive()) {
            scores.set(0, scores.get(0)+1);
            winner = 0;   // player 1 wins
        }
        else if (!p1.isAlive() && p2.isAlive()) {
            scores.set(1, scores.get(1)+1);
            winner = 1;   // player 2 wins
        }
        else {
            scores.set(0, scores.get(0)+1);
            scores.set(1, scores.get(1)+1);
            winner = -1;  // Tie
        }
    }

    @Override
    protected boolean isOver(Counter counter) {
        boolean over = false;
        if (!p1.isAlive() || !p2.isAlive() || counter.time_up()) {
            over = true;
            setWinner();
        }
        return over;
    }


}
