package imageRenderer;

import java.awt.*;

public abstract class GraphicsRenderer implements ImageRenderer{
    public Graphics g;
    public GraphicsRenderer(Graphics g){ this.g = g; }

    @Override
    public void render(){ gRender(); }
    public abstract void gRender();
}
