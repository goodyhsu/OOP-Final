import controller.Game;

import map.AnimationMap;
import model.GameWorld;
import views.GameView;
import map.GameMap;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Map
        ArrayList<String> obstacle_img_list = new ArrayList<>();
        obstacle_img_list.add("sprites/obstacle/wood/0.png");
        obstacle_img_list.add("sprites/obstacle/stone/0.png");
        ArrayList<String> items = new ArrayList<>(Arrays.asList("IncreaseBomb_num", "DamageUp",
                "Explode_rangeUp", "IncreaseBomb_num", "IncreaseHP", "SpeedUp", "Star", "Explode_rangeUp"));

        ArrayList<GameMap> maps= new ArrayList<>();
        GameMap gameMap0 = new GameMap("maps/backgrounds/SoccerHen.png", "maps/files/0.txt", "music/bgm/map1.wav",
                obstacle_img_list, items);
        GameMap gameMap1 = new AnimationMap("maps/backgrounds/coolSea", "maps/files/1.txt", "music/bgm/map0.wav",
                obstacle_img_list, items, 40);
        GameMap gameMap2 = new GameMap("maps/backgrounds/Laputa.png", "maps/files/2.txt", "music/bgm/map2.wav",
                obstacle_img_list, items);
        GameMap gameMap3 = new GameMap("maps/backgrounds/snowfield.png", "maps/files/1.txt", "music/bgm/map1.wav",
                obstacle_img_list, items);
        GameMap gameMap4 = new GameMap("maps/backgrounds/mountain.png", "maps/files/0.txt", "music/bgm/map0.wav",
                obstacle_img_list, items);
        maps.addAll(Arrays.asList(gameMap0, gameMap1, gameMap2, gameMap3, gameMap4));
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
