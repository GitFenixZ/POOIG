package communs.interfaces;

import communs.objets.Sac;
import communs.objets.piece.PieceControleur;

/**
 * Interface du player. L'un des joueur qui joue la partie.
 */
public interface InterfacePlayer {
    /**
     * Methode qui fait piocher un joueur dans un sac
     * 
     * @param sac Sac dans lequel pioche le joueur.
     */
    public void piocher(Sac sac);

    /**
     * Methode qui supprime la main d'un joueur.
     */
    public void jeter();

    /**
     * Increment le score de i points
     * 
     * @param i Valeur a ajouter au score
     */
    public void scoreadd(int i);

    // Getters
    public PieceControleur getMain();

    public String getName();

    public int getscore();
}