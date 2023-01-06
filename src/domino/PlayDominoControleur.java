package domino;

import java.util.Scanner;

import communs.PlayGameControleur;
import communs.PlayGameView;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;

/**
 * Class modélisant une partie de domino qui se joue.
 */

public class PlayDominoControleur extends PlayGameControleur<Integer> {
    /**
     * Constructeur
     */
    public PlayDominoControleur(int nombreDePiece) {
        model = new PlayDominoModel(nombreDePiece);
        view = new PlayGameView<Integer>(model, this);
    }

    /**
     * Constructeur
     */
    public PlayDominoControleur(Scanner sc, int nombreDePiece, int nombreDeJoueur) {
        model = new PlayDominoModel(nombreDePiece);
        view = new PlayDominoTerminalView(model, this);
        initPlayerTerminal(sc, nombreDeJoueur);
    }

    /**
     * Initialise la liste de joueur pour la partie en mode terminal
     * 
     * @param sc lit la reponse de l'utilisateur
     */
    public void initPlayerTerminal(Scanner sc, int nombreDeJoueur) {
        ((PlayDominoTerminalView) view).initialisationJoueur(nombreDeJoueur, sc);
    }

    /**
     * Ajout un robots a liste des joueurs de la partie actuel.
     * 
     * @param nom pseudo du robot
     */
    public void ajoutBot(String nom) {
        ((PlayDominoModel) model).ajoutBot(nom);
    }

    /**
     * Place la pièce qu'a le player dans sa main sur le plateau.
     * 
     * @param game   partie qui se joue
     * @param player Joueur qui place sa piece
     * @param sc     System.in permettra de lire la reponse de l'utilisateur et de
     *               savoir si le joueur veux placer sa piece.
     */
    public void pensezVousPouvoirJouer(Scanner sc, PlayerControleur<PieceControleur<Integer>> player) {
        ((PlayDominoModel) model).pensezVousPouvoirJouer(sc, player);
    }
}
