package domino.plateau;

import communs.objets.plateau.PlateauControleur;
import communs.objets.plateau.PlateauView;

/**
 * Class controlant le plateau du jeu de domino.
 */
public class DominoPlateauControleur extends PlateauControleur<Integer> {
    public DominoPlateauControleur(int hauteurPiece, int largeurPiece) {
        super();
        model = new DominoPlateauModel(hauteurPiece, largeurPiece);
        view = new PlateauView<Integer>(this);
        view.setModel(model);
    }
}
