package Communs.Class;

import Communs.Class.Piece.PieceControleur;
import Communs.Interfaces.InterfacePlayer;

/**
 * Class modélisant un joueur avec un nom, une main et un score.
 */
public class Player implements InterfacePlayer {
    /** Piece qu'a le joueur en main */
    private PieceControleur main;
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
    public void piocher(Sac sac) {
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
    public PieceControleur getMain() {
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
}
