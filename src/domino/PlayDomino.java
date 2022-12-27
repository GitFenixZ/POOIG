package domino;

import java.util.ArrayList;
import java.util.Scanner;

import communs.objets.Player;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;

/**
 * Class modélisant une partie de domino qui se joue.
 */

public class PlayDomino {
    /**
     * Sac de piece de la partie
     */
    private Sac<PieceControleur<Integer>> sac;
    /**
     * Liste des joueurs autour de la table
     */
    private ArrayList<Player<PieceControleur<Integer>>> joueurs;
    /**
     * Plateau de jeu pour la partie en cours
     */
    private DominoPlateauControleur plateau;

    /**
     * Constructeur
     * 
     * @param nombreDePiece  Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur   Nombre de joueur qui vont jouer
     * @param hauteurPlateau Hauteur du plateau de jeu
     * @param largeurPlateau Largeur du plateau de jeu
     */
    public PlayDomino(int nombreDePiece, int nombreJoueur, int hauteurPlateau, int largeurPlateau, Scanner sc) {
        sac = new Sac<PieceControleur<Integer>>(nombreDePiece);
        // remplis le sac de piece de domino
        for (int i = 0; i < nombreDePiece; i++) {
            sac.ajouter(new DominoPieceControleur());
        }
        joueurs = new ArrayList<Player<PieceControleur<Integer>>>();
        // intialise les joueurs
        for (int i = 0; i < nombreJoueur; i++) {
            System.out.println("Comment s'appel le joueur " + (i + 1) + " ?");
            joueurs.add(new Player<PieceControleur<Integer>>(sc.nextLine()));
        }
        // initialise le plateau
        plateau = new DominoPlateauControleur(hauteurPlateau, largeurPlateau, 5, 5);
    }

    /**
     * Methode qui permet de faire piocher un joueur dans le sac
     * 
     * @param player joueur a faire piocher
     */
    public void piocherPiece(Player<PieceControleur<Integer>> player) {
        player.piocher(sac);
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * @param sc System.in permettra de lire la reponse de l'utilisateur
     */
    public void play(Scanner sc) {
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
            System.out.println("Pensez vous pouvoir jouer ?");
            rep = sc.nextLine();
            if (rep.equals("oui")) {
                if (plateau.possibleDePlacer(joueurs.get(indice).getMain())) {
                    System.out.println("Oui ! Vous avez effectivement une ou plusieurs solutions.");
                    plateau.placerPiece(joueurs.get(indice), sc);
                } else {
                    System.out.println("Vous vous trompez, aucune solution n'est valide!");
                }
            } else {
                if (rep.equals("non")) {
                    if (plateau.possibleDePlacer(joueurs.get(indice).getMain())) {
                        System.out.println("Cherchez bien ! Car il y a une ou des solutions!");
                        plateau.placerPiece(joueurs.get(indice), sc);
                    } else {
                        System.out.println("Et oui aucune solution n'est valide.");
                    }
                }
            }
            joueurs.get(indice).jeter();
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
