package controller;

import map.Map;
import model.CharacterSelector;
import model.Counter;
import model.World;
import utils.MusicUtils;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class GameLoop {
    private View view;
    private Counter counter = null;
    private int total_round;

    private final CharacterSelector char_selector = new CharacterSelector();
    private final GameRenderer gameRenderer = new GameRenderer(this);
    private final GameRound gameRound = new GameRound(this);
    private final MusicUtils musicUtils = new MusicUtils();

    public enum Status{selecting, instructions, start, in_progress, over, wait}
    Status status;

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
        int round = 1;
        while (round <= total_round) {
            selectCharacter(round);
            setMapAndWorld(round);
            counter = new Counter(3000/15, false);
            gameRound.nextRound(counter);
            round++;
        }
    }

    private void setMapAndWorld(int round) {
        Map map = getMap(round-1);
        getWorld().setMap(map);
        map.setWorld(getWorld());
        musicUtils.playMusic(map.getMusic_file(), true, true);
    }

    private void selectCharacter(int round) {
        musicUtils.playMusic("music/bgm/default.wav", true, true);
        setStatus(GameLoop.Status.selecting);
        getChar_selector().reset(round);
        while (getStatus() != GameLoop.Status.wait) {
            getView().render(this);
            delay(15);
        }
    }

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

    protected abstract World getWorld();

    protected abstract Map getMap(int round);

    public void changeCharacter(int player, int change) {
        char_selector.changeCharacter(player, change);
    }

    protected CharacterSelector getChar_selector() {
        return char_selector;
    }

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
