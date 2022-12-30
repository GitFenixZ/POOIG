package carcassonne.piece;

import communs.objets.piece.PieceView;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

public class CarcassonnePieceView extends PieceView<Terrain> {

    public CarcassonnePieceView() {
        super();
    }

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

    @Override
    /**
     * Créer un affichage correct pour la fenetre
     */
    public void setimagePiece() {
        ArrayList<ArrayList<Terrain>> valeurs = getModel().getValeurs();
        setLayout(new GridLayout(getModel().getHauteur(), 1));
        for (int i = 0; i < getModel().getHauteur(); i++) {
            String res = " ";
            for (int j = 0; j < getModel().getLargeur(); j++) {
                if (valeurs.get(i).get(j) != getModel().getVide()) {
                    res += valeurs.get(i).get(j).toString() + " ";
                } else {
                    if (j != 0) {
                        res += valeurs.get(i).get(j - 1).toString() + " ";
                    } else {
                        res += valeurs.get(i).get(j + 1).toString() + " ";
                    }
                }
            }
            add(new JLabel(res));
        }
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
