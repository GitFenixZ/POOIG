package carcassonne.partisan;

import javax.swing.JPanel;

public class PartisanView extends JPanel {
    private PartisanModel model;
    private PartisanControleur controleur;

    PartisanView(PartisanModel model, PartisanControleur controleur) {
        this.model = model;
        this.controleur = controleur;
    }

}
