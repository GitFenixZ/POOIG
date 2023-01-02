package carcassonne;

import carcassonne.piece.Terrain;
import communs.PlayGameControleur;
import communs.PlayGameView;
import java.awt.Color;

/**
 * Class controlant une partie de carcassonne qui se joue.
 */
public class PlayCarcassonneControleur extends PlayGameControleur<Terrain> {
    /**
     * Constructeur
     * 
     * @param nombreJoueur Nombre de joueur qui vont jouer
     */
    public PlayCarcassonneControleur(int nombreJoueur) {
        model = new PlayCarcassonneModel(nombreJoueur);
        view = new PlayGameView<Terrain>(model, this);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Avec interface Graphique
     */
    public void play() {
        ((PlayCarcassonneModel) model).start();
        rejouer();
    }

    public void ajoutPerso(String nom, Color couleur) {
        ((PlayCarcassonneModel) model).ajoutPerso(nom, couleur);
    }

    public void ajoutBot(String nom, Color couleur) {
        ((PlayCarcassonneModel) model).ajoutBot(nom, couleur);
    }
}