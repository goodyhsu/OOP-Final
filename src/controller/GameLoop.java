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
        counter = new Counter(400);
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
            if (roundOver(counter))
                stop();
        }
        ((GameView.Canvas) view).roundOver();
        view.render(getWorld(), counter);
    }

    protected abstract World getWorld();

    public void stop() {
        running = false;
        counter.stopCounter();
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract boolean roundOver(Counter counter);

    public interface View {
        void render(World world, Counter counter);
    }
}
