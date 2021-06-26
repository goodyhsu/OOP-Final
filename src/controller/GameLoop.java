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
    private boolean running;
    private View view;
    private Counter counter = null;
    private int total_round;

    public void setView(View view) {
        this.view = view;
    }

    public void start(int total_round) {
        this.total_round = total_round;
        new Thread(this::gameLoop).start();
//        counter = new Counter(40000);
    }

    private void gameLoop() {
        int r = 1;
        while (r <= total_round) {
            nextRound(r++);
        }
        return;
    }

    private void nextRound(int r) {
        selectCharacter(r);
        counter = new Counter(40000);
        ((GameView.Canvas) view).roundStart();
        running = true;
        int last_update_items_time = 0;
        while (running) {
            last_update_items_time = updateItems(last_update_items_time);
            World world = getWorld();
            world.update();
            view.render(world, counter, getChar_selector());
            delay(15);
            if (isOver(counter)) {
                ((GameView.Canvas) view).roundOver();
                roundOver();
            }
        }
    }

    private int updateItems(int last_update_items_time) {
        if (counter.getCurrent_time() - last_update_items_time >= (2*60*1000/15) || last_update_items_time == 0) {
            getWorld().getMap().setItems(5);
            last_update_items_time = counter.getCurrent_time() + 1;
        }
        return last_update_items_time;
    }

    private void selectCharacter(int round) {
        ((GameView.Canvas) view).setSelecting(true);
        getChar_selector().reset(round);
        while (((GameView.Canvas) view).getSelecting()) {
            view.render(getWorld(), counter, getChar_selector());
            delay(15);
        }
        getChar_selector().setPlayers((Game)this, getWorld());
        getWorld().getMap().setMap();
    }

    protected abstract World getWorld();
    protected abstract characterSelector getChar_selector();

    public void roundOver() {
        running = false;
        counter.stopCounter();
        for (Sprite sprite : getWorld().getSprites()) {
            if (!(sprite instanceof Player))
                getWorld().removeSprite(sprite);
        }

        int over_loop = (int) (5 / 0.015);
        while (over_loop > 0) {
            over_loop--;
            view.render(getWorld(), counter, getChar_selector());
            delay(15);
        }
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract boolean isOver(Counter counter);

    public interface View {
        void render(World world, Counter counter, characterSelector char_selector);
    }
}
