package forkliftpuzzle;

import agent.Action;

/**
 * Created by rick_sanchez on 13/05/2017.
 */
public class ActionForward extends Action<ForkliftPuzzleState> {
    public ActionForward(int idPeça) {
        super(1, idPeça);
    }

    @Override
    public void execute(ForkliftPuzzleState state) {
        state.moveForward(id);
        state.setAction(this);
    }

    @Override
    public boolean isValid(ForkliftPuzzleState state) {
        return state.canMoveForward(id);
    }
}
