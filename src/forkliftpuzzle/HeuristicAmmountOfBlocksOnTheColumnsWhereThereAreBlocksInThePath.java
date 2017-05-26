package forkliftpuzzle;

import agent.Heuristic;

/**
 * Created by rick_sanchez on 24/05/2017.
 */
public class HeuristicAmmountOfBlocksOnTheColumnsWhereThereAreBlocksInThePath extends Heuristic<ForkliftPuzzleProblem, ForkliftPuzzleState> {

    public double compute(ForkliftPuzzleState state){
        return state.computeAmmountOfBlocksOnTheColumnsWhereThereAreBlocksInThePath();
    }

    @Override
    public String toString(){
        return "Ammount Of Blocks On The Columns Where There Are Blocks On The Path";
    }
}