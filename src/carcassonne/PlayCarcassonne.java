package carcassonne;

import java.awt.Color;
import java.util.Scanner;

import carcassonne.joueurs.CarcassonneBot;
import carcassonne.joueurs.CarcassonnePlayer;
import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.CarcassonnePieceModel;
import carcassonne.piece.Terrain;
import communs.PlayGame;
import communs.objets.Player;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;

/**
 * Class modélisant une partie de carcassonne qui se joue.
 */
public class PlayCarcassonne extends PlayGame<Terrain> {
    /**
     * Constructeur
     * 
     * @param nombreDePiece  Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur   Nombre de joueur qui vont jouer
     * @param hauteurPlateau Hauteur du plateau de jeu
     * @param largeurPlateau Largeur du plateau de jeu
     */
    public PlayCarcassonne(int nombreDePiece, int nombreJoueur, int hauteurPlateau, int largeurPlateau, Scanner sc) {
        super(nombreDePiece, hauteurPlateau, largeurPlateau);

        // initialise le plateau
        plateau = new PlateauControleur<Terrain>(hauteurPlateau, largeurPlateau, 5, 5);
        // intialise les joueurs

        // Faire un sorte que les joueurs puissent choisir leur couleur et si ils sont
        // des Robots
        for (int i = 0; i < nombreJoueur; i++) {
            System.out.println("Est ce une joueur ?");
            if (sc.nextLine().equals("oui")) {
                System.out.println("Comment s'appel le joueur " + (i + 1) + " ?");
                joueurs.add(new CarcassonnePlayer(sc.nextLine(), Color.BLUE));
            } else {
                joueurs.add(new CarcassonneBot("Joueur" + i, Color.RED));
            }
        }

        // remplis le sac de piece de Carcassonne
        for (int i = 0; i < 9; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.un));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.deux));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.trois));
        }
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.quatre));
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.cinq));
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.six));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.sept));
        }
        for (int i = 0; i < 8; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.huit));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.neuf));
        }
        for (int i = 0; i < 5; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.dix));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.onze));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.douze));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.treize));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.quatroze));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.quize));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.seize));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.dixsept));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.dixhuit));
        }
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.dixneuf));
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingt));
        }
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingtun));
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingtdeux));
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingttrois));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingtquatre));
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

        // tant que le sac n'est pas vide
        int tours = 0;
        while (!sac.isEmpty() && tours < 10) {
            tours++;
            if (indice >= joueurs.size()) {
                indice = 0;
            }
            if (!((CarcassonnePlayer) joueurs.get(indice)).partisantsIsEmpty()) {
                // si le joueur à toujours des partisant, il peut jouer son tour.
                piocherPiece(joueurs.get(indice));
                System.out.print(joueurs.get(indice));
                if (joueurs.get(indice) instanceof CarcassonneBot) {
                    if (!((CarcassonneBot) joueurs.get(indice)).jouer(plateau)) {
                        // fait jouer le bot. Si il ne peut pas jouer la pièce
                        indice--; // il rejoue.
                    }
                } else {
                    // c'est un joueur donc à gérer dans la fenetre
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