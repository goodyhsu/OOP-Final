package controller;

import model.Counter;
import model.World;
import views.GameView;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class GameLoop {
    private boolean running;
    private View view;
    private Counter counter;

    public void setView(View view) {
        this.view = view;
    }

    public void start() {
        new Thread(this::gameLoop).start();
        counter = new Counter(40000);
    }

    private void gameLoop() {
        int total_r = 1;
        int r = 1;
        while (r <= total_r) {
            round(r++);
        }
        return;
    }

    private void round(int r) {
        ((GameView.Canvas) view).roundStart();
        running = true;
        while (running) {
            World world = getWorld();
            world.update();
            view.render(world, counter);
            delay(15);
            if (isOver(counter)) {
                ((GameView.Canvas) view).roundOver();
                roundOver();
            }
        }
    }

    protected abstract World getWorld();

    public void roundOver() {
        running = false;
        counter.stopCounter();
        int over_loop = (int) (5 / 0.015);
        while (over_loop > 0) {
            over_loop--;
            view.render(getWorld(), counter);
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
        void render(World world, Counter counter);
    }
}
