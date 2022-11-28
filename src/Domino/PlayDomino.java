package Domino;

import java.util.Scanner;

import Communs.*;

/**
 * Class modélisant une partie de domino qui se joue.
 */

public class PlayDomino {
    private Sac sac;
    private Player[] joueurs;
    private PlateauControleur plateau;

    public PlayDomino(int nombreDePiece, int nombreJoueur, int hauteurPlateau, int largeurPlateau) {
        sac = new Sac(nombreDePiece);
        for (int i = 0; i < nombreDePiece; i++) {
            sac.ajouter(new DominoControleur());
        }
        joueurs = new Player[nombreJoueur];
        for (int i = 0; i < nombreJoueur; i++) {
            joueurs[i] = new Player("" + i);
        }
        plateau = new PlateauControleur(hauteurPlateau, largeurPlateau, 5, 5);
    }

    public void piocherPiece(Player player) {
        player.piocher(sac);
    }

    public void play(Scanner sc) {
        int indice = 0;
        System.out.println(plateau);
        String rep = "";
        while (!sac.isEmpty()) {
            if (indice >= joueurs.length) {
                indice = 0;
            }
            piocherPiece(joueurs[indice]);
            System.out.print(joueurs[indice].getMain());
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

        for (Player p : joueurs) {
            System.out.println(p.getName() + " : " + p.getscore());
        }
    }
}
