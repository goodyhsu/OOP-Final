package monster;

import fsm.CyclicSequence;
import fsm.ImageState;
import model.Direction;

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
            for (Direction direction : monster.getDirections()) {
                monster.getWorld().move(monster, direction.translate(monster.getSpeed()));
            }
        }
    }

    @Override
    public String toString() {
        return "Walking";
    }
}