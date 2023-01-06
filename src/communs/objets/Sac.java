package communs.objets;

import java.util.ArrayList;
import java.util.Random;

import communs.interfaces.InterfaceSac;

/**
 * Class modélisant un sac, qui contient des pièces, et dans le quel on peut
 * tirer aléatoirement.
 * 
 * P est le type des objets dans le sac.
 * Exemple : PieceControleur<Integer> dans le domino.
 */

public class Sac<P> implements InterfaceSac<P> {
    /**
     * Sac qui contient les pièces
     */
    private ArrayList<P> sac;
    /**
     * Capacité de pièces que peut contenir le sac
     */
    private int nombreDePieceMax;

    /**
     * Constructeur
     * 
     * @param nombreDePieceMax Capacité de pièce que peut contenir le sac
     */
    public Sac(int nombreDePieceMax) {
        sac = new ArrayList<P>();
        this.nombreDePieceMax = nombreDePieceMax;
    }

    /**
     * Méthode qui tire une pièce aléatoire dans le sac
     * 
     * @return une pièce du sac
     */
    @Override
    public P tire() {
        if (!isEmpty()) {
            Random rd = new Random();
            int indice = rd.nextInt(sac.size());
            P piece = sac.get(indice);
            sac.remove(indice);
            return piece;
        }
        return null;
    }

    /**
     * Méthode qui ajoute une pièce dans le sac
     */
    @Override
    public void ajouter(P piece) {
        sac.add(piece);
    }

    /**
     * @return le nombre de pièce dans le sac en se moment
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
