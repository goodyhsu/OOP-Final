package player;

import fsm.CyclicSequence;
import fsm.ImageState;
import model.Direction;

import java.util.List;

public class Walking extends CyclicSequence {
    private final Player player;

    public Walking(Player player, List<ImageState> states) {
        super(states);
        this.player = player;
    }

    @Override
    public void update() {
        if (player.isAlive()) {
            super.update();
            for (Direction direction : player.getDirections()) {
                player.getWorld().move(player, direction.translate());
            }
        }
    }

    @Override
    public String toString() {
        return "Walking";
    }
}