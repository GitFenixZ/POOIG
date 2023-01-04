package domino;

import java.util.Scanner;

import communs.PlayGameControleur;

/**
 * Class modélisant une partie de domino qui se joue.
 */

public class PlayDominoControleur extends PlayGameControleur<Integer> {
    /**
     * Constructeur
     * 
     * @param nombreDePiece Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur  Nombre de joueur qui vont jouer
     */
    public PlayDominoControleur(int nombreDePiece, int nombreJoueur, Scanner sc) {
        model = new PlayDominoModel(nombreDePiece, nombreJoueur);
        view = new PlayDominoView(model, this);
    }

    /**
     * Constructeur
     * 
     * @param nombreDePiece Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur  Nombre de joueur qui vont jouer
     */
    public PlayDominoControleur(int nombreDePiece, int nombreJoueur) {
        model = new PlayDominoModel(nombreDePiece, nombreJoueur);
        view = new PlayDominoView(model, this);
    }

    /**
     * Initialise la liste de joueur pour la partie en mode terminal
     * 
     * @param sc lit la reponse de l'utilisateur
     */
    public void initPlayerTerminal(Scanner sc, int nombreDeJoueur) {
        ((PlayDominoView) view).initialisationJoueur(sc, nombreDeJoueur);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Se joue dans le termianal.
     * 
     * @param sc lit la reponse de l'utilisateur
     */
    public void playTerminal(Scanner sc) {
        ((PlayDominoModel) model).playTerminal(sc, this);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Avec interface Graphique
     */
    public void play() {
        view.revalidate();
        ((PlayDominoModel) model).play();
        rejouer();
    }

    /**
     * Ajout un robots a liste des joueurs de la partie actuel.
     * 
     * @param nom pseudo du robot
     */
    public void ajoutBot(String nom) {
        ((PlayDominoModel) model).ajoutBot(nom);
    }
}
