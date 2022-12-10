package Domino;

import java.util.Scanner;

import Communs.*;

/**
 * Class modélisant une partie de domino qui se joue.
 */

public class PlayDomino {
    /**
     * Sac de piece de la partie
     */
    private Sac sac;
    /**
     * Liste des joueurs autour de la table
     */
    private Player[] joueurs;
    /**
     * Plateau de jeu pour la partie en cours
     */
    private PlateauControleur plateau;

    /**
     * Constructeur
     * 
     * @param nombreDePiece  Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur   Nombre de joueur qui vont jouer
     * @param hauteurPlateau Hauteur du plateau de jeu
     * @param largeurPlateau Largeur du plateau de jeu
     */
    public PlayDomino(int nombreDePiece, int nombreJoueur, int hauteurPlateau, int largeurPlateau, Scanner sc) {
        sac = new Sac(nombreDePiece);
        // remplis le sac de piece de domino
        for (int i = 0; i < nombreDePiece; i++) {
            sac.ajouter(new DominoControleur());
        }
        joueurs = new Player[nombreJoueur];
        // intialise les joueurs
        for (int i = 0; i < nombreJoueur; i++) {
            System.out.println("Comment s'appel le joueur " + (i + 1) + " ?");
            joueurs[i] = new Player(sc.nextLine());
        }
        // initialise le plateau
        plateau = new PlateauControleur(hauteurPlateau, largeurPlateau, 5, 5);
    }

    /**
     * Methode qui permet de faire piocher un joueur dans le sac
     * 
     * @param player joueur a faire piocher
     */
    public void piocherPiece(Player player) {
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
            if (indice >= joueurs.length) {
                indice = 0;
            }
            piocherPiece(joueurs[indice]);
            System.out.print(joueurs[indice]);
            System.out.println("Pensez vous pouvoir jouer ?");
            rep = sc.nextLine();
            if (rep.equals("oui")) {
                if (plateau.possibleDePlacer(joueurs[indice].getMain())) {
                    System.out.println("Oui ! Vous avez effectivement une ou plusieurs solutions.");
                    plateau.placerPiece(joueurs[indice], sc);
                } else {
                    System.out.println("Vous vous trompez, aucune solution n'est valide!");
                }
            } else {
                if (rep.equals("non")) {
                    if (plateau.possibleDePlacer(joueurs[indice].getMain())) {
                        System.out.println("Cherchez bien ! Car il y a une ou des solutions!");
                        plateau.placerPiece(joueurs[indice], sc);
                    } else {
                        System.out.println("Et oui aucune solution n'est valide.");
                    }
                }
            }
            joueurs[indice].jeter();
            indice++;
            System.out.println(plateau.afficher());
        }
        System.out.println("Bravo  à tous!!!");

        // affiche les score de tout le monde
        for (Player p : joueurs) {
            System.out.println(p.getName() + " : " + p.getscore());
        }
    }
}
