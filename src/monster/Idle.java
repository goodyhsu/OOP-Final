package monster;

import fsm.CyclicSequence;
import fsm.ImageState;

import java.util.List;

public class Idle extends CyclicSequence {
    public Idle(List<ImageState> states) {
        super(states);
    }

    @Override
    public String toString() {
        return "Idle";
    }
}