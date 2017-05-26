package forkliftpuzzle;

import agent.Heuristic;

/**
 * Created by rick_sanchez on 24/05/2017.
 */
public class HeuristicAmmountOfBlockedBlocksInPathOriginal extends Heuristic<ForkliftPuzzleProblem, ForkliftPuzzleState> {

    public double compute(ForkliftPuzzleState state){
        return state.computeAmountOfBlockedBlocksInPathOriginal();
    }

    @Override
    public String toString(){
        return "Original Amount Of Blocked Blocks In Path";
    }
}