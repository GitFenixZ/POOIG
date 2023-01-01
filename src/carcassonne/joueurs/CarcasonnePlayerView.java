package carcassonne.joueurs;

import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.Terrain;
import communs.objets.PlayerView;
import communs.objets.piece.PieceControleur;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View d'un joueur de carcassonne
 */
public class CarcasonnePlayerView extends PlayerView<PieceControleur<Terrain>> {

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return "Nom : " + getModel().getName() + " Score : " + getModel().getscore() + " Et a "
                + ((CarcassonnePlayerModel) getModel()).getNombreDePartisant()
                + " partisants.\nPièce en main :\n" + ((CarcassonnePieceControleur) getModel().getMain());
    }

    /**
     * Affiches les informations d'un joueur
     * 
     * @param model
     */
    public CarcasonnePlayerView(CarcassonnePlayerModel model) {
        super();
        setModel(model);
        setLayout(new GridLayout(2, 1));
        JPanel a = new JPanel();
        a.add(new JLabel(getModel().getName()));
        add(a);
        revalidate();
    }
}
