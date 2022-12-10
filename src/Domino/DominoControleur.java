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
    /**
     * Constructeur
     * Initialise une piece du jeu du domino de taille 5 par 5
     * C'est a dire avec 3 valeur a chaque cote.
     * Exemple :
     * ..1..1..1..
     * 1.........1
     * 1.........1
     * 1.........1
     * ..1..1..1..
     */
    public DominoControleur() {
        PieceModel model = new DominoModel(5, 5);
        PieceView view = new PieceView();
        view.setModel(model);
        setModel(model);
        setView(view);
    }
}
