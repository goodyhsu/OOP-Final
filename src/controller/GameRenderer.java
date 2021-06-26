package controller;

import model.Counter;
import model.World;
import views.GameView;

import java.awt.*;

import static utils.renderUtils.drawString;

public class GameRenderer {
    GameLoop gameLoop;
    public GameRenderer(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void render(Graphics g) {
        if (gameLoop.getStatus() == Game.Status.over){
            drawOver(g, gameLoop.getWorld());
        }
        else if (gameLoop.getStatus() == Game.Status.start) {
            drawStart(g, gameLoop.getWorld());
            gameLoop.setStatus(Game.Status.in_progress);
        }
        else if (gameLoop.getStatus() == Game.Status.in_progress) {
            // world
            gameLoop.getWorld().render(g); // ask the world to paint itself and paint the sprites on the canvas
            // grids
            drawGrids(g);
            // timer
            if (gameLoop.getCounter() != null)
                drawTimer(g, gameLoop.getCounter());
        }
        else if (gameLoop.getStatus() == Game.Status.selecting){
            gameLoop.getChar_selector().render(g);
        }
        else if (gameLoop.getStatus() == Game.Status.instructions) {
            gameLoop.getChar_selector().renderInstructions(g);
        }
    }

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

    private void drawTimer(Graphics g, Counter counter) {
        int left_time = (int) ((counter.getTime_limit() - counter.getCurrent_time())*0.015);
        drawString(g, Integer.toString(left_time), Color.WHITE,
                new Font("TimesRoman", Font.PLAIN, 32), GameView.WIDTH/2, 50);
    }

    private void drawStart(Graphics g, World world) {
        world.render(g);
        drawTimer(g, gameLoop.getCounter());
        drawString(g, "Game Start!", Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 64), GameView.WIDTH/2-150, GameView.HEIGHT/2);
    }

    private void drawOver(Graphics g, World world) {
        world.render(g);
        drawString(g, "Game Over", Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 64), GameView.WIDTH/2-150, GameView.HEIGHT/2);
    }
}
