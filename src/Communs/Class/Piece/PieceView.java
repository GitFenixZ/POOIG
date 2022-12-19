package Communs.Class.Piece;

import Communs.Interfaces.Piece.InterfacePieceView;

/**
 * Class modélisant la vue d'une piece de jeu.
 */
public class PieceView implements InterfacePieceView {
    PieceModel model;

    // setter
    @Override
    public void setModel(PieceModel model) {
        this.model = model;
    }

    /**
     * retourne un String représentant une pièce.
     * C'est a dire un String composé de plusieurs lignes avec tout les valeur de a
     * pièce. Et des espace la ou il faut.
     */
    @Override
    public String toString() {
        String res = "";
        for (int[] ligne : model.getValeurs()) {
            for (int colonne : ligne) {
                if (colonne == -1) {
                    res += "  ";
                } else {
                    res += colonne + " ";
                }
            }
            res += "\n";
        }
        return res;
    }

}
