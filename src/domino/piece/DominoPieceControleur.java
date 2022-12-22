package domino.piece;

import communs.interfaces.Direction;
import communs.objets.piece.PieceControleur;
import communs.objets.piece.PieceModel;
import communs.objets.piece.PieceView;

/**
 * Class modélisant le contoleur du domino. Qui permet les interactions vue
 * controleur.
 * Modélisant une piece de domino. C'est à dire une piece de taille 3 par 3.
 */
public class DominoPieceControleur extends PieceControleur<Integer> {
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
    public DominoPieceControleur() {
        PieceModel<Integer> model = new DominoPieceModel(5, 5);
        PieceView<Integer> view = new PieceView<Integer>();
        view.setModel(model);
        setModel(model);
        setView(view);
    }

    /**
     * Somme toutes la valeur d'un coté de la piece
     * 
     * @param cote cote que l'on veut sommer
     * @return La somme de toutes les valeur d'un cote
     */
    public int somme(Direction cote) {
        return ((DominoPieceModel) getModel()).somme(cote);
    }
}
