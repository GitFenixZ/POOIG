package domino.plateau;

import communs.objets.Direction;
import communs.objets.Point;
import communs.objets.plateau.PlateauModel;
import domino.piece.DominoPieceControleur;
import communs.exceptions.positionInvalide;

/**
 * Class modélisant le controleur du plateau du jeu de domino.
 */
public class DominoPlateauModel extends PlateauModel<Integer> {
    DominoPlateauModel(int hauteurPiece, int largeurPiece) {
        super(hauteurPiece, largeurPiece);
    }

    @Override
    /**
     * Méthode qui permet de calculer le nombre de point que rapporte une pièce
     * quand elle vient d'être placer.
     * 
     * // * @param point coordonnées sur laquelle la pièce a ete placée.
     * 
     * @return le nombre de point que la pièce fait gagner
     */
    public int calculePoint(Point point) {
        int res = 0;
        // fait la somme de chaque cote qui a une pièce adjacente
        try {
            if (getPiece(new Point(point.getX() + 1, point.getY())) != null) {
                res += ((DominoPieceControleur) getPiece(point)).somme(Direction.RIGHT);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(new Point(point.getX() - 1, point.getY())) != null) {
                res += ((DominoPieceControleur) getPiece(point)).somme(Direction.LEFT);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(new Point(point.getX(), point.getY() + 1)) != null) {
                res += ((DominoPieceControleur) getPiece(point)).somme(Direction.DOWN);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(new Point(point.getX(), point.getY() - 1)) != null) {
                res += ((DominoPieceControleur) getPiece(point)).somme(Direction.UP);
            }
        } catch (positionInvalide e) {
        }
        return res;
    }
}
