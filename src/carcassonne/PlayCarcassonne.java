package carcassonne;

import java.util.ArrayList;
import java.util.Scanner;

import communs.objets.Player;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;

/**
 * Class modélisant une partie de carcassonne qui se joue.
 */
public class PlayCarcassonne {
    /* Sac de piece de la partie */
    private Sac<PieceControleur<Terrain>> sac;
    /**
     * Liste des joueurs autour de la table
     */
    private ArrayList<Player<PieceControleur<Terrain>>> joueurs;
    /**
     * Plateau de jeu pour la partie en cours
     */
    private PlateauControleur<Terrain> plateau;

    /**
     * Constructeur
     * 
     * @param nombreDePiece  Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur   Nombre de joueur qui vont jouer
     * @param hauteurPlateau Hauteur du plateau de jeu
     * @param largeurPlateau Largeur du plateau de jeu
     */
    public PlayCarcassonne(int nombreDePiece, int nombreJoueur, int hauteurPlateau, int largeurPlateau, Scanner sc) {
        sac = new Sac<PieceControleur<Terrain>>(nombreDePiece);
        // remplis le sac de piece de domino
        for (int i = 0; i < 9; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.un));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.deux));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.trois));
        }
        sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.quatre));
        sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.cinq));
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.six));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.sept));
        }
        for (int i = 0; i < 8; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.huit));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.neuf));
        }
        for (int i = 0; i < 5; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.dix));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.onze));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.douze));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.treize));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.quatroze));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.quize));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.seize));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.dixsept));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.dixhuit));
        }
        sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.dixneuf));
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.vingt));
        }
        sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.vingtun));
        sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.vingtdeux));
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.vingttrois));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcasonnePieceControleur(CarcassonnePieceModel.vingtquatre));
        }

        joueurs = new ArrayList<Player<PieceControleur<Terrain>>>();
        // intialise les joueurs
        for (int i = 0; i < nombreJoueur; i++) {
            System.out.println("Est ce une joueur ?");
            if (sc.nextLine().equals("oui")) {
                System.out.println("Comment s'appel le joueur " + (i + 1) + " ?");
                joueurs.add(new CarcassonnePlayer(sc.nextLine()));
            } else {
                joueurs.add(new CarcassonneBot("Joueur" + i));
            }
        }
        // initialise le plateau
        plateau = new PlateauControleur<Terrain>(hauteurPlateau, largeurPlateau, 5, 5);
    }

    /**
     * Methode qui permet de faire piocher un joueur dans le sac
     * 
     * @param player joueur a faire piocher
     */
    public void piocherPiece(Player<PieceControleur<Terrain>> player) {
        player.piocher(sac);
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
            if (joueurs.get(indice) instanceof CarcassonneBot) {
                if (!((CarcassonneBot) joueurs.get(indice)).jouer(plateau)) { // fait jouer le bot. Si il ne peut pas
                                                                              // jouer
                    // la pièce
                    indice--; // il rejoue.
                }
            } else {
                System.out.println("Pensez vous pouvoir jouer ?");
                rep = sc.nextLine();
                if (rep.equals("oui")) {
                    if (plateau.possibleDePlacer(joueurs.get(indice).getMain())) {
                        System.out.println("Oui ! Vous avez effectivement une ou plusieurs solutions.");
                        plateau.placerPiece(joueurs.get(indice), sc);
                        joueurs.get(indice).jeter();
                    } else {
                        System.out.println("Vous vous trompez, aucune solution n'est valide!");
                        joueurs.get(indice).jeter();
                        indice--;// il rejoue.
                    }
                } else {
                    if (rep.equals("non")) {
                        if (plateau.possibleDePlacer(joueurs.get(indice).getMain())) {
                            System.out.println("Cherchez bien ! Car il y a une ou des solutions!");
                            plateau.placerPiece(joueurs.get(indice), sc);
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
        for (Player<PieceControleur<Terrain>> p : joueurs) {
            System.out.println(p.getName() + " : " + p.getscore());
        }
    }
}