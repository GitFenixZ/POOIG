package domino;

import java.util.Scanner;

import communs.PlayGameControleur;
import communs.PlayGameView;

/**
 * Class modélisant une partie de domino qui se joue.
 */

public class PlayDominoControleur extends PlayGameControleur<Integer> {
    /**
     * Constructeur
     * 
     * @param nombreDePiece  Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur   Nombre de joueur qui vont jouer
     * @param hauteurPlateau Hauteur du plateau de jeu
     * @param largeurPlateau Largeur du plateau de jeu
     */
    public PlayDominoControleur(int nombreDePiece, int nombreJoueur, Scanner sc) {
        model = new PlayDominoModel(nombreDePiece, nombreJoueur, sc);
        view = new PlayGameView<Integer>(model);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Se joue dans le termianal.
     * 
     * @param sc System.in permettra de lire la reponse de l'utilisateur
     */
    public void playTerminal(Scanner sc) {
        ((PlayDominoModel) model).playTerminal(sc);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Avec interface Graphique
     */
    public void play() {
        ((PlayDominoModel) model).play();
    }
}
