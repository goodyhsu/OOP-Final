package views;

import controller.Game;
import controller.GameLoop;
import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static utils.renderUtils.drawString;


public class GameView extends JFrame {
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1050;
    public static final int P1 = 1;
    public static final int P2 = 2;
    private final Canvas canvas = new Canvas();
    private Game game;
    public enum Status{selecting, instructions, start, in_progress, over, wait};

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
                        if (canvas.status == Status.in_progress)
                            game.movePlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        if (canvas.status == Status.in_progress)
                            game.movePlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        if (canvas.status == Status.in_progress)
                            game.movePlayer(P1, Direction.LEFT);
                        else if (canvas.status == Status.selecting)
                            game.changeCharacter(0, -1);
                        break;
                    case KeyEvent.VK_D:
                        if (canvas.status == Status.in_progress)
                            game.movePlayer(P1, Direction.RIGHT);
                        else if (canvas.status == Status.selecting)
                            game.changeCharacter(0, 1);
                        break;
                    case KeyEvent.VK_E:
                        if (canvas.status == Status.in_progress)
                            game.attack(P1);
                        break;
                    case KeyEvent.VK_I:
                        if (canvas.status == Status.in_progress)
                            game.movePlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        if (canvas.status == Status.in_progress)
                            game.movePlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        if (canvas.status == Status.in_progress)
                            game.movePlayer(P2, Direction.LEFT);
                        else if (canvas.status == Status.selecting)
                            game.changeCharacter(1, -1);
                        break;
                    case KeyEvent.VK_L:
                        if (canvas.status == Status.in_progress)
                            game.movePlayer(P2, Direction.RIGHT);
                        else if (canvas.status == Status.selecting)
                            game.changeCharacter(1, 1);
                        break;
                    case KeyEvent.VK_U:
                        if (canvas.status == Status.in_progress)
                            game.attack(P2);
                        break;
                    case KeyEvent.VK_ENTER:
                        if (canvas.status == Status.selecting)
                            canvas.setStatus(Status.wait);
                    case KeyEvent.VK_H:
                        if (canvas.status == Status.selecting) {
                            canvas.setStatus(Status.instructions);
                        }
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        if (canvas.status == Status.instructions)
                            canvas.setStatus(Status.selecting);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (canvas.status == Status.in_progress)
                            game.stopPlayer(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        if (canvas.status == Status.in_progress)
                            game.stopPlayer(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        if (canvas.status == Status.in_progress)
                            game.stopPlayer(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        if (canvas.status == Status.in_progress)
                            game.stopPlayer(P1, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_I:
                        if (canvas.status == Status.in_progress)
                            game.stopPlayer(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        if (canvas.status == Status.in_progress)
                            game.stopPlayer(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        if (canvas.status == Status.in_progress)
                            game.stopPlayer(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        if (canvas.status == Status.in_progress)
                            game.stopPlayer(P2, Direction.RIGHT);
                        break;
                }
            }
        });
    }

    public static class Canvas extends JPanel implements GameLoop.View {
        private World world;
        private Counter counter;
        private characterSelector char_selector;
        private Status status = Status.selecting;

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

            if (status == Status.over){
                drawOver(g, world);
            }
            else if (status == Status.start) {
                drawStart(g, world);
                setStatus(Status.in_progress);
            }
            else if (status == Status.in_progress) {
                // world
                world.render(g); // ask the world to paint itself and paint the sprites on the canvas
                // grids
                drawGrids(g);
                // timer
                if (counter != null)
                    drawTimer(g);
            }
            else if (status == Status.selecting){
                char_selector.render(g);
            }
            else if (status == Status.instructions) {
                char_selector.renderInstructions(g);
            }
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public void roundOver() {
            setStatus(Status.over);
        }

        public void roundStart() {
            setStatus(Status.start);
        }

        public Status getStatus() { return status; }

        private void drawGrids(Graphics g) {
            int line_w = 1;
            g.setColor(Color.darkGray);
            for (int x = 0; x <= GameView.WIDTH; x += GameView.BLOCK_WIDTH) {
                g.fillRect(x, 0, line_w, GameView.HEIGHT+line_w);
            }
            for (int y = 0; y <= GameView.HEIGHT; y += GameView.BLOCK_HEIGHT) {
                g.fillRect(0, y, GameView.WIDTH+line_w, line_w);
            }
        }

        private void drawTimer(Graphics g) {
            int left_time = (int) ((counter.getTime_limit() - counter.getCurrent_time())*0.015);
            drawString(g, Integer.toString(left_time), Color.WHITE,
                    new Font("TimesRoman", Font.PLAIN, 32), GameView.WIDTH/2, 50);
        }

        private void drawStart(Graphics g, World world) {
            world.render(g);
            drawTimer(g);
            drawString(g, "Game Start!", Color.BLACK,
                    new Font("TimesRoman", Font.PLAIN, 64), GameView.WIDTH/2-150, GameView.HEIGHT/2);
        }

        private void drawOver(Graphics g, World world) {
            world.render(g);
            world.update();
            drawString(g, "Game Over", Color.BLACK,
                    new Font("TimesRoman", Font.PLAIN, 64), GameView.WIDTH/2-150, GameView.HEIGHT/2);
        }
    }

}
