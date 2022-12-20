package Communs.Interfaces.Plateau;

import Communs.Class.Plateau.PlateauModel;

/**
 * Interface de la vue du Plateau de jeu.
 */
public interface InterfacePlateauView {
    // setter
    public void setModel(PlateauModel model);

    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public String afficher();
}
