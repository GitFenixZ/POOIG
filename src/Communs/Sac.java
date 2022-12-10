package Communs;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class modélisant un sac, qui contient des pieces, et dans le quel on peut
 * tirer aléatoirement.
 */

public class Sac {
    /**
     * Contenur du sac
     */
    private ArrayList<PieceControleur> sac;
    /**
     * Capacite de piece que peut contenur le sac
     */
    private int nombreDePieceMax;

    /**
     * Constructeur
     * 
     * @param nombreDePieceMax Capacite de piece que peut contenur le sac
     */
    public Sac(int nombreDePieceMax) {
        sac = new ArrayList<PieceControleur>();
        this.nombreDePieceMax = nombreDePieceMax;
    }

    /**
     * Methode qui tire une piece aleatoire dans le sac
     * 
     * @return un piece du sac
     */
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

    /**
     * Methode qui ajoute une piece dans le sac
     */
    public void ajouter(PieceControleur piece) {
        sac.add(piece);
    }

    /**
     * @return le nombre de piece dans le sac en se moment
     */
    public int getNombreDePiece() {
        return sac.size();
    }

    /**
     * @return si le sac est vide
     */
    public boolean isEmpty() {
        return sac.size() == 0;
    }

    /**
     * @return si le sac est plein
     */
    public boolean isFull() {
        return sac.size() == nombreDePieceMax;
    }
}
