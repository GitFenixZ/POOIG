package communs.objets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;

import communs.objets.piece.PieceControleur;

/**
 * Vue du joueur.
 */
public class PlayerView<P> extends JPanel {
    private PlayerModel<P> model;
    private PlayerControleur<P> controleur;

    public PlayerView() {
    }

    public PlayerView(PlayerModel<P> model) {
        this.model = model;
        setLayout(new GridLayout(2, 1));
        JPanel a = new JPanel();
        a.add(new JLabel(model.getName()));
        a.add(new JLabel("" + model.getscore()));
        add(a);
    }

    public PlayerModel<P> getModel() {
        return model;
    }

    public void setModel(PlayerModel<P> model) {
        this.model = model;
    }

    public void setControleur(PlayerControleur<P> controleur) {
        this.controleur = controleur;
    }

    public PlayerControleur<P> getControleur() {
        return controleur;
    }

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return "Nom : " + model.getName() + " Score : " + model.getscore() + "\nMain :\n" + model.getMain();
    }

    /**
     * reactualise les infos d'un joueur dans son affichage.
     */
    public void reactualiser() {
        removeAll();
        setLayout(new GridLayout(2, 1));
        JPanel a = new JPanel();
        a.add(new JLabel(model.getName()));
        a.add(new JLabel("" + model.getscore()));
        add(a);
        add(((PieceControleur) getModel().getMain()).getView());
        revalidate();
    }

}
