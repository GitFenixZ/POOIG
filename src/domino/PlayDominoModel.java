package domino;

import java.util.Scanner;

import communs.PlayGameModel;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;
import domino.joueurs.DominoBotControleur;
import domino.piece.DominoPieceControleur;
import domino.plateau.DominoPlateauControleur;

/**
 * Class modélisant une partie de domino qui se joue.
 */

public class PlayDominoModel extends PlayGameModel<Integer> {

    /**
     * Constructeur
     * 
     * @param nombreDePiece Nombre de pièce présente dans le sac pour la partie
     */
    public PlayDominoModel(int nombreDePiece) {
        super(nombreDePiece);
        // initialise le plateau
        plateau = new DominoPlateauControleur(5, 5);
        // remplis le sac de pièce de domino
        for (int i = 0; i < nombreDePiece; i++) {
            sac.ajouter(new DominoPieceControleur());
        }
    }

    /**
     * Ajout un robots a liste des joueurs de la partie actuel.
     * 
     * @param nom pseudo du robot
     */
    public void ajoutBot(String nom) {
        joueurs.add(new DominoBotControleur(nom));
    }

    /**
     * fait jouer un tour au robot joueur actuel
     */
    public void jouerBot() {
        ((DominoBotControleur) getActuelPlayer()).jouer(plateau);
    }

    /**
     * Place la pièce qu'a le player dans sa main sur le plateau.
     * 
     * @param game   partie qui se joue
     * @param player Joueur qui place sa pièce
     * @param sc     System.in permettra de lire la réponse de l'utilisateur et de
     *               savoir si le joueur veux placer sa pièce.
     */
    public void pensezVousPouvoirJouer(Scanner sc, PlayerControleur<PieceControleur<Integer>> player) {
        plateau.pensezVousPouvoirJouer(sc, player);
    }
}
