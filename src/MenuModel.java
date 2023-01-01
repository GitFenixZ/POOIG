import java.util.Scanner;

import carcassonne.PlayCarcassonneControleur;
import communs.PlayGameControleur;
import domino.PlayDominoControleur;

/**
 * Class permetant l'éxécution des parties.
 */

public class MenuModel {
    private PlayGameControleur game;

    MenuModel() {
    }

    public void initDomino(int nombreDeJoueur) {
        game = new PlayDominoControleur(5 * nombreDeJoueur, nombreDeJoueur);
    }

    public void playDomino() {
        ((PlayDominoControleur) game).play();
    }

    public void initDominoTerminale(int nombreDeJoueur, Scanner sc) {
        game = new PlayDominoControleur(5 * nombreDeJoueur, nombreDeJoueur, sc);
    }

    public void playDominoTerminale(Scanner sc) {
        ((PlayDominoControleur) game).playTerminal(sc);
    }

    public void initCarcassonne(int nombreDeJoueur) {
        game = new PlayCarcassonneControleur(3);
    }

    public void playCarcassonne() {
        ((PlayCarcassonneControleur) game).play();
    }

    public void ajoutPersoDomino(String nom) {
        ((PlayDominoControleur) game).ajoutPerso(nom);
    }

    public void ajoutPersoCarcassonne(String nom) {
        ((PlayCarcassonneControleur) game).ajoutPerso(nom);
    }

    public void ajoutBotDomino(String nom) {
        ((PlayDominoControleur) game).ajoutBot(nom);
    }

    public void ajoutBotCarcassonne(String nom) {
        ((PlayCarcassonneControleur) game).ajoutBot(nom);
    }

    public PlayGameControleur getGame() {
        return game;
    }
}
