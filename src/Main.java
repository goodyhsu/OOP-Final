import bomb.BombCollisionHandler;
import controller.Game;
import model.World;
import player.Dog;
import player.Cat;
import player.PlayerCollisionHandler;
import views.GameView;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // players
        Dog p1 = new Dog(1, new Point(0, 0));
        Cat p2 = new Cat(1, new Point(300, 300));

        // CollisionHandler
        PlayerCollisionHandler playerCollisionHandler = new PlayerCollisionHandler();
        BombCollisionHandler bombCollisionHandler = new BombCollisionHandler();

        //world
        World world = new World(playerCollisionHandler, bombCollisionHandler, p1, p2);

        //controller
        Game game = new Game(world, p1, p2);
        //view
        GameView view = new GameView(game);

        // world - set Obstacles
        ArrayList<String> obstacle_list = new ArrayList<String>();
        obstacle_list.add("Wood");
        obstacle_list.add("Stone");
        world.setObstacles(view, obstacle_list);

        //game start
        game.start();
        view.launch();
    }
}
