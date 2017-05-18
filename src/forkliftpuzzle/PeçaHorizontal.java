package forkliftpuzzle;

import java.util.ArrayList;

/**
 * Created by rick_sanchez on 17/05/2017.
 */
public class PeçaHorizontal extends Peça{

    public PeçaHorizontal(ArrayList<Coordenadas> cordenadas, int id) {
        super(cordenadas, id);
    }

    public boolean isCoordenadaMaisAEsquerda(Coordenadas coordenada) {
        for (Coordenadas coordenadaPeça:
             coordenadas) {
            if (coordenada.getColuna() > coordenadaPeça.getColuna()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCoordenadaMaisADireita(Coordenadas coordenada) {
        for (Coordenadas coordenadaPeça:
                coordenadas) {
            if (coordenada.getColuna() < coordenadaPeça.getColuna()) {
                return false;
            }
        }
        return true;
    }

    public PeçaHorizontal paraTras() {
        ArrayList<Coordenadas> novasCoordenadas = new ArrayList<>();
        for (Coordenadas coordenada :
                coordenadas) {
            novasCoordenadas.add(new Coordenadas(coordenada.getLinha(), coordenada.getColuna() - 1));
        }
        return new PeçaHorizontal(novasCoordenadas ,id);
    }

    public PeçaHorizontal paraFrente() {
        ArrayList<Coordenadas> novasCoordenadas = new ArrayList<>();
        for (Coordenadas coordenada :
                coordenadas) {
            novasCoordenadas.add(new Coordenadas(coordenada.getLinha(), coordenada.getColuna() + 1));
        }
        return new PeçaHorizontal(novasCoordenadas ,id);
    }

    public Coordenadas getCoordenadaMaisParaTras(){
        Coordenadas coordenadaMaisParaTras = coordenadas.get(0);
        for (Coordenadas coordenada :
                coordenadas) {
            if (coordenada.getColuna() < coordenadaMaisParaTras.getColuna()){
                coordenadaMaisParaTras = coordenada;
            }
        }
        return coordenadaMaisParaTras;
    }

    public Coordenadas getCoordenadaMaisAFrente(){
        Coordenadas coordenadaMaisAFrente= coordenadas.get(0);
        for (Coordenadas coordenada :
                coordenadas) {
            if (coordenada.getColuna() > coordenadaMaisAFrente.getColuna()){
                coordenadaMaisAFrente = coordenada;
            }
        }
        return coordenadaMaisAFrente;
    }
}
