package forkliftpuzzle;

import agent.Action;

/**
 * Created by rick_sanchez on 13/05/2017.
 */
public class ActionForward extends Action<ForkliftPuzzleState> {
    public ActionForward() {
        super(1);
    }

    @Override
    public void execute(ForkliftPuzzleState state) {
        state.moveForward();
        state.setAction(this);
    }

    @Override
    public boolean isValid(ForkliftPuzzleState state) {
        return state.canMoveForward();
    }
}
