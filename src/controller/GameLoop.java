package controller;

import model.Counter;
import model.Sprite;
import model.World;
import model.characterSelector;
import player.Player;
import views.GameView;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class GameLoop {
    private View view;
    private Counter counter = null;
    private int total_round;
    private final GameRenderer gameRenderer = new GameRenderer(this);
    private final GameRound gameRound = new GameRound(this);
    public enum Status{selecting, instructions, start, in_progress, over, wait};
    Status status;


    public void setView(View view) {
        this.view = view;
    }
    protected View getView() {return view;}

    public void start(int total_round) {
        this.total_round = total_round;
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        int r = 1;
        while (r <= total_round) {
            counter = new Counter(40000, false);
            gameRound.nextRound(r++, counter);
        }
        return;
    }


    protected abstract World getWorld();
    protected abstract characterSelector getChar_selector();

    public Counter getCounter() {
        return counter;
    }

    protected abstract boolean isOver(Counter counter);

    public interface View {
        void render(GameLoop game);
    }
    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    protected void roundStart() {
        setStatus(Status.start);
    }

    protected void roundOver() {
        setStatus(Status.over);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
