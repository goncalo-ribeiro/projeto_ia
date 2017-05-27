package forkliftpuzzle;

import agent.Heuristic;

/**
 * Created by rick_sanchez on 24/05/2017.
 */
public class HeuristicAmmountOfBlocksOnTheColumnsWhereThereAreBlocksInThePathPlusDistance extends Heuristic<ForkliftPuzzleProblem, ForkliftPuzzleState> {

    public double compute(ForkliftPuzzleState state){
        return state.computeAmmountOfBlocksOnTheColumnsWhereThereAreBlocksInThePathPlusDistance();
    }

    @Override
    public String toString(){
        return "Distance to Door + Ammount Of Blocks On The Columns Where There Are Blocks On The Path";
    }
}