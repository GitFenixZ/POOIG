package carcassonne.joueurs;

import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.Terrain;
import communs.objets.Point;
import communs.objets.plateau.PlateauControleur;
import java.awt.Color;
import java.util.Random;

/**
 * Modelise un robot qui peut jouer a carcassonne.
 */
public class CarcassonneBotModel extends CarcassonnePlayerModel {

    CarcassonneBotModel(String nom, Color couleur) {
        super(nom, couleur);
    }

    public boolean jouer(PlateauControleur<Terrain> plateauControleur, CarcassonneBotControleur botActuelControleur) {
        Point position = plateauControleur.peutPlacer(getMain());
        if (position != null) {
            plateauControleur.setPiece(botActuelControleur, position);
            Random rd = new Random();
            // Veut-il placer un partisant
            if (rd.nextInt(2) == 0 && !partisantsIsEmpty()) { // si 0, il place un partissant aléatoirement sur la
                                                              // pièce.
                ((CarcassonnePieceControleur) getMain()).placerPartisant(
                        new Point(rd.nextInt(getMain().getLargeur()), rd.nextInt(getMain().getHauteur())),
                        botActuelControleur);
            } // si 1 il ne place pas de partisant
            return true;
        } else {
            return false;
        }
    }
}
