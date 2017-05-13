package forkliftpuzzle;

import agent.Action;

/**
 * Created by rick_sanchez on 13/05/2017.
 */
public class ActionBackwards extends Action<ForkliftPuzzleState> {
    public ActionBackwards() {
        super(1);
    }

    @Override
    public void execute(ForkliftPuzzleState state) {
        state.moveBackwards();
        state.setAction(this);
    }

    @Override
    public boolean isValid(ForkliftPuzzleState state) {
        return state.canMoveBackwards();
    }
}
