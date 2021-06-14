package player;

import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;

import java.awt.*;

import static fsm.FiniteStateMachine.Transition.from;
import static player.Player.Event.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class Dog extends Player {

    public Dog(int damage, Point location) {

        this.damage = damage;
        this.location = location;
        bomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bone/0.png");
        smallBomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bone/0.png");

        ImageRenderer imageRenderer = new PlayerImageRenderer(this);
        State idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("sprites/dog/idle", imageRenderer)));
        State walking = new WaitingPerFrame(2,
                new Walking(this, imageStatesFromFolder("sprites/dog/walk", imageRenderer)));
        State attacking = new WaitingPerFrame(3,
                new Attacking(this, fsm, imageStatesFromFolder("sprites/dog/attack", imageRenderer)));
        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(walking).when(STOP).to(idle));
        fsm.addTransition(from(idle).when(ATTACK).to(attacking));
        fsm.addTransition(from(walking).when(ATTACK).to(attacking));
    }


}
