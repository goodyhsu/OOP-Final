package obstacle;

import java.awt.*;
import java.io.File;

public abstract class DestroyableObstacle extends Obstacle{
    public DestroyableObstacle(File file){ super(file); }
    @Override
    public void onDamaged(Rectangle r, int i) {
        world.removeSprite(this);
    }
}
