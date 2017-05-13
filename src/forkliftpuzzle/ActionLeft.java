package forkliftpuzzle;

import agent.Action;

public class ActionLeft extends Action<ForkliftPuzzleState>{

    public ActionLeft(){
        super(1);
    }

    public void execute(ForkliftPuzzleState state){
        state.moveLeft();
        state.setAction(this);
    }

    public boolean isValid(ForkliftPuzzleState state){
        return state.canMoveLeft();
    }
}
