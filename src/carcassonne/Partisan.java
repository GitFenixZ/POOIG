package carcassonne;

import java.awt.Color;

/**
 * Modélise un partisan
 */
public class Partisan {

    private Color couleur;

    public Partisan(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }
}
