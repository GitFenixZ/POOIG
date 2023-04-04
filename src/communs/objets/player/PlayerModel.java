package communs.objets.player;

import communs.interfaces.player.InterfacePlayerModel;
import communs.objets.Sac;

/**
 * Class modélisant un joueur avec un nom, une main et un score.
 * 
 * P est le type de l'objet que le joueur a dans la main.
 * Exemple : PieceControleur<Integer> dans le domino.
 */
public class PlayerModel<P> implements InterfacePlayerModel<P> {
    /** Piece qu'a le joueur en main */
    private P main;
    /** Nom du joueur */
    private String name;
    /** Score actuel du joueur */
    private int score;

    /**
     * Constructeur
     * 
     * @param name initialise le nom du joueur
     */
    public PlayerModel(String name) {
        this.name = name;
    }

    /**
     * Méthode qui fait piocher un joueur dans un sac
     * 
     * @param sac Sac dans lequel pioche le joueur.
     */
    @Override
    public void piocher(Sac<P> sac) {
        main = sac.tire();
    }

    /**
     * Méthode qui supprime la main d'un joueur.
     */
    @Override
    public void jeter() {
        main = null;
    }

    /**
     * Increment le score de i points
     * 
     * @param i Valeur a ajouter au score
     */
    @Override
    public void scoreAdd(int i) {
        score += i;
    }

    // Getters
    @Override
    public P getMain() {
        return main;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }
}