package controller;

import imageRenderer.ImageRenderer;
import model.World;
import utils.MusicUtils;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class GameLoop {
    private View view;
    private ImageRenderer gameRenderer;
    protected final MusicUtils musicUtils = new MusicUtils();

    public void setGamRenderer(ImageRenderer renderer){ this.gameRenderer = renderer; }
    public ImageRenderer getGameRenderer() {
        return gameRenderer;
    }
    public void setView(View view) {
        this.view = view;
    }
    public abstract void start();
    protected View getView() { return view; }
    public abstract World getWorld();
    public MusicUtils getMusicUtils() { return musicUtils; }

    protected void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface View {
        void render(GameLoop game);
    }

    protected abstract void gameLoop();
}
