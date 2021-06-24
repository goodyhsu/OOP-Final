package controller;

import model.Counter;
import model.Direction;
import model.World;
import model.characterSelector;
import player.Player;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Game extends GameLoop {
    private Player p1;
    private Player p2;
    private final World world;
    private final characterSelector char_selector;

    public Game(World world, characterSelector char_selector) {
        this.world = world;
        this.char_selector = char_selector;
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
    protected boolean isOver(Counter counter) {
        boolean over = false;
        if (!p1.isAlive() || !p2.isAlive() || counter.time_up()) {
            over = true;
        }
        return over;
    }

    public void changeCharacter(int player, int change) {
        char_selector.changeCharacter(player, change);
    }

    @Override
    protected characterSelector getChar_selector() {
        return char_selector;
    }
}
