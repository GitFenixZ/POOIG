package domino;

import java.util.Random;

import communs.exceptions.directionInvalide;
import communs.objets.piece.PieceModel;
import communs.interfaces.Direction;

public class DominoPieceModel extends PieceModel<Integer> {

    /**
     * Initialise une piece de taille hauteur par largeur.
     * Pour chaque valeur qui est sur un bords, mais qui n'est pas un coin
     * initialise une valeurs.
     * 
     * @param hauteur Hauteur de la piece
     * @param largeur Largeur de la piece
     */
    DominoPieceModel(int hauteur, int largeur) {
        super(hauteur, largeur, -1);
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                // si c'est une valeur sur un bords de la piece, mais ce n'est pas un coins
                if (((i == 0 || i == hauteur - 1) && j != 0 && j != largeur - 1)
                        || ((j == 0 || j == largeur - 1)) && i != 0 && i != hauteur - 1) {
                    // initialise la valeur
                    valeurs.get(i).set(j, new Random().nextInt(2));
                }
            }
        }
    }

    /**
     * Somme toutes la valeur d'un coté de la piece
     * 
     * @param cote cote que l'on veut sommer
     * @return La somme de toutes les valeur d'un cote
     */
    public int somme(Direction cote) {
        try {
            int res = 0;
            for (int valeur : this.getCote(cote)) {
                if (valeur != vide) {
                    res += valeur;
                }
            }
            return res;
        } catch (directionInvalide e) {
            return 0;
        }
    }
}
