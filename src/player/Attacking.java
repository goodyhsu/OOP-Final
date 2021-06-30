package player;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Attacking extends Sequence {
    private final Player player;
    private final StateMachine stateMachine;

    public Attacking(Player player, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.player = player;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (player.isAlive()) {
            super.update();
        }
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        stateMachine.reset();
    }
}