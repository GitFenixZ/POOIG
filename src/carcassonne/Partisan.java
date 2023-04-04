package carcassonne;

import java.awt.Color;

/**
 * Class représentant un partisan
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
