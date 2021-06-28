package model;

import imageRenderer.GraphicsRenderer;

import java.awt.*;

public class WorldRenderer extends GraphicsRenderer{
    World world;
    public WorldRenderer(Graphics g, World world){
        super(g);
        this.world = world;
    }

    @Override
    public void gRender() {
        // background
        this.world.getMap().render(g);
        // sprites
        for (Sprite sprite : this.world.getSprites()) {
            sprite.render(g);
        }
    }
}
