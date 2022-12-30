package communs.interfaces.plateau;

import communs.objets.plateau.PlateauModel;

/**
 * Interface de la vue du Plateau de jeu.
 * 
 * V est le types des valeur qui apparaissent sur les pièces qui
 * pourront être placé sur le plateau.
 * Exemple : Integer dans le domino.
 */
public interface InterfacePlateauView<V> {
    // setter
    public void setModel(PlateauModel<V> model);

    public void setPiece();

    /**
     * Raffraichi l'affichage graphique.
     */
    public void refreshGridLayout();

    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public String afficher();
}
