package utils;

import java.awt.*;

public class renderUtils {
    public static void drawString(Graphics g, String string, Color color, Font font, int x, int y) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(string, x, y);
    }

    public static void drawImage(Graphics g, Image image, int x, int y, int width, int height) {
        float scale = 1;
        int img_w = (int) image.getWidth(null);
        scale = (float) width / img_w;

        int img_h = (int) image.getHeight(null);
        scale = Math.min(scale, (float) height / img_h);

        img_w = (int) (img_w * scale);
        img_h = (int) (img_h * scale);

        if (y != 0) {
            y = (int) (y - ((float)img_h - width) / 2);
        }
        g.drawImage(image, x + (int) ((width-img_w) / 2), y + (int) ((height-img_h)/2), img_w, img_h, null);
    }
}
