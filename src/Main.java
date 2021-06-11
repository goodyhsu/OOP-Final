import controller.Game;
import model.World;
import player.Player;
import views.GameView;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // players
        Player p1 = new Player(1, new Point(0, 0));
        Player p2 = new Player(1, new Point(300, 300));

        //world
        World world = new World(p1, p2);

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
