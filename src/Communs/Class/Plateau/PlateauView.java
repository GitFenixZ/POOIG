package Communs.Class.Plateau;

import Communs.Exceptions.positionInvalide;
import Communs.Interfaces.Carre;
import Communs.Interfaces.Plateau.InterfacePlateauView;

/**
 * Class modélisant la vue du plateau
 */
public class PlateauView implements Carre, InterfacePlateauView {
    private PlateauModel model;

    // setter
    @Override
    public void setModel(PlateauModel model) {
        this.model = model;
    }

    @Override
    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public String afficher() {
        String res = "";

        int xdepart = 0;
        int xfin = 3;
        int ydepart = 0;
        int yfin = 3;
        if (model.getActuelX() == 0) {
            xdepart++;
        }
        if (model.getActuelX() == model.getLargeur() - 1) {
            xfin--;
        }
        if (model.getActuelY() == 0) {
            ydepart++;
        }
        if (model.getActuelY() == model.getHauteur() - 1) {
            yfin--;
        }

        res += "-".repeat((model.getLargeurPiece() * 2 + 2) * (xfin - xdepart) + 1) + "\n";

        for (int y = ydepart; y < yfin; y++) {
            for (int i = 0; i < model.getLargeurPiece(); i++) {
                res += "|";
                for (int x = xdepart; x < xfin; x++) {
                    try {
                        if (model.getPiece(model.getActuelX() + x - 1, model.getActuelY() + y - 1) != null) {
                            res += model.getPiece(model.getActuelX() + x - 1, model.getActuelY() + y - 1).getligne(i)
                                    + "|";
                        } else {
                            res += " ".repeat(model.getLargeurPiece() * 2 + 1) + "|";
                        }
                    } catch (positionInvalide e) {
                    }
                }
                res += "\n";
            }
            res += "-".repeat((model.getLargeurPiece() * 2 + 2) * (xfin - xdepart) + 1)
                    + "\n";
        }
        return res;
    }

    /**
     * Affiche l'intégralité des pièce du tableau.
     * Ligne par ligne.
     */
    @Override
    public String toString() {
        String[] affichage = new String[model.getHauteur() * model.getHauteurPiece()];
        for (int i = 0; i < model.getHauteur(); i++) {
            for (int j = 0; j < model.getLargeur(); j++) {
                for (int k = 0; k < model.getHauteurPiece(); k++) {
                    if (affichage[i * model.getHauteurPiece() + k] == null) {
                        affichage[i * model.getHauteurPiece() + k] = "";
                    }
                    try {
                        if (model.getPiece(j, i) != null) {
                            affichage[i * model.getHauteurPiece() + k] += model.getPiece(j, i).getligne(k) + "|";
                        } else {
                            affichage[i * model.getHauteurPiece() + k] += " ".repeat(model.getLargeurPiece() * 2 + 1)
                                    + "|";
                        }
                    } catch (positionInvalide e) {
                    }
                }
            }
        }
        String res = "-".repeat((model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                + "\n";
        for (int i = 0; i < affichage.length; i++) {
            res += "|" + affichage[i] + "\n";
            if (i % model.getHauteurPiece() == 4) {
                res += "-".repeat((model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                        + "\n";
            }
        }
        return res;
    }
}
