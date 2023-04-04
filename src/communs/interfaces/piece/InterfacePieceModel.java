package communs.interfaces.piece;

import java.util.ArrayList;

import communs.exceptions.directionInvalide;
import communs.objets.Direction;
import communs.objets.piece.PieceModel;

/**
 * Interface du Model des Pièces.
 * 
 * V représente le type des valeurs qui apparaît sur les pièces du jeu.
 * Exemple : Integer dans le domino.
 */
public interface InterfacePieceModel<V> {

    // getters

    public int getLargeur();

    public int getHauteur();

    public V getVide();

    /**
     * Getters du tableau de valeur
     * 
     * @return une copy du tableau.
     */
    public ArrayList<ArrayList<V>> getValeurs();

    /**
     * Getter d'un côte sous forme d'un tableau contenant les valeur du côté
     * souhaite. En faisant une copie.
     * 
     * @param cote le cote dont on veut obtenir les valeurs
     * @return un tableau avec les valeur du cote
     * @throws directionInvalide Si la valeur de cote est ACTUEL.
     */
    public ArrayList<V> getCote(Direction cote) throws directionInvalide;

    /**
     * Compare le côté adjacent de deux pièce.
     * Par exemple si je fait this.comparer (LEFT, piece) je vais comparer le cote
     * LEFT de la piece 'this', avec le cote RIGHT de la piece 'piece'.
     * 
     * @param cote  Le coté que je veux comparer de la pièce this.
     * @param piece Pièce avec la quel je veux comparer le coté.
     * @return Si les deux coté sont bien identique
     * @throws directionInvalide Si la direction est ACTUEL
     */
    public boolean comparer(Direction cote, PieceModel<V> piece) throws directionInvalide;

    /**
     * Méthode qui tourne une piece à 90 degrés sur la droite.
     * Les valeur qui était en haut seront à droite.
     * Les valeur qui était a droite seront en bas.
     * Les valeur qui était en bas seront à gauche.
     * Les valeur qui était a gauche seront en haut.
     */
    public void tournerDroite();

    /**
     * Méthode qui tourne une piece à 90 degrés sur la gauche.
     * Les valeur qui était en haut seront à gauche.
     * Les valeur qui était a gauche seront en bas.
     * Les valeur qui était en bas seront à droite.
     * Les valeur qui était a droite seront en haut.
     */
    public void tournerGauche();
}
