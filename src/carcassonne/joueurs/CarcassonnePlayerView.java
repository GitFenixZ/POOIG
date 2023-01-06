package carcassonne.joueurs;

import carcassonne.PlayCarcassonneControleur;
import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.CarcassonnePieceView;
import carcassonne.piece.Terrain;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerView;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            // Empeche la fermeture de la fenêtre de placement de partisan
            frame.setDefaultCloseOperation(0); 
            frame.setSize(200, 260);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            JPanel panel = new JPanel(new BorderLayout());
            JPanel panelTexte = new JPanel(new GridLayout(2, 1));
            JLabel texte1 = new JLabel("Placez un partisan :");
            JLabel texte2 = new JLabel("Cliquez ou vous voulez");
            panelTexte.add(texte1);
            panelTexte.add(texte2);

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
            panel.add(panelTexte, BorderLayout.NORTH);
            panel.add(pieceView, BorderLayout.CENTER);
            panel.add(passer, BorderLayout.SOUTH);
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}
