package player;

import bomb.NormalBomb;
import bomb.NormalSmallBomb;
import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import bomb.Bomb;
import model.World;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Attacking extends Sequence {
    private final Player player;
    private final StateMachine stateMachine;
    private final Set<Integer> damagingStateNumbers = new HashSet<>(List.of(6));

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