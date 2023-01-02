package carcassonne.joueurs;

import carcassonne.PlayCarcassonneControleur;
import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.CarcassonnePieceView;
import carcassonne.piece.Terrain;
import communs.objets.PlayerView;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
     * Affiches les informations d'un joueur
     * 
     * @param model
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

    public void placerPartisant(CarcassonnePlayerControleur player, PlayCarcassonneControleur game) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(4, 3));
        JLabel texte1 = new JLabel("Voulez vous placer un partisant ? (Cliqué ou vous voulez)");
        JLabel texte2 = new JLabel("(Cliqué ou vous voulez)");

        // créer une copy de la view de la piece du joueur actuel que nomme pieceView
        CarcassonnePieceView pieceView = new CarcassonnePieceView(
                ((CarcassonnePieceControleur) player.getMain()).getId());

        pieceView.setImage(((CarcassonnePieceView) player.getMain().getView()).getImage());

        pieceView.setModel(((CarcassonnePieceControleur) player.getMain()).getModel());

        pieceView.revalidate();

        pieceView.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                ((CarcassonnePlayerControleur) getControleur())
                        .placerPartisant(
                                new Point(5 * e.getX() / pieceView.getWidth(), 5 * e.getY() / pieceView.getHeight()));
                game.postPartisan();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
        JButton passer = new JButton("passer");
        passer.addActionListener(event -> {
            frame.dispose();
            game.postPartisan();
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
