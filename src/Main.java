import controller.Game;
import model.World;
import player.Player;
import views.GameView;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // players
        Player p1 = new Dog(100, new Point(0, 0));
        Player p2 = new Dog(150, new Point(300, 300));

        //world
        World world = new World(p1, p2);

        //controller
        Game game = new Game(world, p1, p2);
        //view
        GameView view = new GameView(game);

        // world - set Obstacles
        world.setObstacles(view);

        //game start
        game.start();
        //view launch
    }
}