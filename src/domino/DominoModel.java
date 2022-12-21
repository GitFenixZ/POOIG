package domino;

import java.util.Random;

import communs.objets.piece.PieceModel;

public class DominoModel extends PieceModel {

    /**
     * Initialise une piece de taille hauteur par largeur.
     * Pour chaque valeur qui est sur un bords, mais qui n'est pas un coin
     * initialise une valeurs.
     * 
     * @param hauteur Hauteur de la piece
     * @param largeur Largeur de la piece
     */
    DominoModel(int hauteur, int largeur) {
        super(hauteur, largeur);
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                // si c'est une valeur sur un bords de la piece, mais ce n'est pas un coins
                if (((i == 0 || i == hauteur - 1) && j != 0 && j != largeur - 1)
                        || ((j == 0 || j == largeur - 1)) && i != 0 && i != hauteur - 1) {
                    // initialise la valeur
                    valeurs[i][j] = new Random().nextInt(2);
                }
            }
        }
    }
}
