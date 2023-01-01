package carcassonne.joueurs;

import java.awt.Color;
import java.util.Random;

import carcassonne.piece.Terrain;
import carcassonne.piece.CarcassonnePieceControleur;
import communs.objets.Point;
import communs.objets.plateau.PlateauControleur;

/**
 * Class modélisant un Robot capable de jouer au carcassonne de manière très
 * standart.
 */
public class CarcassonneBot extends CarcassonnePlayerControleur {

    public CarcassonneBot(String name, Color couleur) {
        super(name, couleur);
    }

    /**
     * Methode a utiliser pour jouer le tout d'un robot.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    public boolean jouer(PlateauControleur<Terrain> plateauControleur) {
        Point position = plateauControleur.peutPlacer(getMain());
        if (position != null) {
            plateauControleur.setPiece(this, position);
            Random rd = new Random();
            // Veut-il placer un partisant
            if (rd.nextInt(2) == 0 && !partisantsIsEmpty()) { // si 0, il place un partissant aléatoirement sur la
                                                              // pièce.
                ((CarcassonnePieceControleur) getMain()).placerPartisant(
                        new Point(rd.nextInt(getMain().getLargeur()), rd.nextInt(getMain().getHauteur())), this);
            } // si 1 il ne place pas de partisant
            return true;
        } else {
            return false;
        }
    }
}
