package gui;

import forkliftpuzzle.ForkLiftPuzzleEvent;
import forkliftpuzzle.ForkLiftPuzzleListener;
import forkliftpuzzle.ForkliftPuzzleState;
import javax.swing.table.AbstractTableModel;

public class PuzzleTableModel extends AbstractTableModel implements ForkLiftPuzzleListener {

    private ForkliftPuzzleState puzzle;

    public PuzzleTableModel(ForkliftPuzzleState puzzle) {
        if(puzzle == null){
            throw new NullPointerException("Puzzle cannot be null");
        }
        this.puzzle = puzzle;
        this.puzzle.addListener(this);
    }

    public int getColumnCount() {
        return puzzle.getNumLines();
    }

    public int getRowCount() {
        return puzzle.getNumColumns();
    }

    public Object getValueAt(int row, int col) {
        return new Integer(puzzle.getTileValue(row, col));
    }

    public void puzzleChanged(ForkLiftPuzzleEvent pe){
        fireTableDataChanged();
        try{
            Thread.sleep(500);
        }catch(InterruptedException ignore){
        }
    }

    public void setPuzzle(ForkliftPuzzleState puzzle){
        if(puzzle == null){
          throw new NullPointerException("Puzzle cannot be null");
        }
        this.puzzle.removeListener(this);
        this.puzzle = puzzle;
        puzzle.addListener(this);
        fireTableDataChanged();
    }
}
