package controller;

import model.Counter;
import model.Sprite;
import player.Player;

public class GameRound {
    GameLoop gameLoop;
    private boolean running;

    public GameRound(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    protected void nextRound(int r, Counter counter) {
        running = false;
        roundStart();
        int last_update_items_time = 0;
        while (running) {
            last_update_items_time = updateItems(last_update_items_time);
            gameLoop.getWorld().update();
            gameLoop.getView().render(gameLoop);
            gameLoop.delay(15);
            if (gameLoop.isOver(counter)) {
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



    private void roundStart() {
        gameLoop.getChar_selector().setPlayers((Game)gameLoop, gameLoop.getWorld());
        gameLoop.getWorld().getMap().setMap();
        gameLoop.setStatus(GameLoop.Status.start);
        gameLoop.getView().render(gameLoop);
        gameLoop.delay(1000);    // view: Game start
        gameLoop.getCounter().start();
        running = true;
    }

    public void roundOver() {
        gameLoop.setStatus(GameLoop.Status.over);
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
            gameLoop.delay(15);
        }
    }
}
