package carcassonne;

import carcassonne.joueurs.CarcassonneBot;
import carcassonne.joueurs.CarcassonnePlayerControleur;
import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.CarcassonnePieceModel;
import carcassonne.piece.Terrain;
import communs.PlayGameModel;
import communs.objets.plateau.PlateauControleur;
import java.awt.Color;

/**
 * Class modélisant une partie de carcassonne qui se joue.
 */
public class PlayCarcassonneModel extends PlayGameModel<Terrain> {
    /**
     * Constructeur
     * Contruit une partie de Carcassonne
     * 
     * @param nombreJoueur Nombre de joueur qui vont jouer
     */
    public PlayCarcassonneModel(int nombreJoueur) {
        super(72);

        // initialise le plateau
        plateau = new PlateauControleur<Terrain>(5, 5);

        // remplis le sac de piece de Carcassonne
        for (int i = 0; i < 9; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.un, 1));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.deux, 2));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.trois, 3));
        }
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.quatre, 4));
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.cinq, 5));
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.six, 6));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.sept, 7));
        }
        for (int i = 0; i < 8; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.huit, 8));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.neuf, 9));
        }
        for (int i = 0; i < 5; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.dix, 10));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.onze, 11));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.douze, 12));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.treize, 13));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.quatroze, 14));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.quize, 15));
        }
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.seize, 16));
        }
        for (int i = 0; i < 4; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.dixsept, 17));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.dixhuit, 18));
        }
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.dixneuf, 19));
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingt, 20));
        }
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingtun, 21));
        sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingtdeux, 22));
        for (int i = 0; i < 2; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingttrois, 23));
        }
        for (int i = 0; i < 3; i++) {
            sac.ajouter(new CarcassonnePieceControleur(CarcassonnePieceModel.vingtquatre, 24));
        }
    }

    /**
     * Lance une partie complete. Du debut jusqu'a ce qu'il n'y ai plus de piece
     * dans le sac.
     * 
     * Avec interface Graphique
     */

    public void start() {
        plateau.start(sac);

    }

    /**
     * ajoute une personnage aux joueurs de la partie
     * 
     * @param nom     pseudo du personnage a ajouter
     * @param couleur couleur que l'équipe du personnage
     */
    public void ajoutPerso(String nom, Color couleur) {
        joueurs.add(new CarcassonnePlayerControleur(nom, couleur));
    }

    /**
     * ajoute une robot aux joueurs de la partie
     * 
     * @param nom     pseudo du robot a ajouter
     * @param couleur couleur que l'équipe du robot
     */
    public void ajoutBot(String nom, Color couleur) {
        joueurs.add(new CarcassonneBot(nom, couleur));

    }
}