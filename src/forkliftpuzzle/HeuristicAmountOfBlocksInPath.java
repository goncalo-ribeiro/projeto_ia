package forkliftpuzzle;

import agent.Heuristic;

public class HeuristicAmountOfBlocksInPath extends Heuristic<ForkliftPuzzleProblem, ForkliftPuzzleState>{

    public double compute(ForkliftPuzzleState state){
        return state.computeAmountOfBlocksInPath();
    }

    @Override
    public String toString(){
        return "Amount Of Blocks In Path";
    }
}