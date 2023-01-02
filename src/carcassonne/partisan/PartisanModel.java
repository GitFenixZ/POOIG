package carcassonne.partisan;

import java.awt.Color;

public class PartisanModel {
    private Color couleur;

    PartisanModel(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }
}
