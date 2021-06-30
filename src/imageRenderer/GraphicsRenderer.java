package imageRenderer;

import java.awt.*;

public abstract class GraphicsRenderer implements ImageRenderer{
    protected Graphics g;
    public GraphicsRenderer(Graphics g){ this.g = g; }

    public Graphics getG(){ return this.g; }
    public void setG(Graphics g){ this.g = g; }

    @Override
    public void render(){ gRender(); }
    public abstract void gRender();
}
