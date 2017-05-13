package forkliftpuzzle;

import agent.Action;
import agent.Problem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static forkliftpuzzle.ForkliftPuzzleState.SIZE;

public class ForkliftPuzzleProblem extends Problem<ForkliftPuzzleState> {

    //private ForkliftPuzzleState goalState;

    public ForkliftPuzzleProblem(ForkliftPuzzleState initialState) {
        super(initialState, new ArrayList<Action>());
        actions.add(new ActionForward());
        actions.add(new ActionBackwards());
        /*
        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionDown());
        actions.add(new ActionLeft());*/
        //this.goalState = new ForkliftPuzzleState(ForkliftPuzzleState.goalMatrix);
    }

    public List<ForkliftPuzzleState> executeActions(ForkliftPuzzleState state) {
        List<ForkliftPuzzleState> successors = new LinkedList<ForkliftPuzzleState>();

        for (Action action : actions) {
            if (action.isValid(state)) {
                ForkliftPuzzleState sucessor = (ForkliftPuzzleState) state.clone();
                action.execute(sucessor);
                successors.add(sucessor);
            }
        }
        return successors;
    }
    /*
    public ForkliftPuzzleState getGoalState() {
        return goalState;
    }*/

    public boolean isGoal(ForkliftPuzzleState state) {
        for (int i = 0; i < state.getNumLines(); i++) {
            if (state.getTileValue(i,state.getNumColumns() - 1) == -1){
                if (state.getTileValue(i, state.getNumColumns() - 2) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public double computePathCost(List<Action> path) {
        return path.size();
    }
}
