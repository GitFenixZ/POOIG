package communs;

import java.util.ArrayList;
import java.util.Scanner;

import communs.objets.Player;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;

/**
 * Class modélisant une partie de domino qui se joue.
 * V est le type des valeur présente sur les pièces du jeu.
 */

public class PlayGame<V> {
    /**
     * Sac de piece de la partie
     */
    protected Sac<PieceControleur<V>> sac;
    /**
     * Liste des joueurs autour de la table
     */
    protected ArrayList<Player<PieceControleur<V>>> joueurs;
    /**
     * Plateau de jeu pour la partie en cours
     */
    protected PlateauControleur<V> plateau;

    /**
     * Constructeur
     * 
     * @param nombreDePiece  Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur   Nombre de joueur qui vont jouer
     * @param hauteurPlateau Hauteur du plateau de jeu
     * @param largeurPlateau Largeur du plateau de jeu
     */
    public PlayGame(int nombreDePiece, int hauteurPlateau, int largeurPlateau) {
        sac = new Sac<>(nombreDePiece);
        joueurs = new ArrayList<>();
        // initialise le plateau
        plateau = new PlateauControleur<V>(hauteurPlateau, largeurPlateau, 5, 5);
    }

    /**
     * Methode qui permet de faire piocher un joueur dans le sac
     * 
     * @param player joueur a faire piocher
     */
    public void piocherPiece(Player<PieceControleur<V>> player) {
        player.piocher(sac);
    }
}
