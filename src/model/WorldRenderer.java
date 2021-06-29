package model;

import controller.Game;
import imageRenderer.GraphicsRenderer;
import views.GameView;

import java.awt.*;

import static views.GameView.HEIGHT;
import static views.GameView.WIDTH;

public class WorldRenderer extends GraphicsRenderer{
    World world;
    public WorldRenderer(Graphics g, World world){
        super(g);
        this.world = world;
    }

    @Override
    public void gRender() {
        // background
        Rectangle range = new Rectangle(0, 0, WIDTH, HEIGHT);
        g.drawImage(this.world.getMap().getBackground_image(), range.x, range.y, range.width, range.height, null);

        // grids
        Game game = (Game) world.getGame();
        if (game.getStatus() == Game.Status.in_progress)
            drawGrids(g);

        // sprites
        for (Sprite sprite : this.world.getSprites()) {
            sprite.render(g);
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
}
