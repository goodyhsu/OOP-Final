package obstacle;

import java.awt.*;
import java.io.File;

public class Stone extends Obstacle{
    public Stone(File file){ super(file); }
    @Override
    public void update(){}
    public void onDamaged(Rectangle r, int i){}
    //getRange()?
}
