package domino.plateau;

import communs.objets.plateau.PlateauControleur;
import communs.objets.plateau.PlateauView;

/**
 * Class modélisant le controleur du plateau du jeu de domino.
 */
public class DominoPlateauControleur extends PlateauControleur<Integer> {
    public DominoPlateauControleur(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        super();
        model = new DominoPlateauModel(hauteur, largeur, hauteurPiece, largeurPiece);
        view = new PlateauView<Integer>();
        view.setModel(model);
    }
}
