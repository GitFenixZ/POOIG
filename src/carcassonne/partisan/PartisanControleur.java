package carcassonne.partisan;

import java.awt.Color;

/**
 * Modélise un partisan
 */
public class PartisanControleur {
    private PartisanModel model;
    private PartisanView view;

    public PartisanControleur(Color couleur) {
        model = new PartisanModel(couleur);
        view = new PartisanView(model, this);
    }

    public PartisanView getView() {
        return view;
    }

    public PartisanModel getModel() {
        return model;
    }

    public Color getCouleur() {
        return model.getCouleur();
    }
}
