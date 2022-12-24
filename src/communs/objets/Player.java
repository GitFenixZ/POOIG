package communs.objets;

import communs.PlayGame;
import communs.interfaces.InterfacePlayer;

/**
 * Class modélisant un joueur avec un nom, une main et un score.
 * 
 * P est le type de l'objet que le joueur a dans la main.
 * Exemple : PieceControleur<Integer> dans le domino.
 */
public class Player<P> implements InterfacePlayer<P> {
    /** Piece qu'a le joueur en main */
    private P main;
    /** Nom du joueur */
    private String name;
    /** Score actuel du joueur */
    private int score;

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return "Nom : " + name + " Score : " + score + "\nMain :\n" + main;
    }

    /**
     * Constructeur
     * 
     * @param name initialise le nom du joueur
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Methode qui fait piocher un joueur dans un sac
     * 
     * @param sac Sac dans lequel pioche le joueur.
     */
    @Override
    public void piocher(Sac<P> sac) {
        main = sac.tire();
    }

    /**
     * Methode qui supprime la main d'un joueur.
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
    public void scoreadd(int i) {
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
    public int getscore() {
        return score;
    }

    /**
     * Methode a utiliser pour jouer sans le terminal.
     * n'affiche aucune informations.
     * Permet de laisser un joueur jouer.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    public void jouer(PlayGame game) {
        while (!game.getTourSuivant()) {
        }
    }
}
