package Communs.Class;

import java.util.ArrayList;
import java.util.Random;

import Communs.Class.Piece.PieceControleur;
import Communs.Interfaces.InterfaceSac;

/**
 * Class modélisant un sac, qui contient des pieces, et dans le quel on peut
 * tirer aléatoirement.
 */

public class Sac implements InterfaceSac {
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
    @Override
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
    @Override
    public void ajouter(PieceControleur piece) {
        sac.add(piece);
    }

    /**
     * @return le nombre de piece dans le sac en se moment
     */
    @Override
    public int getNombreDePiece() {
        return sac.size();
    }

    /**
     * @return si le sac est vide
     */
    @Override
    public boolean isEmpty() {
        return sac.size() == 0;
    }

    /**
     * @return si le sac est plein
     */
    @Override
    public boolean isFull() {
        return sac.size() == nombreDePieceMax;
    }
}
