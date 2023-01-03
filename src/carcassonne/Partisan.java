package carcassonne;

import java.awt.Color;

/**
 * Class representant un partisant
 */
public class Partisan {

    /**
     * Couleur
     */
    private Color couleur;

    public Partisan(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }
}
