package controller;

import model.Counter;
import model.Sprite;
import player.Player;
import utils.MusicUtils;

public class GameRound {
    Game game;
    private boolean running;

    public GameRound(Game game) {
        this.game = game;
    }

    protected void nextRound(Counter counter) {
        running = false;
        roundStart();
        int last_update_items_time = 0;
        while (running) {
            last_update_items_time = updateItems(last_update_items_time);
            game.getWorld().update();
            game.getView().render(game);
            game.delay(15);
            if (game.isOver(counter)) {
                running = false;
            }
        }
        roundOver();
    }

    private int updateItems(int last_update_items_time) {
        Counter counter = game.getCounter();
        if (counter.getCurrent_time() - last_update_items_time >= (2*60*1000/15) || last_update_items_time == 0) {
            game.getWorld().getMap().setItems(5);
            last_update_items_time = counter.getCurrent_time() + 1;
        }
        return last_update_items_time;
    }



    private void roundStart() {
        game.getChar_selector().setPlayers((Game)game, game.getWorld(), game.getPlayerCoordinates());
        game.setStatus(Game.Status.start);
        game.getView().render(game);
        game.delay(1000);    // view: Game start
        game.getCounter().start();

        // play music
        String music_file = game.getMap().getMusic_file();
        game.getMusicUtils().playMusic(music_file, true, true, true);

        running = true;
    }

    public void roundOver() {
        game.setStatus(Game.Status.over);
//        running = false;
        game.getCounter().stopCounter();
        game.getMusicUtils().playMusic("music/bgm/game_over.wav", false, true, true);
        for (Sprite sprite : game.getWorld().getSprites()) {
            if (!(sprite instanceof Player))
                game.getWorld().removeSprite(sprite);
        }

        int over_loop = (int) (6 / 0.015);
        while (over_loop > 0) {
            over_loop--;
            game.getWorld().update();
            game.getView().render(game);
            game.delay(15);
        }
    }
}
