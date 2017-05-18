package forkliftpuzzle;

import java.util.ArrayList;

/**
 * Created by rick_sanchez on 17/05/2017.
 */
public class PeçaVertical extends Peça {

    public PeçaVertical(ArrayList<Coordenadas> cordenadas, int id) {
        super(cordenadas, id);
    }

    public Coordenadas getCoordenadaMaisAcima(){
        Coordenadas coordenadaMaisAcima = coordenadas.get(0);
        for (Coordenadas coordenada :
                coordenadas) {
            if (coordenada.getLinha() < coordenadaMaisAcima.getLinha()){
                coordenadaMaisAcima = coordenada;
            }
        }
        return coordenadaMaisAcima;
    }

    public Coordenadas getCoordenadaMaisAbaixo(){
        Coordenadas coordenadaMaisAbaixo= coordenadas.get(0);
        for (Coordenadas coordenada :
                coordenadas) {
            if (coordenada.getLinha() > coordenadaMaisAbaixo.getLinha()){
                coordenadaMaisAbaixo = coordenada;
            }
        }
        return coordenadaMaisAbaixo;
    }

    public boolean isCoordenadaMaisAcima(Coordenadas coordenada) {
        for (Coordenadas coordenadaPeça:
                coordenadas) {
            if (coordenada.getLinha() > coordenadaPeça.getLinha()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCoordenadaMaisAbaixo(Coordenadas coordenada) {
        for (Coordenadas coordenadaPeça:
                coordenadas) {
            if (coordenada.getLinha() < coordenadaPeça.getLinha()) {
                return false;
            }
        }
        return true;
    }

    public PeçaVertical paraCima() {
        ArrayList<Coordenadas> novasCoordenadas = new ArrayList<>();
        for (Coordenadas coordenada :
                coordenadas) {
            novasCoordenadas.add(new Coordenadas(coordenada.getLinha()-1, coordenada.getColuna()));
        }
        return new PeçaVertical(novasCoordenadas ,id);
    }

    public PeçaVertical paraBaixo() {
        ArrayList<Coordenadas> novasCoordenadas = new ArrayList<>();
        for (Coordenadas coordenada :
                coordenadas) {
            novasCoordenadas.add(new Coordenadas(coordenada.getLinha()+1, coordenada.getColuna()));
        }
        return new PeçaVertical(novasCoordenadas ,id);
    }
}
