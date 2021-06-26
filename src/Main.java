import controller.Game;
import model.World;
import utils.MusicUtils;
import views.GameView;
import map.Map;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Music Test
        MusicUtils musicUtils = new MusicUtils();
        musicUtils.playMusic("music/bgm/map1.wav");

        // Map
        ArrayList<String> obstacle_img_list = new ArrayList<>();
        obstacle_img_list.add("sprites/obstacle/wood/0.png");
        obstacle_img_list.add("sprites/obstacle/stone/0.png");
        ArrayList<String> items = new ArrayList<>(Arrays.asList("DamageUp",
                "Explode_rangeUp", "IncreaseBomb_num", "IncreaseHP", "SpeedUp", "Star"));

        ArrayList<map.Map> maps= new ArrayList<>();
        Map map0 = new Map("maps/backgrounds/0.png", "maps/files/0.txt", "None",
                obstacle_img_list, items);
        Map map1 = new Map("maps/backgrounds/1.png", "maps/files/1.txt", "None",
                obstacle_img_list, items);
        maps.addAll(Arrays.asList(map0, map1));
//        Map map = map0; // Preferred map can be chosen
        World world = new World();
//        map.setWorld(world);

        // Game
        Game game = new Game(world, maps);

        //view
        GameView view = new GameView(game);
        //game start
        game.start(5);

        // view
        view.launch();
    }
}
