package views;

import controller.Game;
import controller.GameLoop;
import model.Direction;
//import model.Sprite;
import model.World;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

public class GameView extends JFrame {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 1000;
    public static final int P1 = 1;
    public static final int P2 = 2;
    private final Canvas canvas = new Canvas();
    private final Game game;

    // Need to define the size of a BLOCK
    public static final int BLOCK_HEIGHT = 75;
    public static final int BLOCK_WIDTH = 75;

    public GameView(Game game) throws HeadlessException {
        this.game = game;
        game.setView(canvas);
    }

    public void launch() {
        // GUI Stuff
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvas);
        setSize(WIDTH, HEIGHT);
        setContentPane(canvas);
        setVisible(true);

        // Keyboard listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game.movePlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        game.movePlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        game.movePlayer(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.movePlayer(P1, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_E:
                        game.attack(P1);
                        break;
                    case KeyEvent.VK_I:
                        game.movePlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        game.movePlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        game.movePlayer(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        game.movePlayer(P2, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_U:
                        game.attack(P2);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game.stopPlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        game.stopPlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        game.stopPlayer(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.stopPlayer(P1, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_I:
                        game.stopPlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        game.stopPlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        game.stopPlayer(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        game.stopPlayer(P2, Direction.RIGHT);
                        break;
                }
            }
        });
    }

    public static class Canvas extends JPanel implements GameLoop.View {
        private World world;

        @Override
        public void render(World world) {
            this.world = world;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            // Now, let's paint
            g.setColor(Color.WHITE); // paint background with all white
            g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);

            // grids
            int line_w = 2;
            g.setColor(Color.BLACK);
            for (int x = 0; x < GameView.WIDTH; x += GameView.BLOCK_WIDTH) {
                g.fillRect(x, 0, line_w, GameView.HEIGHT);
            }

            for (int y = 0; y < GameView.WIDTH; y += GameView.BLOCK_HEIGHT) {
                g.fillRect(0, y, GameView.WIDTH, line_w);
            }
            
            world.render(g); // ask the world to paint itself and paint the sprites on the canvas
        }
    }

}