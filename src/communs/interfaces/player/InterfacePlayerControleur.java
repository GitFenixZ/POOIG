package communs.interfaces.player;

import communs.objets.Sac;

/**
 * Interface du controleur d'un player. L'un des joueur qui joue la partie.
 * 
 * P est le type de l'objet que le joueur a dans la main.
 * Exemple : PieceControleur<Integer> dans le domino.
 */
public interface InterfacePlayerControleur<P> {
    /**
     * Methode qui fait piocher un joueur dans un sac
     * 
     * @param sac Sac dans lequel pioche le joueur.
     */
    public void piocher(Sac<P> sac);

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
    public P getMain();

    public String getName();

    public int getscore();
}