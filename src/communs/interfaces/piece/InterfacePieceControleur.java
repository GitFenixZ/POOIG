package communs.interfaces.piece;

import java.util.ArrayList;

import communs.objets.Direction;
import communs.objets.piece.PieceControleur;
import communs.objets.piece.PieceModel;
import communs.objets.piece.PieceView;

/**
 * Interface du controleur des Pieces.
 * 
 * V represente le type des valeurs qui apparait sur les pièces du jeu.
 * Exemple : Integer dans le domino.
 */
public interface InterfacePieceControleur<V> {

    public void setModel(PieceModel<V> model);

    public void setView(PieceView<V> view);

    // getters

    public ArrayList<V> getCote(Direction cote);

    public int getLargeur();

    public int getHauteur();

    public PieceModel<V> getModel();

    public PieceView<V> getView();

    /**
     * Compare le coté adjacent de deux pièce.
     * Par exemple si je fait this.comparer (LEFT, piece) je vais comparer le cote
     * LEFT de la piece 'this', avec le cote RIGHT de la piece 'piece'.
     * 
     * @param cote  Le coté que je veux comparer de la pièce this.
     * @param piece Pièce avec la quel je veux comparer le coté.
     * @return Si les deux coté sont bien identique
     */
    public boolean comparer(Direction cote, PieceControleur<V> piece);

    /**
     * Methode qui tourne une piece à 90 degrès sur la droite.
     * Les valeur qui etait en haut seront à droite.
     * Les valeur qui etait a droite seront en bas.
     * Les valeur qui etait en bas seront à gauche.
     * Les valeur qui etait a gauche seront en haut.
     */
    public void tournerDroite();

    /**
     * Methode qui tourne une piece à 90 degrès sur la gauche.
     * Les valeur qui etait en haut seront à gauche.
     * Les valeur qui etait a gauche seront en bas.
     * Les valeur qui etait en bas seront à droite.
     * Les valeur qui etait a droite seront en haut.
     */
    public void tournerGauche();

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
    public String getligne(int indice);
}
