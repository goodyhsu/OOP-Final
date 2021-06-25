package views;

import controller.Game;
import controller.GameLoop;
import model.*;
//import model.Sprite;
import player.Player;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import javax.swing.*;
//import javax.swing.event.*;

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
                        if (!canvas.selecting)
                            game.movePlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        if (!canvas.selecting)
                            game.movePlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        if (!canvas.selecting)
                            game.movePlayer(P1, Direction.LEFT);
                        else
                            game.changeCharacter(0, -1);
                        break;
                    case KeyEvent.VK_D:
                        if (!canvas.selecting)
                            game.movePlayer(P1, Direction.RIGHT);
                        else
                            game.changeCharacter(0, 1);
                        break;
                    case KeyEvent.VK_E:
                        if (!canvas.selecting)
                            game.attack(P1);
                        break;
                    case KeyEvent.VK_I:
                        if (!canvas.selecting)
                            game.movePlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        if (!canvas.selecting)
                            game.movePlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        if (!canvas.selecting)
                            game.movePlayer(P2, Direction.LEFT);
                        else
                            game.changeCharacter(1, -1);
                        break;
                    case KeyEvent.VK_L:
                        if (!canvas.selecting)
                            game.movePlayer(P2, Direction.RIGHT);
                        else
                            game.changeCharacter(1, 1);
                        break;
                    case KeyEvent.VK_U:
                        if (!canvas.selecting)
                            game.attack(P2);
                        break;
                    case KeyEvent.VK_ENTER:
                        if (canvas.selecting)
                            canvas.setSelecting(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (!canvas.selecting)
                            game.stopPlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        if (!canvas.selecting)
                            game.stopPlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        if (!canvas.selecting)
                            game.stopPlayer(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        if (!canvas.selecting)
                            game.stopPlayer(P1, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_I:
                        if (!canvas.selecting)
                            game.stopPlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        if (!canvas.selecting)
                            game.stopPlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        if (!canvas.selecting)
                            game.stopPlayer(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        if (!canvas.selecting)
                            game.stopPlayer(P2, Direction.RIGHT);
                        break;
                }
            }
        });
    }

    public static class Canvas extends JPanel implements GameLoop.View {
        private World world;
        private boolean over;
        private Counter counter;
        private boolean selecting = true;
        private characterSelector char_selector;

        @Override
        public void render(World world, Counter counter, characterSelector char_selector) {
            this.world = world;
            this.counter = counter;
            this.char_selector = char_selector;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);

            // Now, let's paint
            g.setColor(Color.WHITE); // paint background with all white
            g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);


            if (over){
                for (Sprite sprite : world.getSprites()) {
                    if (sprite instanceof Player && sprite.isAlive()) {
                        sprite.update();
                        sprite.render(g);
                    }
                }
                drawOver(g);
            }
            else if (!selecting) {
                // world
                world.render(g); // ask the world to paint itself and paint the sprites on the canvas
                // grids
                drawGrids(g);
            }
            else {
                char_selector.render(g);
            }
            // timer
            drawTimer(g);
        }

        public void roundOver() {
            this.over = true;
        }

        public void roundStart() {
            this.over = false;
        }

        public void setSelecting(boolean selecting) {
            this.selecting = selecting;
            this.over = false;
        }
        public boolean getSelecting() { return selecting; }

        private void drawGrids(Graphics g) {
            int line_w = 1;
            g.setColor(Color.GRAY);
            for (int x = 0; x <= GameView.WIDTH; x += GameView.BLOCK_WIDTH) {
                g.fillRect(x, 0, line_w, GameView.HEIGHT+line_w);
            }
            for (int y = 0; y <= GameView.HEIGHT; y += GameView.BLOCK_HEIGHT) {
                g.fillRect(0, y, GameView.WIDTH+line_w, line_w);
            }
        }

        private void drawTimer(Graphics g) {
            g.setColor(Color.WHITE);
            int left_time = (int) ((counter.getTime_limit() - counter.getCurrent_time())*0.015);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.drawString(Integer.toString(left_time), GameView.WIDTH/2, 50);
        }

        private void drawOver(Graphics g) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 64));
            g.drawString("Game Over", GameView.WIDTH/2-150, GameView.HEIGHT/2);
        }
    }

}
