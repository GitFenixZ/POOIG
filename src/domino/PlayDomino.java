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
    public PlayDomino(int nombreDePiece, int nombreJoueur, Scanner sc) {
        super(nombreDePiece);

        // initialise le plateau
        plateau = new DominoPlateauControleur(5, 5);
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
     * Se joue dans le termianal.
     * 
     * @param sc System.in permettra de lire la reponse de l'utilisateur
     */
    public void playTerminal(Scanner sc) {
        if (!sac.isEmpty()) {
            plateau.start(sac);
        }
        System.out.println(plateau);
        String rep = "";
        // tant que le sac n'est pas vide
        while (!sac.isEmpty()) {
            piocherPiece(joueurs.get(getIndice()));
            System.out.print(joueurs.get(getIndice()));
            if (joueurs.get(getIndice()) instanceof DominoBot) {
                if (!((DominoBot) joueurs.get(getIndice())).jouerTerminal((DominoPlateauControleur) plateau)) {
                    // fait jouer le bot. Si il ne peut pas jouer la pièce
                    rejouer();
                    ; // il rejoue.
                }
            } else {
                System.out.println("Pensez vous pouvoir jouer ?");
                rep = sc.nextLine();
                if (rep.equals("oui")) {
                    if (((DominoPlateauControleur) plateau).possibleDePlacer(joueurs.get(getIndice()).getMain())) {
                        System.out.println("Oui ! Vous avez effectivement une ou plusieurs solutions.");
                        ((DominoPlateauControleur) plateau).placerPiece(joueurs.get(getIndice()), sc);
                        joueurs.get(getIndice()).jeter();
                    } else {
                        System.out.println("Vous vous trompez, aucune solution n'est valide!");
                        joueurs.get(getIndice()).jeter();
                        rejouer();// il rejoue.
                    }
                } else {
                    if (rep.equals("non")) {
                        if (((DominoPlateauControleur) plateau).possibleDePlacer(joueurs.get(getIndice()).getMain())) {
                            System.out.println("Cherchez bien ! Car il y a une ou des solutions!");
                            ((DominoPlateauControleur) plateau).placerPiece(joueurs.get(getIndice()), sc);
                            joueurs.get(getIndice()).jeter();
                        } else {
                            System.out.println("Et oui aucune solution n'est valide.");
                            joueurs.get(getIndice()).jeter();
                            rejouer();// il rejoue.
                        }
                    }
                }
            }
            nextPlayer();
            System.out.println(plateau.afficher());
        }
        System.out.println("Bravo  à tous!!!");

        // affiche les score de tout le monde
        for (Player<PieceControleur<Integer>> p : joueurs) {
            System.out.println(p.getName() + " : " + p.getscore());
        }
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Avec interface Graphique
     */
    public void play() {
        if (!sac.isEmpty()) {
            plateau.start(sac);
        }
        // tant que le sac n'est pas vide
        while (!sac.isEmpty()) {
            piocherPiece(joueurs.get(getIndice()));
            System.out.print(joueurs.get(getIndice()));
            if (joueurs.get(getIndice()) instanceof DominoBot) {
                if (!((DominoBot) joueurs.get(getIndice())).jouerTerminal((DominoPlateauControleur) plateau)) {
                    // fait jouer le bot. Si il ne peut pas jouer la pièce
                    this.rejouer(); // il rejoue.
                }
            } else {
                joueurs.get(getIndice()).jouer(this);
            }
            this.nextPlayer();
        }
        System.out.println("Bravo  à tous!!!");
    }
}
