package monster;

import fsm.CyclicSequence;
import fsm.ImageState;

import java.awt.*;
import java.util.List;

public class Walking extends CyclicSequence {
    private final Monster monster;

    public Walking(Monster monster, List<ImageState> states) {
        super(states);
        this.monster = monster;
    }

    @Override
    public void update() {
        if (monster.isAlive()) {
            super.update();
            Dimension offset = monster.getDirection().translate(monster.getSpeed());
            monster.getLocation().translate(offset.width, offset.height);
        }
    }

    @Override
    public String toString() {
        return "Walking";
    }
}