package player;

import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;

import java.awt.*;

import static fsm.FiniteStateMachine.Transition.from;
import static player.Player.Event.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class Dog extends Player {

    public Dog(Point location) {

        this.location = location;
        this.speed = 10;
        bomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bomb/bone/0.png");
        smallBomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bomb/smallBone/0.png");

        ImageRenderer imageRenderer = new PlayerImageRenderer(this);
        State idle = new WaitingPerFrame(5,
                new Idle(imageStatesFromFolder("sprites/player/dog/idle", imageRenderer)));
        State walking = new WaitingPerFrame(3,
                new Walking(this, imageStatesFromFolder("sprites/player/dog/walk", imageRenderer)));
        State attacking = new WaitingPerFrame(3,
                new Attacking(this, fsm, imageStatesFromFolder("sprites/player/dog/attack", imageRenderer)));
        //State damaged = new WaitingPerFrame(3,
        //        new Damaged(this, fsm, imageStatesFromFolder("sprite/player/cat/damaged", imageRenderer)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(walking).when(STOP).to(idle));
        fsm.addTransition(from(idle).when(ATTACK).to(attacking));
        fsm.addTransition(from(walking).when(ATTACK).to(attacking));
    }


}
