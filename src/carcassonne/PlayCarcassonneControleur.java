package carcassonne;

import java.util.Scanner;

import carcassonne.piece.Terrain;
import communs.PlayGameControleur;
import communs.PlayGameView;

/**
 * Class controlant une partie de carcassonne qui se joue.
 */
public class PlayCarcassonneControleur extends PlayGameControleur<Terrain> {
    /**
     * Constructeur
     * 
     * @param nombreJoueur Nombre de joueur qui vont jouer
     * @param sc           scanner qui lit les entrées
     */
    public PlayCarcassonneControleur(int nombreJoueur, Scanner sc) {
        model = new PlayCarcassonneModel(nombreJoueur, sc);
        view = new PlayGameView<Terrain>(model);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * @param sc System.in permettra de lire la reponse de l'utilisateur
     */
    public void playTerminal(Scanner sc) {
        ((PlayCarcassonneModel) model).playTerminal(sc);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Avec interface Graphique
     */
    public void play() {
        ((PlayCarcassonneModel) model).play();
    }
}