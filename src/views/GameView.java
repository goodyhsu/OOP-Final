package views;

import controller.Game;
import controller.GameLoop;
import controller.GameRenderer;
import imageRenderer.ImageRenderer;
import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameView extends JFrame {
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1050;
    public static final int P1 = 1;
    public static final int P2 = 2;
    private final Canvas canvas = new Canvas();
    private Game game;

    // Need to define the size of a BLOCK
    public static final int BLOCK_HEIGHT = 50;
    public static final int BLOCK_WIDTH = 50;

    public GameView(Game game) throws HeadlessException {
        this.game = game;
        canvas.gameLoop = game;
        game.setView(canvas);
    }

    public void launch() {
        // GUI Stuff
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvas);
        setSize(WIDTH+75, HEIGHT+75);
        setContentPane(canvas);
        setVisible(true);

        // Keyboard listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.movePlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.movePlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.movePlayer(P1, Direction.LEFT);
                        else if (game.getStatus() == Game.Status.selecting)
                            game.changeCharacter(0, -1);
                        break;
                    case KeyEvent.VK_D:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.movePlayer(P1, Direction.RIGHT);
                        else if (game.getStatus() == Game.Status.selecting)
                            game.changeCharacter(0, 1);
                        break;
                    case KeyEvent.VK_E:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.attack(P1);
                        break;
                    case KeyEvent.VK_I:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.movePlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.movePlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.movePlayer(P2, Direction.LEFT);
                        else if (game.getStatus() == Game.Status.selecting)
                            game.changeCharacter(1, -1);
                        break;
                    case KeyEvent.VK_L:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.movePlayer(P2, Direction.RIGHT);
                        else if (game.getStatus() == Game.Status.selecting)
                            game.changeCharacter(1, 1);
                        break;
                    case KeyEvent.VK_U:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.attack(P2);
                        break;
                    case KeyEvent.VK_ENTER:
                        if (game.getStatus() == Game.Status.selecting)
                            game.setStatus(Game.Status.wait);
                    case KeyEvent.VK_H:
                        if (game.getStatus() == Game.Status.selecting) {
                            game.setStatus(Game.Status.instructions);
                        }
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        if (game.getStatus() == Game.Status.instructions)
                            game.setStatus(Game.Status.selecting);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.stopPlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.stopPlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.stopPlayer(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.stopPlayer(P1, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_I:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.stopPlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.stopPlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.stopPlayer(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        if (game.getStatus() == Game.Status.in_progress)
                            game.stopPlayer(P2, Direction.RIGHT);
                        break;
                }
            }
        });
    }

    public static class Canvas extends JPanel implements GameLoop.View {
        GameLoop gameLoop;

        @Override
        public void render(GameLoop gameLoop) {
            this.gameLoop = gameLoop;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);

            // set world renderer
            World world = gameLoop.getWorld();
            ImageRenderer world_renderer = new WorldRenderer(g, world);
            world.setRenderer(world_renderer);
            // set game renderer
            gameLoop.setGamRenderer(new GameRenderer(g, gameLoop));

            // Now, let's paint
            if (gameLoop != null)
                gameLoop.getGameRenderer().render();
        }
    }

}
