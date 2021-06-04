package obstacle;

import model.SpriteCoordinate;

import java.awt.*;
import java.io.File;

public class Stone extends Obstacle{
    public Stone(File file, SpriteCoordinate coordinate){ super(file, coordinate); }

    @Override
    public void update(){}
    //getRange()?
}
