package monster;

import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import model.SpriteCoordinate;

import static monster.Monster.Event.*;
import static fsm.FiniteStateMachine.Transition.from;
import static utils.ImageStateUtils.imageStatesFromFolder;

public class Ghost extends Monster {

    public Ghost (SpriteCoordinate coordinate) {
        super(coordinate);
        ImageRenderer imageRenderer = new MonsterImageRenderer(this);

        String dir = "sprites/ghost";
        State idle = new WaitingPerFrame(15,
                new Idle(imageStatesFromFolder(dir + "/default", imageRenderer)));
        State walking = new WaitingPerFrame(10,
                new Walking(this, imageStatesFromFolder( dir + "/default", imageRenderer)));

        fsm.setInitialState(walking);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(walking).when(STOP).to(idle));
    }
}
