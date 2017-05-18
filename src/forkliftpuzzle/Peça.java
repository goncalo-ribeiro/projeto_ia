package forkliftpuzzle;

import java.util.ArrayList;

/**
 * Created by rick_sanchez on 17/05/2017.
 */
public abstract class Peça {
    protected ArrayList<Coordenadas> coordenadas;
    public int id;

    public Peça(ArrayList<Coordenadas> cordenadas, int id) {
        this.coordenadas = cordenadas;
        this.id = id;
    }

    @Override
    public String toString() {
        String tamanhoStr = "Tamanho = " + coordenadas.size();
        String coordenadasStr = "";
        for (Coordenadas coordenada :
                coordenadas) {
            coordenadasStr+="\t"+coordenada;
        }
        return tamanhoStr+coordenadasStr;
    }

    public ArrayList<Coordenadas> getCoordenadas() {
        return coordenadas;
    }

    public int getId() {
        return id;
    }

}
