package communs.objets.piece;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import communs.interfaces.piece.InterfacePieceView;

/**
 * Class modélisant la vue d'une piece de jeu.
 * 
 * V est le types des valeur qui apparaissent sur la pièce.
 * Exemple : Integer dans le domino.
 */
public class PieceView<V> implements InterfacePieceView<V> {
    private PieceModel<V> model;
    private final JPanel imagePiece;

    public PieceView() {
        imagePiece = new JPanel();
    }

    // setter
    @Override
    public void setModel(PieceModel<V> model) {
        this.model = model;
    }

    @Override
    /**
     * Créer un affichage correct pour la fenetre
     */
    public void setimagePiece() {
        imagePiece.setLayout(new GridLayout(model.getHauteur(), 1));
        for (int i = 0; i < model.getHauteur(); i++) {
            String res = " ";
            for (V e : model.getValeurs().get(i)) {
                if (e != model.getVide()) {
                    res += e.toString() + " ";
                } else {
                    res += "   ";
                }
            }
            imagePiece.add(new JLabel(res));
        }
    }

    // getter
    @Override
    public PieceModel<V> getModel() {
        return model;
    }

    @Override
    public JPanel getImagePiece() {
        return imagePiece;
    }

    /**
     * Getter d'une ligne du tableau sous form de String
     * 
     * @param indice indice de la ligne voulu
     * @return un String representant une ligne du tableau
     */
    public String getligne(int indice) {
        String res = " ";
        for (V e : model.getValeurs().get(indice)) {
            if (e != model.getVide()) {
                res += e.toString() + " ";
            } else {
                res += "  ";
            }
        }
        return res;
    }

    /**
     * retourne un String représentant une pièce.
     * C'est a dire un String composé de plusieurs lignes avec tout les valeur de a
     * pièce. Et des espace la ou il faut.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < model.getValeurs().size(); i++) {
            res += getligne(i) + "\n";
        }
        return res;
    }
}
