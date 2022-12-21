package domino;

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
     * @param x la colonne sur laquel la piece a ete placee
     * @param y la ligne sur laquel la piece a ete placee
     * @return le nombre de point que la piece fait gagner
     */
    public int calculePoint(int x, int y) {
        int res = 0;
        // fait la somme de chaque cote qui a une piece adjacente
        try {
            if (getPiece(x + 1, y) != null) {
                res += ((DominoPieceControleur) getPiece(x + 1, y)).somme(Direction.LEFT);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(x - 1, y) != null) {
                res += ((DominoPieceControleur) getPiece(x - 1, y)).somme(Direction.RIGHT);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(x, y + 1) != null) {
                res += ((DominoPieceControleur) getPiece(x, y + 1)).somme(Direction.UP);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(x, y - 1) != null) {
                res += ((DominoPieceControleur) getPiece(x, y - 1)).somme(Direction.DOWN);
            }
        } catch (positionInvalide e) {
        }
        return res;
    }
}
