package carcassonne;

import carcassonne.piece.Terrain;
import communs.PlayGameControleur;
import communs.PlayGameView;
import java.awt.Color;

/**
 * Class contrôlant une partie de carcassonne qui se joue.
 */
public class PlayCarcassonneControleur extends PlayGameControleur<Terrain> {
    /**
     * Constructeur
     */
    public PlayCarcassonneControleur() {
        model = new PlayCarcassonneModel();
        view = new PlayGameView<Terrain>(model, this);
    }

    /**
     * ajoute une personnage aux joueurs de la partie
     * 
     * @param nom     pseudo du personnage a ajouter
     * @param couleur couleur que l'équipe du personnage
     */
    public void ajoutPerso(String nom, Color couleur) {
        ((PlayCarcassonneModel) model).ajoutPerso(nom, couleur);
    }

    /**
     * ajoute une robot aux joueurs de la partie
     * 
     * @param nom     pseudo du robot a ajouter
     * @param couleur couleur que l'équipe du robot
     */
    public void ajoutBot(String nom, Color couleur) {
        ((PlayCarcassonneModel) model).ajoutBot(nom, couleur);
    }
}