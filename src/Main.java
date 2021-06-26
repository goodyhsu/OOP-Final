import controller.Game;
import model.World;
import model.characterSelector;
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
        ArrayList<String> obstacle_list = new ArrayList<>();
        ArrayList<String> obstacle_img_list = new ArrayList<>();
        obstacle_list.add("Wood");
        obstacle_list.add("Stone");
        obstacle_img_list.add("sprites/obstacle/wood/0.png");
        obstacle_img_list.add("sprites/obstacle/stone/0.png");
        ArrayList<String> items = new ArrayList<String>();
        items.addAll(Arrays.asList("DamageUp", "Explode_rangeUp", "IncreaseBomb_num", "IncreaseHP", "SpeedUp", "Star"));

        Map map0 = new Map("maps/backgrounds/0.png", "maps/files/0.txt", "None",
                obstacle_img_list, items);
        Map map1 = new Map("maps/backgrounds/1.png", "maps/files/1.txt", "None",
                obstacle_img_list, items);
        Map map = map1; // Preferred map can be chosen
        World world = new World(map);
        map.setWorld(world);

        //controller
        characterSelector char_selector = new characterSelector();
        Game game = new Game(world, char_selector);

        //view
        GameView view = new GameView(game);
        //game start
        game.start(5);

        // view
        view.launch();
    }
}
