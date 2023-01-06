package communs.interfaces;

/**
 * Répresente un point avec deux coordonnées. Sur un plan 2D
 */
public interface InterfacePoint {

    // getters
    public int getX();

    public int getY();

    public void setX(int x);

    public void setY(int y);

    /**
     * Permet de se déplacer le point a droite.
     */
    public void allerADroite();

    /**
     * Permet de se déplacer le point a gauche.
     */
    public void allerAGauche();

    /**
     * Permet de se déplacer le point en bas.
     */
    public void allerEnBas();

    /**
     * Permet de se déplacer le point en haut.
     */
    public void allerEnHaut();

}
