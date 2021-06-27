package player;

import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import model.SpriteShape;

import java.awt.*;

import static fsm.FiniteStateMachine.Transition.from;
import static player.Player.Event.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class Tom extends PlayerType {

    public Tom(Point location, int index, int type) {
        super(location, index, type);

        this.shape = new SpriteShape(new Dimension(50, 72),
                new Dimension(7, 14), new Dimension(36, 36));
        this.speed = 12;
        bomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bomb/seagull/0.png");
        smallBomb_image = Toolkit.getDefaultToolkit().getImage("sprites/bomb/seagullEgg/0.png");
        music_file = "music/SE/tom.wav";

        ImageRenderer imageRenderer = new PlayerImageRenderer(this);

        String dir = "sprites/player/tom" + Integer.toString(type);
        State idle = new WaitingPerFrame(5,
                new Idle(imageStatesFromFolder(dir + "/idle", imageRenderer)));
        State walking = new WaitingPerFrame(3,
                new Walking(this, imageStatesFromFolder(dir + "/walk", imageRenderer)));
        State attacking = new WaitingPerFrame(3,
                new Attacking(this, fsm, imageStatesFromFolder(dir + "/attack", imageRenderer)));
        State damaged = new WaitingPerFrame(4,
                new Damaged(this, fsm, imageStatesFromFolder(dir + "/damaged", imageRenderer)));

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
