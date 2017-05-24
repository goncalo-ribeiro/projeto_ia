package forkliftpuzzle;

import agent.Heuristic;

public class HeuristicDistanceToDoor extends Heuristic<ForkliftPuzzleProblem, ForkliftPuzzleState> {

    public double compute(ForkliftPuzzleState state) {
        return state.computeDistanceToDoor();
    }
    
    @Override
    public String toString(){
        return "Distance to Door";
    }    
}