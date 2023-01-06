import java.util.Scanner;

import carcassonne.PlayCarcassonneControleur;
import communs.PlayGameControleur;
import domino.PlayDominoControleur;
import java.awt.Color;

/**
 * Class permetant l'éxécution des parties.
 */

public class MenuModel {
    private PlayGameControleur game;

    MenuModel() {
    }

    // Getter
    public PlayGameControleur getGame() {
        return game;
    }

    /**
     * initialise une partie de domino
     * 
     * @param nombreDeJoueur nombre de joueur qui vont jouer.
     */
    public void initDomino(int nombreDeJoueur) {
        game = new PlayDominoControleur(10 * nombreDeJoueur);
    }

    /**
     * Lance une partie complete de domino. Du debut jusqu'a ce qu'il n'y ai
     * plus de piece dans le sac.
     */
    public void playDomino() {
        game.play();
        game.rejouer();
    }

    /**
     * initialise une partie de Domino dans le terminal.
     * 
     * @param nombreDeJoueur nombre de joueur qui vont jouer.
     */
    public void initDominoTerminale(Scanner sc, int nombreDeJoueur) {
        game = new PlayDominoControleur(sc, 10 * nombreDeJoueur, nombreDeJoueur);
    }

    /**
     * initialise une partie de Carcassonne.
     */
    public void initCarcassonne() {
        game = new PlayCarcassonneControleur();
    }

    /**
     * Lance une partie complete de carcassonne. Du debut jusqu'a ce qu'il n'y ai
     * plus de piece dans le sac.
     */
    public void playCarcassonne() {
        game.play();
        game.rejouer();
    }

    /**
     * Ajoute un joueur a la partie.
     * 
     * @param nom pseudo du joueur.
     */
    public void ajoutPersoDomino(String nom) {
        ((PlayDominoControleur) game).ajoutPerso(nom);
    }

    /**
     * ajoute une personnage aux joueurs de la partie
     * 
     * @param nom     pseudo du personnage a ajouter
     * @param couleur couleur que l'équipe du personnage
     */
    public void ajoutPersoCarcassonne(String nom, Color couleur) {
        ((PlayCarcassonneControleur) game).ajoutPerso(nom, couleur);
    }

    /**
     * Ajout un robots a liste des joueurs de la partie actuel.
     * 
     * @param nom pseudo du robot
     */
    public void ajoutBotDomino(String nom) {
        ((PlayDominoControleur) game).ajoutBot(nom);
    }

    /**
     * ajoute une robot aux joueurs de la partie
     *
     * @param nom     pseudo du robot a ajouter
     * @param couleur couleur que l'équipe du robot
     */
    public void ajoutBotCarcassonne(String nom, Color couleur) {
        ((PlayCarcassonneControleur) game).ajoutBot(nom, couleur);
    }

}
