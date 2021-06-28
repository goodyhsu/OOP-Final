package model;

import imageRenderer.GraphicsRenderer;

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
        // sprites
        for (Sprite sprite : this.world.getSprites()) {
            sprite.render(g);
        }
    }
}
