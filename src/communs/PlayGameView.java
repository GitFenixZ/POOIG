package communs;

import javax.swing.JPanel;

/**
 * Vue d'une partie
 */
public class PlayGameView<V> extends JPanel {
    private PlayGameModel<V> model;

    public PlayGameView(PlayGameModel<V> model) {
        super();
        this.model = model;
        add(this.model.getImagePlateau());
        setVisible(true);
    }

    public void setModel(PlayGameModel<V> model) {
        this.model = model;
    }
}
