import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * Class permettant de voir une partis
 */
public class MenuView extends JPanel {
    private MenuModel model;
    private MenuControleur controleur;
    private JPanel menu;

    public void setControleur(MenuControleur controleur) {
        this.controleur = controleur;
    }

    MenuView(MenuModel model) {
        super();
        this.model = model;

        menu = new JPanel();
        JButton playDominoTerminal = new JButton("Domino Terminal");
        JButton playDomino = new JButton("Domino");
        JButton playCarcassonne = new JButton("Carcassonne");
        playDominoTerminal.setVisible(true);
        playDomino.setVisible(true);
        playCarcassonne.setVisible(true);
        menu.add(playDominoTerminal);
        menu.add(playDomino);
        menu.add(playCarcassonne);
        add(menu);

        playDominoTerminal.addActionListener(event -> controleur.PlayDominoTerminale());
        playDomino.addActionListener(event -> controleur.PlayDomino());
        playCarcassonne.addActionListener(event -> controleur.PlayCarcassonne());
    }
}
