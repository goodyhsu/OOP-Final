import controller.Game;
<<<<<<< HEAD
import model.World;
import monster.Ghost;
import utils.MusicUtils;
=======
import model.GameWorld;
>>>>>>> c54f10e96fafec4e2a4e333d730ef5ea5cd7ec13
import views.GameView;
import map.GameMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Ghost Test
        //Ghost ghost = new Ghost(new Point(500, 500));

        // Map
        ArrayList<String> obstacle_img_list = new ArrayList<>();
        obstacle_img_list.add("sprites/obstacle/wood/0.png");
        obstacle_img_list.add("sprites/obstacle/stone/0.png");
        ArrayList<String> items = new ArrayList<>(Arrays.asList("DamageUp",
                "Explode_rangeUp", "IncreaseBomb_num", "IncreaseHP", "SpeedUp", "Star"));

        ArrayList<GameMap> maps= new ArrayList<>();
        GameMap gameMap0 = new GameMap("maps/backgrounds/0.png", "maps/files/0.txt", "music/bgm/map0.wav",
                obstacle_img_list, items);
        GameMap gameMap1 = new GameMap("maps/backgrounds/1.png", "maps/files/1.txt", "music/bgm/map1.wav",
                obstacle_img_list, items);
        GameMap gameMap2 = new GameMap("maps/backgrounds/2.png", "maps/files/2.txt", "music/bgm/map2.wav",
                obstacle_img_list, items);
        maps.addAll(Arrays.asList(gameMap0, gameMap1, gameMap2));
        GameWorld world = new GameWorld();

        // Game
        Game game = new Game(world, maps);

        //view
        GameView view = new GameView(game);
        //game start
        game.start();

        // view
        view.launch();
    }
}
