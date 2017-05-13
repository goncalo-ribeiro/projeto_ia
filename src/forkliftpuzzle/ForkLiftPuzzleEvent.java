package forkliftpuzzle;

import java.util.EventObject;

public class ForkLiftPuzzleEvent extends EventObject {

    public ForkLiftPuzzleEvent(ForkliftPuzzleState source) {
        super(source);
    }
}
