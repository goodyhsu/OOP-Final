package controller;

import imageRenderer.GraphicsRenderer;
import model.Counter;
import views.GameView;

import java.awt.*;

import static utils.renderUtils.drawString;

public class GameRenderer extends GraphicsRenderer {
    Game game;
    public GameRenderer(Graphics g, GameLoop gameLoop) {
        super(g);
        this.game = (Game) gameLoop;
    }

    @Override
    public void gRender() {
        if (game.getStatus() == Game.Status.over){
            drawOver(g);
        }
        else if (game.getStatus() == Game.Status.start) {
            drawStart(g);
            game.setStatus(Game.Status.in_progress);
        }
        else if (game.getStatus() == Game.Status.in_progress) {
            drawAll(g);
        }
        else if (game.getStatus() == Game.Status.selecting || game.getStatus() == Game.Status.instructions){
            game.getChar_selector().render(g);
        }
    }

    private void drawAll(Graphics g) {
        // world
        game.getWorld().render(); // ask the world to paint itself and paint the sprites on the canvas
        // timer
        if (game.getCounter() != null)
            drawTimer(g, game.getCounter());
    }

    private void drawTimer(Graphics g, Counter counter) {
        int left_time = (int) ((counter.getTime_limit() - counter.getCurrent_time())*0.015);
        Font font = new Font("TimesRoman", Font.PLAIN, 32);
        int string_width = g.getFontMetrics(font).stringWidth(Integer.toString(left_time));
        int string_height = g.getFontMetrics(font).getHeight();
        g.setColor(Color.WHITE);
        g.fillRoundRect((int)(GameView.WIDTH/2 - string_width/2)-5, 5, string_width+10, string_height+2, 15, 15);
        drawString(g, Integer.toString(left_time), Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 32), GameView.WIDTH/2, 40, true);
    }

    private void drawStart(Graphics g) {
        drawAll(g);
        drawString(g, "Game Start!", Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 64), GameView.WIDTH/2, GameView.HEIGHT/2, true);
    }

    private void drawOver(Graphics g) {
        game.getWorld().render();
        int winner = game.getWinner();
        String string;
        if (winner != -1)
            string = "Player" + (winner+1) + " wins!";
        else
            string = "Ties";
        drawString(g, string, Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 64), GameView.WIDTH/2, GameView.HEIGHT/2, true);
        if (winner != -1) {
            int height = g.getFontMetrics().getHeight();
            string = "You can play Ugly Tom now : )";
            drawString(g, string, Color.BLACK,
                    new Font("TimesRoman", Font.PLAIN, 32), GameView.WIDTH/2, GameView.HEIGHT/2 + height + 5, true);
        }
    }
}
