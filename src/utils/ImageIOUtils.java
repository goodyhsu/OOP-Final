package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageIOUtils {
    public static Image getImage(String dir, String filename) {
        File f = new File(dir, filename);
        if (f == null) {
            System.out.println("Image not found :((");
        }
        Image image;
        try {
            image = javax.imageio.ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
