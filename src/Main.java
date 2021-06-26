import bomb.BombCollisionHandler;
import controller.Game;
import item.ItemCollisionHandler;
import model.Sprite;
import model.World;
import model.characterSelector;
import player.Dog;
import player.Cat;
import player.PlayerCollisionHandler;
import utils.MusicUtils;
import views.GameView;
import utils.MusicUtils;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Music Test
        MusicUtils musicUtils = new MusicUtils();
        musicUtils.playMusic("music/bgm/map2.wav");

        //world
        ArrayList<String> obstacle_list = new ArrayList<String>();
        ArrayList<String> obstacle_img_list = new ArrayList<String>();
        obstacle_list.add("Wood");
        obstacle_list.add("Stone");
        obstacle_img_list.add("sprites/obstacle/wood/0.png");
        obstacle_img_list.add("sprites/obstacle/stone/0.png");

        World world = new World(obstacle_img_list);

        //controller
        characterSelector char_selector = new characterSelector();
        Game game = new Game(world, char_selector);
//        game.setPlayer(p1, p2);

        //view
        GameView view = new GameView(game);


//        world.setObstacles(view, obstacle_list, obstacle_img_list);

        //game start
        game.start(5);

        // view
        view.launch();
    }
}
