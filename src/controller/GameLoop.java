package controller;

import map.Map;
import model.CharacterSelector;
import model.Counter;
import model.SpriteCoordinate;
import model.World;
import utils.MusicUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class GameLoop {
    private View view;
    private Counter counter = null;
    private int total_round;
    private int round = 1;

    private final CharacterSelector char_selector = new CharacterSelector(this);
    private final GameRenderer gameRenderer = new GameRenderer(this);
    private final GameRound gameRound = new GameRound(this);
    private final MusicUtils musicUtils = new MusicUtils();

    public enum Status{selecting, instructions, start, in_progress, over, wait}
    Status status = Status.selecting;

    public void setView(View view) {
        this.view = view;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void start(int total_round) {
        this.total_round = total_round;
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
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
        setStatus(GameLoop.Status.selecting);
        getChar_selector().reset(round);
        while (getStatus() != GameLoop.Status.wait) {
            getView().render(this);
            delay(15);
        }
    }

    public abstract ArrayList<Integer> getScores();

    protected abstract int getWinner();

    protected abstract boolean isOver(Counter counter);

    protected View getView() { return view; }

    public Counter getCounter() {
        return counter;
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    public Status getStatus() {
        return status;
    }

    public abstract World getWorld();

    protected abstract Map getMap();

    protected abstract void setMapAndWorld(int round);

    public void changeCharacter(int player, int change) {
        char_selector.changeCharacter(player, change);
    }

    protected CharacterSelector getChar_selector() {
        return char_selector;
    }

    protected ArrayList<SpriteCoordinate> getPlayerCoordinates() {
        return getMap().getPlayerCoordinates();
    }

    public MusicUtils getMusicUtils() { return musicUtils; }

    protected void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface View {
        void render(GameLoop game);
    }
}
