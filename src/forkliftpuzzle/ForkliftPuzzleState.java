package forkliftpuzzle;

import agent.Action;
import agent.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ForkliftPuzzleState extends State implements Cloneable {

    /*
    static final int[][] goalMatrix = {{0, 1, 2},
                                       {3, 4, 5},
                                       {6, 7, 8}};*/
    static final int[] linesfinalMatrix = {0, 0, 0, 1, 1, 1, 2, 2, 2};
    static final int[] colsfinalMatrix = {0, 1, 2, 0, 1, 2, 0, 1, 2};
    public ArrayList<PeçaHorizontal> peçasHorizontais;
    public HashMap<Integer, PeçaHorizontal> hashHorizontal;
    public ArrayList<PeçaVertical> peçasVerticais;
    public HashMap<Integer, PeçaVertical> hashVertical;
    public static final int SIZE = 6;
    private int[][] matrix;
    public int lineForkLift;
    public int columnForkLift;

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
        peçasHorizontais = new ArrayList<>();
        hashHorizontal = new HashMap<>();
        peçasVerticais = new ArrayList<>();
        hashVertical = new HashMap<>();
        definirPeças();
        //printPeças();
        //System.out.println(this);
    }

    private void definirPeças() {
        ArrayList<Coordenadas> coordenadasForkLift = new ArrayList<Coordenadas>(1);
        coordenadasForkLift.add(new Coordenadas(lineForkLift, columnForkLift));
        PeçaHorizontal forklift = new PeçaHorizontal(coordenadasForkLift, 1);
        peçasHorizontais.add(forklift);
        hashHorizontal.put(1, forklift);

        int colunasASaltar = 0;
        for (int linha = 0; linha < matrix.length; linha++) {
            for (int coluna = 0; coluna < matrix.length; coluna++) {
                if (matrix[linha][coluna] > 1 && matrix[linha][coluna]%2==0) {
                    colunasASaltar= adicionarPeçaHorizontal(linha, coluna);
                    //System.out.println("horizontal \t linha=" + linha + " coluna=" + coluna);
                    coluna+=colunasASaltar;
                }
            }
        }
        int linhasSaltar = 0;
        for (int coluna = 0; coluna < matrix.length; coluna++) {
            for (int linha = 0; linha < matrix.length; linha++) {
                if (matrix[linha][coluna] > 1 && matrix[linha][coluna]%2==1) {
                    linhasSaltar = adicionarPeçaVertical(linha, coluna);
                    linha+=linhasSaltar;
                }
            }
        }
    }

    private int adicionarPeçaVertical(int linha, int coluna) {
        int comprimento = (matrix[linha][coluna]%10)/2;
        ArrayList<Coordenadas> coordenadas = new ArrayList<>(comprimento);
        for (int i = linha; i < linha+comprimento; i++) {
            coordenadas.add(new Coordenadas(i, coluna));
        }
        int chave = matrix[linha][coluna];

        PeçaVertical peça = new PeçaVertical(coordenadas, chave);
        peçasVerticais.add(peça);

        hashVertical.put(chave, peça);

        return comprimento - 1;
    }

    private int adicionarPeçaHorizontal(int linha, int coluna) {
        int comprimento = (matrix[linha][coluna]%10)/2;
        ArrayList<Coordenadas> coordenadas = new ArrayList<>(comprimento);
        for (int i = coluna; i < coluna + comprimento; i++) {
            coordenadas.add(new Coordenadas(linha, i));
        }
        int chave = matrix[linha][coluna];

        PeçaHorizontal peça = new PeçaHorizontal(coordenadas, chave);
        peçasHorizontais.add(peça);

        hashHorizontal.put(chave, peça);

        return comprimento - 1;
    }

    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveForward(int idPeça) {
        PeçaHorizontal peça = hashHorizontal.get(idPeça);
        Coordenadas coordenadaMaisAFrente = peça.getCoordenadaMaisAFrente();

        if (coordenadaMaisAFrente.getColuna() == 5
                || matrix[coordenadaMaisAFrente.getLinha()][coordenadaMaisAFrente.getColuna() + 1] > 0){//TODO
            return false;
        }

        return true;
    }

    public void moveForward(int idPeça) {
        //System.out.println("MOVE FORWARD");
        //System.out.println(this);

        PeçaHorizontal peçaHorizontal = hashHorizontal.get(idPeça);

        Coordenadas coordenadasMaisParaTras = peçaHorizontal.getCoordenadaMaisParaTras();
        Coordenadas coordenadasMaisAFrente = peçaHorizontal.getCoordenadaMaisAFrente();
        int valorPeça = matrix[coordenadasMaisParaTras.getLinha()][coordenadasMaisParaTras.getColuna()];

        matrix[coordenadasMaisParaTras.getLinha()][coordenadasMaisParaTras.getColuna()] = 0;
        matrix[coordenadasMaisAFrente.getLinha()][coordenadasMaisAFrente.getColuna()+1] = valorPeça;

        if (idPeça == 1) {
            columnForkLift++;
        }

        hashHorizontal.replace(idPeça, peçaHorizontal.paraFrente());
        //System.out.println(this);
    }

    public boolean canMoveBackwards(int idPeça) {
        PeçaHorizontal peça = hashHorizontal.get(idPeça);
        Coordenadas coordenadaMaisParaTras = peça.getCoordenadaMaisParaTras();

        if (coordenadaMaisParaTras.getColuna() == 0
                || matrix[coordenadaMaisParaTras.getLinha()][coordenadaMaisParaTras.getColuna() - 1] != 0){//TODO
            return false;
        }

        return true;
    }

    public void moveBackwards(int idPeça) {
        //System.out.println("MOVE BACKWARDS");
        //System.out.println(this);

        PeçaHorizontal peçaHorizontal = hashHorizontal.get(idPeça);

        Coordenadas coordenadasMaisParaTras = peçaHorizontal.getCoordenadaMaisParaTras();
        Coordenadas coordenadasMaisAFrente = peçaHorizontal.getCoordenadaMaisAFrente();
        int valorPeça = matrix[coordenadasMaisParaTras.getLinha()][coordenadasMaisParaTras.getColuna()];

        matrix[coordenadasMaisParaTras.getLinha()][coordenadasMaisParaTras.getColuna() - 1] = valorPeça;
        matrix[coordenadasMaisAFrente.getLinha()][coordenadasMaisAFrente.getColuna()] = 0;


        if (idPeça == 1) {
            columnForkLift--;
        }

        hashHorizontal.replace(idPeça, peçaHorizontal.paraTras());
        //System.out.println(this);
    }

    public boolean canMoveUpwards(int idPeça) {
        PeçaVertical peça = hashVertical.get(idPeça);
        Coordenadas coordenadaMaisAcima = peça.getCoordenadaMaisAcima();

        if (coordenadaMaisAcima.getLinha() == 0
                || matrix[coordenadaMaisAcima.getLinha() - 1][coordenadaMaisAcima.getColuna()] > 0){
            return false;
        }

        return true;
    }

    public void moveUpwards(int idPeça) {
        //System.out.println("MOVE UPWARDS");
        //System.out.println(this);

        PeçaVertical peçaVertical = hashVertical.get(idPeça);
        //System.out.println(idPeça);
        Coordenadas coordenadasMaisAcima = peçaVertical.getCoordenadaMaisAcima();
        Coordenadas coordenadasMaisAbaixo = peçaVertical.getCoordenadaMaisAbaixo();
        int valorPeça = matrix[coordenadasMaisAcima.getLinha()][coordenadasMaisAcima.getColuna()];

        matrix[coordenadasMaisAcima.getLinha() - 1][coordenadasMaisAcima.getColuna()] = valorPeça;
        matrix[coordenadasMaisAbaixo.getLinha()][coordenadasMaisAbaixo.getColuna()] = 0;

        hashVertical.replace(idPeça, peçaVertical.paraCima());
        //System.out.println(this);
    }

    public boolean canMoveDownwards(int idPeça) {
        PeçaVertical peça = hashVertical.get(idPeça);
        Coordenadas coordenadaMaisAbaixo = peça.getCoordenadaMaisAbaixo();

        if (coordenadaMaisAbaixo.getLinha() == 5
                || matrix[coordenadaMaisAbaixo.getLinha() + 1][coordenadaMaisAbaixo.getColuna()] > 0){//TODO
            return false;
        }

        return true;
    }

    public void moveDownwards(int idPeça) {
        //System.out.println("MOVE DOWNWARDS");
        //System.out.println(this);

        PeçaVertical peçaVertical = hashVertical.get(idPeça);

        Coordenadas coordenadasMaisAcima = peçaVertical.getCoordenadaMaisAcima();
        Coordenadas coordenadasMaisAbaixo = peçaVertical.getCoordenadaMaisAbaixo();
        int valorPeça = matrix[coordenadasMaisAcima.getLinha()][coordenadasMaisAcima.getColuna()];

        matrix[coordenadasMaisAbaixo.getLinha() + 1][coordenadasMaisAbaixo.getColuna()] = valorPeça;
        matrix[coordenadasMaisAcima.getLinha()][coordenadasMaisAcima.getColuna()] = 0;

        hashVertical.replace(idPeça, peçaVertical.paraBaixo());
        //System.out.println(this);
    }

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
    private void printPeças() {
        System.out.println("\n*=*=*=*=*=*=*= PRINT PEÇAS =*=*=*=*=*=*=*\n");
        int contador = 0;
        for (Peça peça :
                peçasHorizontais) {
            System.out.println("PEÇA HORIZONTAL Nº" + ++contador + ":\t" +peça);
        }
        contador = 0;
        for (Peça peça :
                peçasVerticais) {
            System.out.println("PEÇA VERTICAL Nº" + ++contador + ":\t" +peça);
        }
    }

    public PeçaHorizontal getPeçaHorizontal(int id){
        return hashHorizontal.get(id);
    }

    public PeçaVertical getPeçaVertical(int id){
        return hashVertical.get(id);
    }

    public double computeAmountOfBlocksInPath() {
        double h = 0;
        for (int j = columnForkLift+1; j < matrix.length; j++) {
            if((matrix[lineForkLift][j])>0) {
                h++;
            }
        }
        System.out.println(this);
        System.out.println("Custo da heuristica Blocos no caminho= "+h);
        return h;
    }

    public double computeDistanceToDoor() {
        int h = matrix.length-1 - columnForkLift;
        System.out.println(this);
        System.out.println("Custo da heuristica Distancia= "+h);
        return h;
    }

    public double computeAmountOfBlockedBlocksInPath() {
        double h = 0;
        for (int j = columnForkLift+1; j < matrix.length; j++) {
            int valor = matrix[lineForkLift][j];
            if(valor > 0) {
                if (canMoveOutOfTheWay(valor)) {
                    h += 1;
                } else {
                    h+= 3;
                }
            }
        }
        System.out.println(this);
        System.out.println("Custo da heuristica Blocos no caminho= "+h);
        return h;
    }

    private boolean canMoveOutOfTheWay(int idPeça) {
        PeçaVertical peçaAMover = hashVertical.get(idPeça);
        if (peçaAMover == null) {
            return false;
        }

        int tamanho = (idPeça % 10) / 2, colunaPeça = peçaAMover.getCoordenadaMaisAbaixo().getColuna();

        //verifica se pode sair para cima
        int linhaMaisAcima = peçaAMover.getCoordenadaMaisAcima().getLinha();
        boolean flag = true;
        forloop:
        for (int i = linhaMaisAcima-1; i > linhaMaisAcima-1 - tamanho; i--) {
            if (i < 0) {
                flag = false;
                break forloop;
            }
            if (matrix[i][colunaPeça] > 0) {
                flag = false;
                break forloop;
            }
        }
        if (flag) {
            System.out.println("Pode sair para cima");
            return true;
        }

        //verifica se pode sair para baixo
        int linhaMaisAbaixo = peçaAMover.getCoordenadaMaisAbaixo().getLinha();
        flag = true;
        forloop:
        for (int i = linhaMaisAbaixo+1; i < linhaMaisAbaixo+1 + tamanho; i++) {
            if (i > getNumLines()-1) {
                flag = false;
                break forloop;
            }
            if (matrix[i][colunaPeça] > 0) {
                flag = false;
                break forloop;
            }
        }
        if (flag) {
            System.out.println("Pode sair para baixo");
            return true;
        }



        return false;
    }
}
