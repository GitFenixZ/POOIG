package communs.objets.piece;

import communs.exceptions.directionInvalide;
import communs.interfaces.Carre;
import communs.interfaces.piece.InterfacePieceControleur;

/**
 * Class modélisant le controleur d'un pièce de jeu permettant les interractions
 * vue modèle.
 */
public class PieceControleur implements Carre, InterfacePieceControleur {
    PieceModel model;
    PieceView view;

    // setters

    @Override
    public void setModel(PieceModel model) {
        this.model = model;
    }

    @Override
    public void setView(PieceView view) {
        this.view = view;
    }

    // getters
    @Override
    public int[] getCote(Direction cote) {
        try {
            return model.getCote(cote);
        } catch (directionInvalide e) {
            return new int[0];
        }
    }

    @Override
    /**
     * Somme toutes la valeur d'un coté de la piece
     * 
     * @param cote cote que l'on veut sommer
     * @return La somme de toutes les valeur d'un cote
     */
    public int somme(Direction cote) {
        return model.somme(cote);
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
    public boolean comparer(Direction cote, PieceControleur piece) {
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
        return model.getligne(indice);
    }

    @Override
    /**
     * retourne un String représentant une pièce.
     */
    public String toString() {
        return view.toString();
    }
}
