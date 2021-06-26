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
import map.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Music Test
        MusicUtils musicUtils = new MusicUtils();
        musicUtils.playMusic("music/bgm/map2.wav");

        // Map
        ArrayList<String> obstacle_list = new ArrayList<String>();
        ArrayList<String> obstacle_img_list = new ArrayList<String>();
        obstacle_list.add("Wood");
        obstacle_list.add("Stone");
        obstacle_img_list.add("sprites/obstacle/wood/0.png");
        obstacle_img_list.add("sprites/obstacle/stone/0.png");
        ArrayList<String> items = new ArrayList<String>();
        items.addAll(Arrays.asList("DamageUp", "Explode_rangeUp", "IncreaseBomb_num", "IncreaseHP", "SpeedUp", "Star"));

        Map map1 = new Map("maps/backgrounds/0.png", "maps/files/2.txt", "None",
                obstacle_img_list, items);
        Map map = map1; // Preferred map can be chosen
        World world = new World(map);
        map.setWorld(world);

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
