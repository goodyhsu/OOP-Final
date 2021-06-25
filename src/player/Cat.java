package player;

import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;

import java.awt.*;

import static fsm.FiniteStateMachine.Transition.from;
import static player.Player.Event.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class Cat extends Player {

    public Cat(Point location) {

        this.location = location;
        speed = 8;
        bomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bomb/fish/0.png");
        smallBomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bomb/smallFish/0.png");

        ImageRenderer imageRenderer = new PlayerImageRenderer(this);
        State idle = new WaitingPerFrame(5,
                new Idle(imageStatesFromFolder("sprites/player/cat/idle", imageRenderer)));
        State walking = new WaitingPerFrame(3,
                new Walking(this, imageStatesFromFolder("sprites/player/cat/walk", imageRenderer)));
        State attacking = new WaitingPerFrame(3,
                new Attacking(this, fsm, imageStatesFromFolder("sprites/player/cat/attack", imageRenderer)));
        State damaged = new WaitingPerFrame(4,
                new Damaged(this, fsm, imageStatesFromFolder("sprites/player/cat/damaged", imageRenderer)));

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
