package eightpuzzle;

import agent.Action;
import agent.Problem;
import agent.State;

import java.util.ArrayList;
import java.util.List;

public class EightPuzzleProblem extends Problem<EightPuzzleState> {
    private EightPuzzleState goalState;

    public EightPuzzleProblem(EightPuzzleState initialState) {
        super(initialState, new ArrayList<Action>(4));

        goalState = new EightPuzzleState(EightPuzzleState.goalMatrix);

        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionDown());
        actions.add(new ActionLeft());
    }

    //permite obter a lista de estados sucessores validos do estado atual,
    //resultantes da aplicação de cada uma das acções validas
    @Override
    public List<EightPuzzleState> executeActions(EightPuzzleState state) { //obterSucessores();
        ArrayList<EightPuzzleState> successors = new ArrayList<>(4);

        for (Action action :
                actions) {//para cada uma das acções disponiveis
            //caso a acção seja valida
            if (action.isValid(state)) {
                //aplica a acção, criando um estado sucessor
                EightPuzzleState successor = (EightPuzzleState) state.clone();
                successor.executeAction(action);

                //acrescenta o estado sucessor à lista a devolver
                successors.add(successor);
            }

        }
        return successors;
    }

    public EightPuzzleState getGoalState() {
        return goalState;
    }

    @Override
    public Boolean isGoal(EightPuzzleState state) {
        return state.equals(goalState);
    }

    @Override
    public double computePathCost(List<Action> path) {
        return path.size();
    }
}
