package communs.interfaces.plateau;

import communs.exceptions.directionInvalide;
import communs.exceptions.positionInvalide;
import communs.interfaces.Direction;

/**
 * Interface qui permet d'etendre un tableau en 2D.
 * 
 * P represente le type des des element qui seront placer dans le tableau.
 * Exemple : PieceControleur<Integer> pour le domino.
 * Est généralisé pour répondre a un problème plus grands.
 */
public interface InterfaceExtendable<P> {

    // getters
    public int getActuelX();

    public int getActuelY();

    public int getHauteur();

    public int getLargeur();

    /**
     * Getter permettant d'obtenir l'objet de la colonne x et de la ligne y du
     * tableau.
     * 
     * @param x colonne
     * @param y ligne
     * @return la piece de la colonne x et de la ligne y
     * @throws positionInvalide Si la valeur les coorodonnees sont a l'exterieure du
     *                          tableau.
     */
    public P getPiece(int x, int y) throws positionInvalide;

    /**
     * Setter permettant de placer l'objet sur la colonne x et de la ligne y du
     * tableau.
     * 
     * @param x     colonne
     * @param y     ligne
     * @param piece La piece a placer
     * @throws positionInvalide Si la valeur les coorodonnees sont a l'exterieure du
     *                          tableau.
     */
    public void setPiece(int x, int y, P piece) throws positionInvalide;

    /**
     * Permet de se deplacer d'une colonne sur la droite sur le tableau.
     * 
     * @throws positionInvalide Si la valeur de actuelX sort du tableau.
     */
    public void allerADroite() throws positionInvalide;

    /**
     * Permet de se deplacer d'une colonne sur la gauche sur le tableau.
     * 
     * @throws positionInvalide Si la valeur de actuelX sort du tableau.
     */
    public void allerAGauche() throws positionInvalide;

    /**
     * Permet de se deplacer d'une ligne vers le bas sur le tableau.
     * 
     * @throws positionInvalide Si la valeur de actuelY sort du tableau.
     */
    public void allerEnBas() throws positionInvalide;

    /**
     * Permet de se deplacer d'une ligne vers le haut sur le tableau.
     * 
     * @throws positionInvalide Si la valeur de actuelY sort du tableau.
     */
    public void allerEnHaut() throws positionInvalide;

    /**
     * Verifie si le tableau est vide
     * 
     * @return true si le tableau est vide,false si il y a au moins une piece.
     */
    public boolean isEmpty();

    /**
     * Permet d'etendre le tableau du plateau dans une direction donnee
     * 
     * @param dir cote que l'on souhaite agrandir.
     * @throws directionInvalide Si la direction est ACTUEL
     */
    public void ajouterUnCote(Direction dir) throws directionInvalide;
}