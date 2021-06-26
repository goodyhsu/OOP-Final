package controller;

import model.Counter;
import model.World;
import model.characterSelector;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class GameLoop {
    private View view;
    private Counter counter = null;
    private int total_round;

    private final characterSelector char_selector = new characterSelector();
    private final GameRenderer gameRenderer = new GameRenderer(this);
    private final GameRound gameRound = new GameRound(this);

    public enum Status{selecting, instructions, start, in_progress, over, wait};
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
        int r = 1;
        while (r <= total_round) {
            selectCharacter(r);
            counter = new Counter(40000, false);
            gameRound.nextRound(r++, counter);
        }
        return;
    }

    private void selectCharacter(int round) {
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

    public void changeCharacter(int player, int change) {
        char_selector.changeCharacter(player, change);
    }

    protected characterSelector getChar_selector() {
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
