package communs.objets.piece;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import communs.interfaces.piece.InterfacePieceView;

/**
 * Class modélisant la vue d'une piece de jeu.
 * 
 * V est le types des valeur qui apparaissent sur la pièce.
 * Exemple : Integer dans le domino.
 */
public class PieceView<V> extends JPanel implements InterfacePieceView<V> {
    private PieceModel<V> model;

    public PieceView() {
        super(null);
        setVisible(true);
    }

    // setter
    @Override
    public void setModel(PieceModel<V> model) {
        this.model = model;
    }

    @Override
    /**
     * Créer un affichage de la pièce correct pour la fenêtre.
     * Et l''actualise.
     */
    public void setImagePiece() {
        removeAll();
        setLayout(new GridLayout(model.getHauteur(), model.getLargeur()));
        for (int i = 0; i < model.getHauteur(); i++) {
            for (V e : model.getValeurs().get(i)) {
                if (e != model.getVide()) {
                    JPanel texte = new JPanel(new GridBagLayout());
                    texte.add(new JLabel(e.toString() + " "));
                    texte.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    add(texte);
                } else {
                    add(new JLabel(" "));
                }
            }
        }
        revalidate();
    }

    // getter
    @Override
    public PieceModel<V> getModel() {
        return model;
    }

    /**
     * Getter d'une ligne du tableau sous form de String
     * 
     * @param indice indice de la ligne voulu
     * @return un String représentant une ligne du tableau
     */
    public String getLigne(int indice) {
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
            res += getLigne(i) + "\n";
        }
        return res;
    }
}
