import bomb.BombCollisionHandler;
import controller.Game;
import item.ItemCollisionHandler;
import model.Sprite;
import model.World;
import model.characterSelector;
import player.Dog;
import player.Cat;
import player.PlayerCollisionHandler;
import views.GameView;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // players
//        Dog p1 = new Dog(new Point(75, 75));
//        Cat p2 = new Cat(new Point(900, 525));

        //world
        // world - set Obstacles
        ArrayList<String> obstacle_list = new ArrayList<String>();
        ArrayList<String> obstacle_img_list = new ArrayList<String>();
        obstacle_list.add("Wood");
        obstacle_list.add("Stone");
        obstacle_img_list.add("sprites/obstacle/wood/0.png");
        obstacle_img_list.add("sprites/obstacle/stone/0.png");

        World world = new World(obstacle_img_list);
//        world.addSprites(p1, p2);

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
