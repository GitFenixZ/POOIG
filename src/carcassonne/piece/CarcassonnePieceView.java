package carcassonne.piece;

import communs.objets.piece.PieceView;

public class CarcassonnePieceView extends PieceView<Terrain> {

    @Override
    /**
     * Getter d'une ligne du tableau sous form de String
     * 
     * @param indice indice de la ligne voulu
     * @return un String representant une ligne du tableau
     */
    public String getligne(int indice) {
        String res = " ";
        for (int i = 0; i < getModel().getValeurs().size(); i++) {
            if (getModel().getValeurs().get(indice).get(i) != getModel().getVide()) {
                res += (getModel().getValeurs().get(indice).get(i)).toString() + " ";
            } else {
                if (i == 0) {
                    res += (getModel().getValeurs().get(indice).get(i + 1)).toString() + " ";
                } else {
                    res += (getModel().getValeurs().get(indice).get(i - 1)).toString() + " ";
                }
            }
        }
        return res;
    }

    /**
     * retourne un String représentant une pièce.
     * C'est a dire un String composé de plusieurs lignes avec tout les valeur de a
     * pièce. Et des espace la ou il faut.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < getModel().getValeurs().size(); i++) {
            res += getligne(i) + "\n";
        }
        return res;
    }
}
