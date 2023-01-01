package communs.objets.piece;

import java.util.ArrayList;

import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.CarcassonnePieceView;
import communs.exceptions.directionInvalide;
import communs.interfaces.piece.InterfacePieceControleur;
import communs.objets.Direction;

/**
 * Class modélisant le controleur d'un pièce de jeu permettant les interractions
 * vue modèle.
 * 
 * V est le types des valeur qui apparaissent sur la pièce.
 * Exemple : Integer dans le domino.
 */
public class PieceControleur<V> implements InterfacePieceControleur<V> {
    private PieceModel<V> model;
    private PieceView<V> view;

    public PieceControleur() {
    }

    PieceControleur(PieceControleur<V> pieceControleur) {
        this.model = new PieceModel<V>(pieceControleur.model);
        this.view = pieceControleur.view;
    }

    // setters

    @Override
    public void setModel(PieceModel<V> model) {
        this.model = model;
    }

    @Override
    public void setView(PieceView<V> view) {
        this.view = view;
        view.setimagePiece();
    }

    // getters
    @Override
    public ArrayList<V> getCote(Direction cote) {
        try {
            return model.getCote(cote);
        } catch (directionInvalide e) {
            return new ArrayList<V>();
        }
    }

    @Override
    public int getLargeur() {
        return model.getLargeur();
    }

    @Override
    public int getHauteur() {
        return model.getHauteur();
    }

    @Override
    public PieceModel<V> getModel() {
        return model;
    }

    @Override
    public PieceView<V> getView() {
        return view;
    }

    @Override
    /**
     * Compare le coté adjacent de deux pièce.
     * Par exemple si je fait this.comparer (LEFT, piece) je vais comparer le cote
     * LEFT de la piece 'this', avec le cote RIGHT de la piece 'piece'.
     * 
     * @param cote  Le coté que je veux comparer de la pièce this.
     * @param piece Pièce avec la quel je veux comparer le coté.
     * @return Si les deux coté sont bien identique
     */
    public boolean comparer(Direction cote, PieceControleur<V> piece) {
        try {
            return model.comparer(cote, piece.model);
        } catch (directionInvalide e) {
            return false;
        }
    }

    @Override
    /**
     * Methode qui tourne une piece à 90 degrès sur la droite.
     * Les valeur qui etait en haut seront à droite.
     * Les valeur qui etait a droite seront en bas.
     * Les valeur qui etait en bas seront à gauche.
     * Les valeur qui etait a gauche seront en haut.
     */
    public void pivotDroite() {
        model.pivotDroite();
        view.setimagePiece();
    }

    @Override
    /**
     * Methode qui tourne une piece à 90 degrès sur la gauche.
     * Les valeur qui etait en haut seront à gauche.
     * Les valeur qui etait a gauche seront en bas.
     * Les valeur qui etait en bas seront à droite.
     * Les valeur qui etait a droite seront en haut.
     */
    public void pivotGauche() {
        model.pivotGauche();
        view.setimagePiece();
    }

    @Override
    /**
     * Creer un String avec les elements de la lignes d'indice 'indice' Si ce n'est
     * pas la premiere ou la dernière ligne, elle mettera des espaces à la places de
     * endroit ou il n'y a pas de numéro.
     * Si c'est la premiere ou la derniere ligne elle fera un string avec tout les
     * valeurs.
     * 
     * @param indice indice de la ligne a recuperer
     * @return une String avec les element de la ligne indice
     */
    public String getligne(int indice) {
        return view.getligne(indice);
    }

    @Override
    /**
     * retourne un String représentant une pièce.
     */
    public String toString() {
        return view.toString();
    }
}
