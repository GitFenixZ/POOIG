package communs.objets;

import communs.interfaces.InterfacePoint;

/**
 * Représente un point avec deux coordonnées.
 */
public class Point implements InterfacePoint {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    /**
     * Permet de se déplacer le point a droite.
     */
    public void allerADroite() {
        x++;
    }

    @Override
    /**
     * Permet de se déplacer le point a gauche.
     */
    public void allerAGauche() {
        x--;
    }

    @Override
    /**
     * Permet de se déplacer le point en bas.
     */
    public void allerEnBas() {
        y++;
    }

    @Override
    /**
     * Permet de se déplacer le point en haut.
     */
    public void allerEnHaut() {
        y--;
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }
}
