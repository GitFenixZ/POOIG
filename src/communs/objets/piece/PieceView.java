package communs.objets.piece;

import java.util.ArrayList;

import communs.interfaces.piece.InterfacePieceView;

/**
 * Class modélisant la vue d'une piece de jeu.
 * 
 * V est le types des valeur qui apparaissent sur la pièce.
 * Exemple : Integer dans le domino.
 */
public class PieceView<V> implements InterfacePieceView<V> {
    protected PieceModel<V> model;

    // setter
    @Override
    public void setModel(PieceModel<V> model) {
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
        for (ArrayList<V> ligne : model.getValeurs()) {
            for (V colonne : ligne) {
                if (colonne == model.vide) {
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
