package domino;

import communs.objets.plateau.PlateauControleur;
import communs.objets.plateau.PlateauView;

public class DominoPlateauControleur extends PlateauControleur<Integer> {
    DominoPlateauControleur(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        super();
        model = new DominoPlateauModel(hauteur, largeur, hauteurPiece, largeurPiece);
        view = new PlateauView<Integer>();
        view.setModel(model);
    }
}
