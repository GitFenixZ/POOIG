package domino;

import java.util.Scanner;

import communs.PlayGame;
import communs.objets.Player;
import communs.objets.piece.PieceControleur;
import domino.joueurs.DominoBot;
import domino.piece.DominoPieceControleur;
import domino.plateau.DominoPlateauControleur;

/**
 * Class modélisant une partie de domino qui se joue.
 */

public class PlayDomino extends PlayGame<Integer> {
    /**
     * Constructeur
     * 
     * @param nombreDePiece  Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur   Nombre de joueur qui vont jouer
     * @param hauteurPlateau Hauteur du plateau de jeu
     * @param largeurPlateau Largeur du plateau de jeu
     */
    public PlayDomino(int nombreDePiece, int nombreJoueur, int hauteurPlateau, int largeurPlateau, Scanner sc) {
        super(nombreDePiece, hauteurPlateau, largeurPlateau);

        // initialise le plateau
        plateau = new DominoPlateauControleur(hauteurPlateau, largeurPlateau, 5, 5);
        // intialise les joueurs
        for (int i = 0; i < nombreJoueur; i++) {
            System.out.println("Est ce une joueur ?");
            if (sc.nextLine().equals("oui")) {
                System.out.println("Comment s'appel le joueur " + (i + 1) + " ?");
                joueurs.add(new Player<PieceControleur<Integer>>(sc.nextLine()));
            } else {
                joueurs.add(new DominoBot("Joueur" + i));
            }
        }
        // remplis le sac de piece de domino
        for (int i = 0; i < nombreDePiece; i++) {
            sac.ajouter(new DominoPieceControleur());
        }
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * @param sc System.in permettra de lire la reponse de l'utilisateur
     */
    public void play(Scanner sc) {
        if (!sac.isEmpty()) {
            plateau.start(sac);
        }
        int indice = 0;
        System.out.println(plateau);
        String rep = "";
        // tant que le sac n'est pas vide
        while (!sac.isEmpty()) {
            if (indice >= joueurs.size()) {
                indice = 0;
            }
            piocherPiece(joueurs.get(indice));
            System.out.print(joueurs.get(indice));
            if (joueurs.get(indice) instanceof DominoBot) {
                if (!((DominoBot) joueurs.get(indice)).jouerTerminal((DominoPlateauControleur) plateau)) {
                    // fait jouer le bot. Si il ne peut pas jouer la pièce
                    indice--; // il rejoue.
                }
            } else {
                System.out.println("Pensez vous pouvoir jouer ?");
                rep = sc.nextLine();
                if (rep.equals("oui")) {
                    if (((DominoPlateauControleur) plateau).possibleDePlacer(joueurs.get(indice).getMain())) {
                        System.out.println("Oui ! Vous avez effectivement une ou plusieurs solutions.");
                        ((DominoPlateauControleur) plateau).placerPiece(joueurs.get(indice), sc);
                        joueurs.get(indice).jeter();
                    } else {
                        System.out.println("Vous vous trompez, aucune solution n'est valide!");
                        joueurs.get(indice).jeter();
                        indice--;// il rejoue.
                    }
                } else {
                    if (rep.equals("non")) {
                        if (((DominoPlateauControleur) plateau).possibleDePlacer(joueurs.get(indice).getMain())) {
                            System.out.println("Cherchez bien ! Car il y a une ou des solutions!");
                            ((DominoPlateauControleur) plateau).placerPiece(joueurs.get(indice), sc);
                            joueurs.get(indice).jeter();
                        } else {
                            System.out.println("Et oui aucune solution n'est valide.");
                            joueurs.get(indice).jeter();
                            indice--;// il rejoue.
                        }
                    }
                }
            }
            indice++;
            System.out.println(plateau.afficher());
        }
        System.out.println("Bravo  à tous!!!");

        // affiche les score de tout le monde
        for (Player<PieceControleur<Integer>> p : joueurs) {
            System.out.println(p.getName() + " : " + p.getscore());
        }
    }
}
