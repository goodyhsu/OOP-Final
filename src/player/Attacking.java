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
            effectDamage();
        }
    }

    private Rectangle damageArea() {
        return player.getArea(new Dimension(10, 15),
                new Dimension(50, 50));
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Rectangle damageArea = damageArea();
        g.setColor(Color.BLUE);
        g.drawRect(damageArea.x, damageArea.y, damageArea.width, damageArea.height);
    }

    private void effectDamage() {
        World world = player.getWorld();
        Bomb bomb = new NormalBomb(player, player.getLocation(), player.getDamage(), player.getDamageArea());
        world.setBomb(bomb);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        stateMachine.reset();
    }
}