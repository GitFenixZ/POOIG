package Communs;

/**
 * Class modélisant la vue d'une piece de jeu.
 */
public class PieceView {
    PieceModel model;

    public void setModel(PieceModel model) {
        this.model = model;
    }

    @Override
    public String toString() {
        String res = "";
        for (int[] ligne : model.getValeurs()) {
            for (int colonne : ligne) {
                if (colonne == -1) {
                    res += "  ";
                } else {
                    res += colonne + " ";
                }
            }
            res += "\n";
        }
        return res;
    }

}
