package forkliftpuzzle;

import agent.Action;

/**
 * Created by rick_sanchez on 18/05/2017.
 */
public class ActionDownwards extends Action<ForkliftPuzzleState> {
    public ActionDownwards(int idPeça) {
        super(1, idPeça);
    }

    @Override
    public void execute(ForkliftPuzzleState state) {
        state.moveDownwards(id);
        state.setAction(this);
    }

    @Override
    public boolean isValid(ForkliftPuzzleState state) {
        return state.canMoveDownwards(id);
    }
}