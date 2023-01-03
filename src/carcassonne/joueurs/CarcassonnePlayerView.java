package carcassonne.joueurs;

import carcassonne.PlayCarcassonneControleur;
import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.CarcassonnePieceView;
import carcassonne.piece.Terrain;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerView;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View d'un joueur de carcassonne
 */
public class CarcassonnePlayerView extends PlayerView<PieceControleur<Terrain>> {

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
     * Constructeur de la playerView
     * 
     * @param model model que devra suivre la view.
     */
    public CarcassonnePlayerView(CarcassonnePlayerModel model) {
        super();
        setModel(model);
        setLayout(new GridLayout(2, 1));
        JPanel a = new JPanel();
        a.add(new JLabel(getModel().getName()));
        add(a);
        revalidate();
    }

    /**
     * Methode permettant de placer un partisant sur une tuile.
     * 
     * @param game partie en tain d'être joué
     */
    public void placerPartisant(PlayCarcassonneControleur game) {
        // ouvre une nouvelle fenetre.
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(4, 3));
        JLabel texte1 = new JLabel("Voulez vous placer un partisant ? (Cliqué ou vous voulez)");
        JLabel texte2 = new JLabel("(Cliqué ou vous voulez)");

        // créer une copy de la view de la piece du joueur actuel : pieceView
        // Evite les problèmes d'affichage d'un Jpanel blanc.
        CarcassonnePieceView pieceView = new CarcassonnePieceView(
                ((CarcassonnePieceControleur) getModel().getMain()).getId());

        pieceView.setImage(((CarcassonnePieceView) getModel().getMain().getView()).getImage());

        pieceView.setModel(((CarcassonnePieceControleur) getModel().getMain()).getModel());

        pieceView.revalidate();

        /**
         * Ajout de la posibilité de cliqué à l'endroit de piece que l'on souhaite pour
         * pouvoir placer le partisant.
         */
        pieceView.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                ((CarcassonnePlayerControleur) getControleur())
                        .placerPartisant(
                                new Point(
                                        ((CarcassonnePieceControleur) getModel().getMain()).getPartisan().size()
                                                * e.getX()
                                                / pieceView.getWidth(),
                                        ((CarcassonnePieceControleur) getModel().getMain()).getPartisan().size()
                                                * e.getY()
                                                / pieceView.getHeight()));
                game.postPartisan();// permet au joueur suivant de jouer.
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        JButton passer = new JButton("passer");
        passer.addActionListener(event -> {
            frame.dispose();
            game.postPartisan();// permet au joueur suivant de jouer.
        });
        panel.add(new JLabel());
        panel.add(texte1);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(texte2);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(pieceView);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(passer);
        panel.add(new JLabel());
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
