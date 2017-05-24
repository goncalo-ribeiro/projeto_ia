package forkliftpuzzle;

/**
 * Created by rick_sanchez on 17/05/2017.
 */
public class Coordenadas {
    private int linha;
    private int coluna;

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public Coordenadas(int linha, int coluna) {

        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return "linha="+linha+" coluna="+coluna;
    }
}
