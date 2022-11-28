package Domino;

import Communs.PieceControleur;
import Communs.PieceModel;
import Communs.PieceView;

/**
 * Class modélisant le contoleur du domino. Qui permet les interactions vue
 * controleur.
 * Modélisant une piece de domino. C'est à dire une piece de taille 3 par 3.
 */
public class DominoControleur extends PieceControleur {

    public DominoControleur() {
        PieceModel model = new PieceModel(3, 3);
        PieceView view = new PieceView();
        view.setModel(model);
        setModel(model);
        setView(view);
    }
}
