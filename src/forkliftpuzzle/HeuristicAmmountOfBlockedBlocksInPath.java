package forkliftpuzzle;

import agent.Heuristic;

/**
 * Created by rick_sanchez on 24/05/2017.
 */
public class HeuristicAmmountOfBlockedBlocksInPath extends Heuristic<ForkliftPuzzleProblem, ForkliftPuzzleState> {

    public double compute(ForkliftPuzzleState state){
        return state.computeAmountOfBlockedBlocksInPath();
    }

    @Override
    public String toString(){
        return "*Revisão* Amount Of Blocked Blocks In Path";
    }
}