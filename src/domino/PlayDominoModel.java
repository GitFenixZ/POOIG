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
     * @param nombreDePiece Nombre de piece presente dans le sac pour la partie
     */
    public PlayDominoModel(int nombreDePiece) {
        super(nombreDePiece);
        // initialise le plateau
        plateau = new DominoPlateauControleur(5, 5);
        // remplis le sac de piece de domino
        for (int i = 0; i < nombreDePiece; i++) {
            sac.ajouter(new DominoPieceControleur());
        }
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Se joue dans le termianal.
     * 
     * @param sc System.in permettra de lire la reponse de l'utilisateur
     */
    public void playTerminal(Scanner sc, PlayDominoControleur controleur) {
        if (!sac.isEmpty()) {
            plateau.start(sac);
        }
        plateau.afficher();
        // tant que le sac n'est pas vide
        while (!sac.isEmpty()) {
            piocherPiece(getActuelPlayer());
            System.out.print(getActuelPlayer());
            if (getActuelPlayer() instanceof DominoBotControleur) {
                if (!((DominoBotControleur) getActuelPlayer()).jouerTerminal((DominoPlateauControleur) plateau)) {
                    // fait jouer le bot. Si il ne peut pas jouer la pièce
                    rejouer();
                    ; // il rejoue.
                }
            } else {
                if (!plateau.pensezVousPouvoirJouer(sc, getActuelPlayer())) {
                    rejouer();
                }
            }
            nextPlayer();
            plateau.afficher();
        }
        System.out.println("Bravo  à tous!!!");

        // affiche les score de tout le monde
        for (PlayerControleur<PieceControleur<Integer>> p : joueurs) {
            System.out.println(p.getName() + " : " + p.getscore());
        }
        System.exit(0);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Avec interface Graphique
     */
    public void play() {
        plateau.start(sac);
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
}
