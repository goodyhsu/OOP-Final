package player;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;

import java.util.List;

public class Damaged extends Sequence {
    private final Player player;
    private final StateMachine stateMachine;

    public Damaged(Player player, StateMachine stateMachine, List<? extends State> states) {
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