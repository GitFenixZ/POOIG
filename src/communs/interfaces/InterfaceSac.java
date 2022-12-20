package communs.interfaces;

import communs.objets.piece.PieceControleur;

/**
 * Interface du Sac dans lequel se trouve des pieces du jeu.
 */
public interface InterfaceSac {

    /**
     * Methode qui tire une piece aleatoire dans le sac
     * 
     * @return un piece du sac
     */
    public PieceControleur tire();

    /**
     * Methode qui ajoute une piece dans le sac
     */
    public void ajouter(PieceControleur piece);

    /**
     * @return le nombre de piece dans le sac en se moment
     */
    public int getNombreDePiece();

    /**
     * @return si le sac est vide
     */
    public boolean isEmpty();

    /**
     * @return si le sac est plein
     */
    public boolean isFull();
}
