package communs.interfaces.player;

/**
 * Interface de la view d'un player. L'un des joueur qui joue la partie.
 * 
 * P est le type de l'objet que le joueur a dans la main.
 * Exemple : PieceControleur<Integer> dans le domino.
 */
public interface InterfacePlayerView {
    /**
     * reactualise les infos d'un joueur dans son affichage.
     */
    public void actualiser();
}
