package controller;

import model.Counter;
import model.Sprite;
import model.World;
import player.Player;
import views.GameView;

public class GameRound {
    GameLoop gameLoop;
    private boolean running;

    public GameRound(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    protected void nextRound(int r, Counter counter) {
        running = false;
        selectCharacter(r);
        roundStart();
        int last_update_items_time = 0;
        while (running) {
            last_update_items_time = updateItems(last_update_items_time);
            World world = gameLoop.getWorld();
            world.update();
            gameLoop.getView().render(gameLoop);
            delay(15);
            if (gameLoop.isOver(counter)) {
                gameLoop.roundOver();
                roundOver();
            }
        }
    }

    private int updateItems(int last_update_items_time) {
        Counter counter = gameLoop.getCounter();
        if (counter.getCurrent_time() - last_update_items_time >= (2*60*1000/15) || last_update_items_time == 0) {
            gameLoop.getWorld().getMap().setItems(5);
            last_update_items_time = counter.getCurrent_time() + 1;
        }
        return last_update_items_time;
    }

    private void selectCharacter(int round) {
        gameLoop.setStatus(GameLoop.Status.selecting);
        gameLoop.getChar_selector().reset(round);
        while (gameLoop.getStatus() != GameLoop.Status.wait) {
            gameLoop.getView().render(gameLoop);
            delay(15);
        }
    }

    private void roundStart() {
        gameLoop.getChar_selector().setPlayers((Game)gameLoop, gameLoop.getWorld());
        gameLoop.getWorld().getMap().setMap();
        gameLoop.roundStart();
        gameLoop.getView().render(gameLoop);
        delay(1000);    // view: Game start
        gameLoop.getCounter().start();
        running = true;
    }

    public void roundOver() {
        running = false;
        gameLoop.getCounter().stopCounter();
        for (Sprite sprite : gameLoop.getWorld().getSprites()) {
            if (!(sprite instanceof Player))
                gameLoop.getWorld().removeSprite(sprite);
        }

        int over_loop = (int) (5 / 0.015);
        while (over_loop > 0) {
            over_loop--;
            gameLoop.getView().render(gameLoop);
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
}
