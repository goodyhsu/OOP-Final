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
            drawOver(g);
        }
        else if (gameLoop.getStatus() == Game.Status.start) {
            drawStart(g);
            gameLoop.setStatus(Game.Status.in_progress);
        }
        else if (gameLoop.getStatus() == Game.Status.in_progress) {
            drawAll(g);
        }
        else if (gameLoop.getStatus() == Game.Status.selecting || gameLoop.getStatus() == Game.Status.instructions){
            gameLoop.getChar_selector().render(g);
        }
    }

    private void drawAll(Graphics g) {
        // world
        gameLoop.getWorld().render(g); // ask the world to paint itself and paint the sprites on the canvas
        // grids
        drawGrids(g);
        // timer
        if (gameLoop.getCounter() != null)
            drawTimer(g, gameLoop.getCounter());
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
        Font font = new Font("TimesRoman", Font.PLAIN, 32);
        int string_width = g.getFontMetrics(font).stringWidth(Integer.toString(left_time));
        int string_height = g.getFontMetrics(font).getHeight();
        g.setColor(Color.gray);
        g.fillRoundRect((int)(GameView.WIDTH/2 - string_width/2)-5, 5, string_width+10, string_height+2, 15, 15);
        drawString(g, Integer.toString(left_time), Color.WHITE,
                new Font("TimesRoman", Font.PLAIN, 32), GameView.WIDTH/2, 40, true);
    }

    private void drawStart(Graphics g) {
        drawAll(g);
        drawString(g, "Game Start!", Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 64), GameView.WIDTH/2, GameView.HEIGHT/2, true);
    }

    private void drawOver(Graphics g) {
        gameLoop.getWorld().render(g);
        int winner = gameLoop.getWinner();
        String string;
        if (winner != -1)
            string = "Player" + (winner+1) + " wins!";
        else
            string = "Ties ; )";
        drawString(g, string, Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 64), GameView.WIDTH/2, GameView.HEIGHT/2, true);
        int height = g.getFontMetrics().getHeight();
        string = "You can play Ugly Tom now : )";
        drawString(g, string, Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 32), GameView.WIDTH/2, GameView.HEIGHT/2 + height + 5, true);
    }
}
