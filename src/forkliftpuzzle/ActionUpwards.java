package forkliftpuzzle;

import agent.Action;

/**
 * Created by rick_sanchez on 18/05/2017.
 */
public class ActionUpwards extends Action<ForkliftPuzzleState> {
    public ActionUpwards(int idPeça) {super(1, idPeça);}

@Override
    public void execute(ForkliftPuzzleState state) {
        state.moveUpwards(id);
        state.setAction(this);
    }

@Override
    public boolean isValid(ForkliftPuzzleState state) {
        return state.canMoveUpwards(id);
    }
}
