package Communs;

/**
 * Class modélisant le controleur d'un pièce de jeu permettant les interractions
 * vue modèle.
 */
public class PieceControleur extends Carre {
    PieceModel model;
    PieceView view;

    public void setModel(PieceModel model) {
        this.model = model;
    }

    public void setView(PieceView view) {
        this.view = view;
    }

    public int[] getCote(Direction cote) {
        return model.getCote(cote);
    }

    public int somme(Direction cote) {
        return model.somme(cote);
    }

    public boolean comparer(Direction cote, PieceControleur piece) {
        return model.comparer(cote, piece.model);
    }

    public void pivotDroite() {
        model.pivotDroite();
    }

    public void pivotGauche() {
        model.pivotGauche();
    }

    public String getligne(int indice) {
        return model.getligne(indice);
    }

    @Override
    public String toString() {
        return view.toString();
    }
}
