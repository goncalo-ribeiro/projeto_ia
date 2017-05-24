package forkliftpuzzle;

import agent.Heuristic;

public class HeuristicTilesOutOfPlace extends Heuristic<ForkliftPuzzleProblem, ForkliftPuzzleState> {

    public double compute(ForkliftPuzzleState state) {
       //TODO HeuristicTilesOutOfPlace
        return 0;
    }
    
    @Override
    public String toString(){
        return "Tiles out of place";
    }    
}