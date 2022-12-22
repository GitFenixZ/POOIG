package domino;

import communs.objets.Point;
import communs.objets.plateau.PlateauModel;
import communs.exceptions.positionInvalide;
import communs.interfaces.Direction;

public class DominoPlateauModel extends PlateauModel<Integer> {
    DominoPlateauModel(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        super(hauteur, largeur, hauteurPiece, largeurPiece);
    }

    @Override
    /**
     * Methode qui permet de calculer le nombre de point que repporte une piece
     * quand elle vient d'être placer.
     * 
     * @param point position de la piece
     * @return le nombre de point que la piece fait gagner
     */
    public int calculePoint(Point point) {
        int res = 0;

        Point haut = new Point(point.getX(), point.getY() - 1);
        Point bas = new Point(point.getX(), point.getY() + 1);
        Point gauche = new Point(point.getX() - 1, point.getY());
        Point droite = new Point(point.getX() + 1, point.getY());
        // fait la somme de chaque cote qui a une piece adjacente
        try {
            if (getPiece(droite) != null) {
                res += ((DominoPieceControleur) getPiece(actuelPosition)).somme(Direction.RIGHT);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(gauche) != null) {
                res += ((DominoPieceControleur) getPiece(actuelPosition)).somme(Direction.LEFT);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(bas) != null) {
                res += ((DominoPieceControleur) getPiece(actuelPosition)).somme(Direction.DOWN);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(haut) != null) {
                res += ((DominoPieceControleur) getPiece(actuelPosition)).somme(Direction.UP);
            }
        } catch (positionInvalide e) {
        }
        return res;
    }
}
