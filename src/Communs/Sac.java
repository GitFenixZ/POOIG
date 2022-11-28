package Communs;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class modélisant un sac, qui contient des pieces, et dans le quel on peut
 * tirer aléatoirement.
 */

public class Sac {
    private ArrayList<PieceControleur> sac;
    private int nombreDePieceMax;

    public Sac(int nombreDePieceMax) {
        sac = new ArrayList<PieceControleur>();
        this.nombreDePieceMax = nombreDePieceMax;
    }

    public PieceControleur tire() {
        if (!isEmpty()) {
            Random rd = new Random();
            int indice = rd.nextInt(sac.size());
            PieceControleur piece = sac.get(indice);
            sac.remove(indice);
            return piece;
        }
        return null;
    }

    public void ajouter(PieceControleur piece) {
        sac.add(piece);
    }

    public int getNombreDePiece() {
        return sac.size();
    }

    public boolean isEmpty() {
        return sac.size() == 0;
    }

    public boolean isFill() {
        return sac.size() == nombreDePieceMax;
    }
}
