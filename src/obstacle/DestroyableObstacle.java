package obstacle;

import model.SpriteCoordinate;

import java.awt.*;
import java.io.File;

public abstract class DestroyableObstacle extends Obstacle{
    public DestroyableObstacle(File file, SpriteCoordinate coordinate){ super(file, coordinate); }

    @Override
    public void damaged(int i) {
        world.removeSprite(this);
    }
}
