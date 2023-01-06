package carcassonne.joueurs;

import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.Terrain;
import communs.objets.Point;
import communs.objets.plateau.PlateauControleur;
import java.awt.Color;
import java.util.Random;

/**
 * Modélise un robot qui peut jouer a carcassonne.
 */
public class CarcassonneBotModel extends CarcassonnePlayerModel {

    CarcassonneBotModel(String nom, Color couleur) {
        super(nom, couleur);
    }

    public boolean jouer(PlateauControleur<Terrain> plateauControleur, CarcassonneBotControleur botActuelControleur) {
        Point position = plateauControleur.getEmplacementPossible(getMain());
        if (position != null) {
            plateauControleur.setPiece(botActuelControleur, position);
            Random rd = new Random();
            // Veut-il placer un partisan
            if (!partisansIsEmpty() && rd.nextInt(2) == 0) { // si 0, il place un partisan aléatoirement sur la
                                                             // pièce.
                ((CarcassonnePieceControleur) getMain()).placerpartisan(
                        new Point(rd.nextInt(getMain().getLargeur()), rd.nextInt(getMain().getHauteur())),
                        botActuelControleur);
            } // si 1 il ne place pas de partisan
            return true;
        } else {
            return false;
        }
    }
}
