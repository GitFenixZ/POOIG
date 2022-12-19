package Communs.Interfaces.Piece;

import Communs.Exceptions.directionInvalide;
import Communs.Interfaces.Carre.Direction;
import Communs.Class.Piece.PieceModel;

/**
 * Interface du Model des Pieces
 */
public interface InterfacePieceModel {

    // getters

    public int getLargeur();

    public int getHauteur();

    /**
     * Getters du tableau de valeur
     * 
     * @return une copy du tableau.
     */
    public int[][] getValeurs();

    /**
     * Getter d'une ligne du tableau sous form de String
     * 
     * @param indice indice de la ligne voulu
     * @return un String representant une ligne du tableau
     */
    public String getligne(int indice);

    /**
     * Getter d'un cote sous forme d'un tableau contenant les valeur du coté
     * souhaite. En faisant une copie.
     * 
     * @param cote le cote dont on veut obtenir les valeurs
     * @return un tableau avec les valeur du cote
     * @throws directionInvalide Si la valeur de cote est ACTUEL.
     */
    public int[] getCote(Direction cote) throws directionInvalide;

    /**
     * Compare le coté adjacent de deux pièce.
     * Par exemple si je fait this.comparer (LEFT, piece) je vais comparer le cote
     * LEFT de la piece 'this', avec le cote RIGHT de la piece 'piece'.
     * 
     * @param cote  Le coté que je veux comparer de la pièce this.
     * @param piece Pièce avec la quel je veux comparer le coté.
     * @return Si les deux coté sont bien identique
     * @throws directionInvalide Si la direction est ACTUEL
     */
    public boolean comparer(Direction cote, PieceModel piece) throws directionInvalide;

    /**
     * Methode qui tourne une piece à 90 degrès sur la droite.
     * Les valeur qui etait en haut seront à droite.
     * Les valeur qui etait a droite seront en bas.
     * Les valeur qui etait en bas seront à gauche.
     * Les valeur qui etait a gauche seront en haut.
     */
    public void pivotDroite();

    /**
     * Methode qui tourne une piece à 90 degrès sur la gauche.
     * Les valeur qui etait en haut seront à gauche.
     * Les valeur qui etait a gauche seront en bas.
     * Les valeur qui etait en bas seront à droite.
     * Les valeur qui etait a droite seront en haut.
     */
    public void pivotGauche();

    /**
     * Somme toutes la valeur d'un coté de la piece
     * 
     * @param cote cote que l'on veut sommer
     * @return La somme de toutes les valeur d'un cote
     */
    public int somme(Direction cote);
}
