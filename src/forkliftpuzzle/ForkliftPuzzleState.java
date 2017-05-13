package forkliftpuzzle;

import agent.Action;
import agent.State;
import java.util.ArrayList;
import java.util.Arrays;

public class ForkliftPuzzleState extends State implements Cloneable {

    /*
    static final int[][] goalMatrix = {{0, 1, 2},
                                       {3, 4, 5},
                                       {6, 7, 8}};*/
    static final int[] linesfinalMatrix = {0, 0, 0, 1, 1, 1, 2, 2, 2};
    static final int[] colsfinalMatrix = {0, 1, 2, 0, 1, 2, 0, 1, 2};
    public static final int SIZE = 6;
    private int[][] matrix;
    private int lineForkLift;
    private int columnForkLift;

    public ForkliftPuzzleState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 1) {
                    lineForkLift = i;
                    columnForkLift = j;
                }
            }
        }
        //System.out.println(lineForkLift + " " + columnForkLift);
    }

    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveForward() {
        return matrix[lineForkLift][columnForkLift + 1] == 0;
    }
    public boolean canMoveBackwards() {
        return columnForkLift != 0 && matrix[lineForkLift][columnForkLift - 1] == 0;
    }

    public boolean canMoveUp() {
        return lineForkLift != 0;
    }

    public boolean canMoveRight() {
        return columnForkLift != matrix.length - 1;
    }

    public boolean canMoveDown() {
        return lineForkLift != matrix.length - 1;
    }

    public boolean canMoveLeft() {
        return columnForkLift != 0;
    }

    /*
     * In the next four methods we don't verify if the actions are valid.
     * This is done in method executeActions in class ForkliftPuzzleProblem.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */
    public void moveForward() {
        matrix[lineForkLift][columnForkLift] = matrix[lineForkLift][++columnForkLift];
        matrix[lineForkLift][columnForkLift] = 1;
    }

    public void moveBackwards() {
        matrix[lineForkLift][columnForkLift] = matrix[lineForkLift][--columnForkLift];
        matrix[lineForkLift][columnForkLift] = 1;
    }

    public void moveUp() {
        matrix[lineForkLift][columnForkLift] = matrix[--lineForkLift][columnForkLift];
        matrix[lineForkLift][columnForkLift] = 0;
    }

    public void moveRight() {
        matrix[lineForkLift][columnForkLift] = matrix[lineForkLift][++columnForkLift];
        matrix[lineForkLift][columnForkLift] = 0;
    }

    public void moveDown() {
        matrix[lineForkLift][columnForkLift] = matrix[++lineForkLift][columnForkLift];
        matrix[lineForkLift][columnForkLift] = 0;
    }

    public void moveLeft() {
        matrix[lineForkLift][columnForkLift] = matrix[lineForkLift][--columnForkLift];
        matrix[lineForkLift][columnForkLift] = 0;
    }
/* TODO
    public double computeTilesOutOfPlace(ForkliftPuzzleState finalState) {
        double h = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // Blank is ignored so that the heuristic is admissible
                if (this.matrix[i][j] != 0 && this.matrix[i][j] != finalState.matrix[i][j]) {
                    h++;
                }
            }
        }
        return h;
    }

    public double computeTileDistances(ForkliftPuzzleState finalState) {
        double h = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (this.matrix[i][j] != 0) { // Blank is ignored so that the heuristic is admissible
                    h += Math.abs(i - linesfinalMatrix[this.matrix[i][j]])
                            + Math.abs(j - colsfinalMatrix[this.matrix[i][j]]);
                }
            }
        }
        return h;
    }*/

    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getTileValue(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return matrix[line][column];
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ForkliftPuzzleState)) {
            return false;
        }

        ForkliftPuzzleState o = (ForkliftPuzzleState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public Object clone() {
        return new ForkliftPuzzleState(matrix);
    }
    //Listeners
    private transient ArrayList<ForkLiftPuzzleListener> listeners = new ArrayList<ForkLiftPuzzleListener>(3);

    public synchronized void removeListener(ForkLiftPuzzleListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(ForkLiftPuzzleListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(ForkLiftPuzzleEvent pe) {
        for (ForkLiftPuzzleListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }
}
