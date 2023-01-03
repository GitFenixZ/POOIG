package communs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import carcassonne.PlayCarcassonneControleur;
import carcassonne.joueurs.CarcassonnePlayerControleur;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;

/**
 * Vue d'une partie
 * V est le type des valeur présente sur les pièces du jeu.
 */
public class PlayGameView<V> extends JPanel {
    private PlayGameModel<V> model;
    private PlayGameControleur<V> controleur;

    private JPanel sideBar;
    private JPanel pseudo;
    private JPanel score;
    private JPanel piece;
    private CommandButtons commandButtons;

    public void setModel(PlayGameModel<V> model) {
        this.model = model;
    }

    public void setControleur(PlayGameControleur<V> controleur) {
        this.controleur = controleur;
    }

    public PlayGameView(PlayGameModel<V> model, PlayGameControleur<V> controleur) {
        super(new BorderLayout());
        this.model = model;
        this.controleur = controleur;

        // La barre d'infos
        sideBar = new JPanel(new GridLayout(4, 1));

        // Case du Joueur
        pseudo = ajouterCase("Joueur : \n", Color.GRAY);

        // Case du Score
        score = ajouterCase("Score :\n", Color.GRAY);

        // Case preview
        piece = new JPanel();
        piece.setLayout(new GridLayout(1, 1));
        piece.setBackground(Color.GRAY);
        sideBar.add(piece);

        // Case des commandes directionnelles + rotations
        commandButtons = new CommandButtons();
        sideBar.add(commandButtons.getCommandsPane());

        // Ajout de la barre d'infos
        add(sideBar, BorderLayout.WEST);

        // Plateau de jeu

        add(model.getImagePlateau(), BorderLayout.CENTER);
    }

    /**
     * actualise l'affichage de la game.
     */
    public void actualiser() {
        // Case du Joueur
        pseudo.removeAll();
        pseudo.add(new JLabel("Joueur :\n" + controleur.getActuelPlayer().getName()));

        // Case du Score
        score.removeAll();
        score.add(new JLabel("Score :\n" + controleur.getActuelPlayer().getscore()));

        // Case preview
        piece.removeAll();
        piece.setLayout(new GridLayout(1, 1));
        if (controleur.getActuelPlayer().getMain() != null) {
            piece.add(((PieceControleur) controleur.getActuelPlayer().getMain()).getView());
        } else {
            piece.removeAll();
        }
        piece.revalidate();
        sideBar.revalidate();
    }

    /**
     * ajoute une case a la sideBar à gauche de l'affichage
     */
    private JPanel ajouterCase(String content, Color c) {
        JPanel panel = new JPanel();
        panel.setBackground(c);
        JLabel label = new JLabel(content, SwingConstants.CENTER);
        panel.add(label);
        sideBar.add(panel);
        return panel;
    }

    /**
     * A la fin de la partie affiche une nouvelle fenetre avec toutes les
     * explications
     */
    public void finDePartie() {
        JFrame finDePartie = new JFrame();
        JPanel panel = new JPanel(new GridLayout(model.getNombreDeJoueur() + 2, 1));
        desactiverBoutonPlacer();
        piece.removeAll();
        piece.revalidate();
        revalidate();
        panel.add(new JLabel("Bravo a tous :"));
        for (PlayerControleur<PieceControleur<V>> p : model.getJoueurs()) {
            panel.add(new JLabel("*" + p.getName() + " : " + p.getscore()));
        }
        JButton ok = new JButton("Ok");
        ok.addActionListener(event -> finDePartie.dispose());
        panel.add(ok);
        finDePartie.setSize(500, 200);
        finDePartie.setLocationRelativeTo(null);
        finDePartie.setContentPane(panel);
        finDePartie.setVisible(true);
        revalidate();
    }

    private class CommandButtons {

        private JPanel commandsPane = new JPanel();
        private GridBagLayout gridbag = new GridBagLayout();
        private GridBagConstraints c = new GridBagConstraints();

        public CommandButtons() {
            commandsPane.setLayout(gridbag);
            init();
        }

        public JPanel getCommandsPane() {
            return commandsPane;
        }

        JButton makeButton(String name, int x, int y) {
            c.gridx = x;
            c.gridy = y;
            JButton button = new JButton(name);
            commandsPane.add(button, c);
            return button;
        }

        public void init() {

            c.weightx = 1.0;
            c.weighty = 1.0;
            c.fill = GridBagConstraints.BOTH;
            int x, y; // for clarity
            makeButton("RotateLeft", x = 0, y = 0).addActionListener(event -> controleur.tournerGauche());

            makeButton("RotateRight", x = 2, y = 0).addActionListener(event -> controleur.tournerDroite());

            makeButton("UpLeft", x = 0, y = 1).addActionListener(event -> {
                controleur.allerAGauche();
                controleur.allerEnHaut();
            });

            makeButton("Up", x = 1, y = 1).addActionListener(event -> controleur.allerEnHaut());

            makeButton("UpRight", x = 2, y = 1).addActionListener(event -> {
                controleur.allerADroite();
                controleur.allerEnHaut();
            });

            makeButton("Left", x = 0, y = 2).addActionListener(event -> controleur.allerAGauche());

            makeButton("Place", x = 1, y = 2).addActionListener(event -> {
                if (model.possibleDePlacer()) {
                    controleur.placerPiece();
                    if (controleur.getActuelPlayer() instanceof CarcassonnePlayerControleur) {
                        ((CarcassonnePlayerControleur) controleur.getActuelPlayer())
                                .placerPartisant((PlayCarcassonneControleur) controleur);
                        desactiverBoutonPlacer();
                    } else {
                        controleur.postPartisan();
                    }
                }
            });

            makeButton("Right", x = 2, y = 2).addActionListener(event -> controleur.allerADroite());

            makeButton("DownLeft", x = 0, y = 3).addActionListener(event -> {
                controleur.allerEnBas();
                controleur.allerAGauche();
            });

            makeButton("Down", x = 1, y = 3).addActionListener(event -> controleur.allerEnBas());

            makeButton("DownRight", x = 2, y = 3).addActionListener(event -> {
                controleur.allerADroite();
                controleur.allerEnBas();
            });
        }
    }

    /**
     * desactive le bouton pour placer des pieces.
     */
    public void desactiverBoutonPlacer() {
        commandButtons.getCommandsPane().getComponent(6).setEnabled(false);
    }

    /**
     * active le bouton pour placer des pieces.
     */
    public void activerBoutonPlacer() {
        commandButtons.getCommandsPane().getComponent(6).setEnabled(true);
    }
}
