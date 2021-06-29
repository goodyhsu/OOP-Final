package controller;

import map.GameMap;
import model.*;
import player.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Game extends GameLoop {
    private Counter counter = null;
    private int round = 1;
    private final CharacterSelector char_selector = new CharacterSelector(this);
    private final GameRound gameRound = new GameRound(this);
    public enum Status{selecting, instructions, start, in_progress, over, wait}
    public Status status = Status.selecting;

    private Player p1;
    private Player p2;
    private final GameWorld world;
    private final ArrayList<GameMap> gameMaps;
    private GameMap gameMap;
    private ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(0, 0));
    private int winner = -1;

    public Game(GameWorld world, ArrayList<GameMap> gameMaps) {
        this.world = world;
        this.gameMaps = gameMaps;
        this.world.setGame(this);
    }

    @Override
    public void start() {
        new Thread(this::gameLoop).start();
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
    public World getWorld() {
        return world;
    }

    protected GameMap getMap(){
        return gameMap;
    }

    protected void setMapAndWorld(int round) {
        world.reset();
        gameMap = gameMaps.get((round-1)% gameMaps.size());
        world.setMap(gameMap);
        gameMap.setWorld(world);
        gameMap.setMap();   // obstacles & ...
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

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

    protected boolean isOver(Counter counter) {
        boolean over = false;
        if (!p1.isAlive() || !p2.isAlive() || counter.time_up()) {
            over = true;
            setWinner();
        }
        return over;
    }

    @Override
    protected void gameLoop() {
        while (true) {
            counter = new Counter(300000 / 15, false);
            selectCharacter();
            setMapAndWorld(round);
            gameRound.nextRound(counter);
            int winner = getWinner();
            if (winner != -1)
                getChar_selector().unlockTom(winner);
            round++;
        }
    }

    private void selectCharacter() {
        musicUtils.playMusic("music/bgm/default.wav", true, true, true);
        setStatus(Status.selecting);
        getChar_selector().reset(round);
        while (getStatus() != Status.wait) {
            getView().render(this);
            delay(15);
        }
    }

    public Counter getCounter() {
        return counter;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public void changeCharacter(int player, int change) {
        char_selector.changeCharacter(player, change);
    }

    protected CharacterSelector getChar_selector() {
        return char_selector;
    }

    protected ArrayList<SpriteCoordinate> getPlayerCoordinates() {
        return getMap().getPlayerCoordinates();
    }
}
