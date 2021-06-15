import bomb.BombCollisionHandler;
import bomb.BombImageRenderer;
import bomb.NormalBomb;
import controller.Game;
import fsm.ImageRenderer;
import model.World;
import player.Dog;
import player.Cat;
import player.PlayerCollisionHandler;
import player.PlayerImageRenderer;
import views.GameView;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // players
        Dog p1 = new Dog(1, new Point(75, 75));
        Cat p2 = new Cat(1, new Point(900, 450));

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
        ArrayList<String> obstacle_img_list = new ArrayList<String>();
        obstacle_list.add("Wood");
        obstacle_list.add("Stone");
        obstacle_img_list.add("sprites/wood/0.png");
        obstacle_img_list.add("sprites/stone/0.png");
        world.setObstacles(view, obstacle_list, obstacle_img_list);

        //game start
        game.start();
        view.launch();
    }
}
