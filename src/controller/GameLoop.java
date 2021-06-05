package controller;

import model.Counter;
import model.World;

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
        counter = new Counter(-1);
    }

    private void gameLoop() {
        running = true;
        while (running) {
            World world = getWorld();
            world.update();
            view.render(world);
            delay(15);
        }
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


    public interface View {
        void render(World world);
    }
}
