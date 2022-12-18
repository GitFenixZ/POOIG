package Communs;

/**
 * Interface permettant définir un objet avec 4 faces.
 */
public interface Carre {
    /**
     * Cotés possibles
     */
    enum Direction {
        LEFT, RIGHT, UP, DOWN, ACTUEL;
    }
}
