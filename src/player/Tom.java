package player;

import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;

import java.awt.*;

import static fsm.FiniteStateMachine.Transition.from;
import static player.Player.Event.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class Tom extends Player {

    public Tom(Point location) {

        this.location = location;
        this.speed = 12;
        bomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bomb/seagull/0.png");
        smallBomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bomb/seagullEgg/0.png");

        ImageRenderer imageRenderer = new PlayerImageRenderer(this);
        State idle = new WaitingPerFrame(5,
                new Idle(imageStatesFromFolder("sprites/player/tom/idle", imageRenderer)));
        State walking = new WaitingPerFrame(3,
                new Walking(this, imageStatesFromFolder("sprites/player/tom/walk", imageRenderer)));
        State attacking = new WaitingPerFrame(3,
                new Attacking(this, fsm, imageStatesFromFolder("sprites/player/tom/attack", imageRenderer)));
        State damaged = new WaitingPerFrame(4,
                new Damaged(this, fsm, imageStatesFromFolder("sprites/player/tom/damaged", imageRenderer)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(walking).when(STOP).to(idle));
        fsm.addTransition(from(idle).when(ATTACK).to(attacking));
        fsm.addTransition(from(walking).when(ATTACK).to(attacking));
        fsm.addTransition(from(idle).when(DAMAGED).to(damaged));
        fsm.addTransition(from(damaged).when(WALK).to(walking));
        fsm.addTransition(from(damaged).when(ATTACK).to(attacking));

        setOwner(this);

    }

}
