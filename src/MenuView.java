import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import communs.PlayGameView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Class permettant de voir une partis
 */
public class MenuView extends JFrame {
    private MenuControleur controller;
    private MenuModel model;

    private JPanel pane;

    private int nombreDeJoueurValide;
    private int nombreDeJoueur;

    MenuView(MenuControleur controller, MenuModel model) {
        super("Menu");
        this.model = model;
        this.controller = controller;
        nombreDeJoueurValide = 0;
        initMenu();
        initFrame();

    }

    private void initFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        revalidate();
    }

    /**
     * Initialise le menu
     */
    private void initMenu() {

        JPanel card1 = new JPanel();

        JLabel questionNombreDeJoueur = new JLabel("Combien de joueur voulez vous ?");
        JTextField entreeNombreDeJoueur = new JTextField(15);

        card1.add(questionNombreDeJoueur);
        card1.add(entreeNombreDeJoueur);

        JPanel card2 = new JPanel();

        JButton playDominoTerminal = new JButton("Domino Terminal");
        JButton playDomino = new JButton("Domino");
        JButton playCarcassonne = new JButton("Carcassonne");

        card2.add(playDominoTerminal);
        card2.add(playDomino);
        card2.add(playCarcassonne);

        JPanel cards = new JPanel();
        cards.setLayout(new GridLayout(2, 1));
        cards.add(card1);
        cards.add(card2);

        pane = new JPanel();
        pane.setLayout(new GridLayout(1, 1));
        pane.add(cards);
        // Ajout des ActionListeners adapté pour chaque bouton

        // Lance le jeu de domino version terminal si on clique sur le bouton
        playDominoTerminal.addActionListener(event -> {
            if (estStringEntier(entreeNombreDeJoueur.getText())) {
                int nbJoueurs = Integer.valueOf(entreeNombreDeJoueur.getText());
                setVisible(false);
                controller.prepareDominoTerminal(nbJoueurs);
            }
        });

        // Lance le jeu de domino version GUI si on clique sur le bouton
        playDomino.addActionListener(event -> {
            if (estStringEntier(entreeNombreDeJoueur.getText())) {
                int nbJoueurs = Integer.valueOf(entreeNombreDeJoueur.getText());
                controller.prepareDomino(nbJoueurs);
                revalidate();
            }
        });

        // Lance le jeu de Carcassonne version GUI si on clique sur le bouton
        playCarcassonne.addActionListener(event -> {
            if (estStringEntier(entreeNombreDeJoueur.getText())) {
                int nbJoueurs = Integer.valueOf(entreeNombreDeJoueur.getText());
                controller.prepareCarcassonne(nbJoueurs);
                revalidate();
            }
        });
    }

    /**
     * Verifie si un String contient bien un entier et uniquement un entier.
     * 
     * @param s string a verifier
     * @return si le string est un nombre
     */
    public boolean estStringEntier(String s) {
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }
        int nb = Integer.valueOf(s);
        return 1 < nb && nb <= 8;
    }

    /**
     * Initialise le layout du panneau de paramètres
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void initLayout(int nombreDeJoueur) {
        this.nombreDeJoueur = nombreDeJoueur;
        pane.removeAll();
        pane.setLayout(new GridLayout(1, nombreDeJoueur));
    }

    /**
     * Initialise le panneau de paramètres de Domino
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void initPanelParametresDomino(int nombreDeJoueur) {
        initLayout(nombreDeJoueur);
        for (int i = 0; i < nombreDeJoueur; i++) {
            pane.add(ajoutPanelPersoDomino(i));
        }
        revalidate();
    }

    /**
     * Initialise le panneau de paramètres de Carcassonne
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void initPanelParametresCarcassonne(int nombreDeJoueur) {
        initLayout(nombreDeJoueur);
        for (int i = 0; i < nombreDeJoueur; i++) {
            pane.add(ajoutPanelPersoCarcassonne(i));
        }
        revalidate();
    }

    /**
     * Lance la partie graphique avec la view du jeu adapté
     * 
     * @param gameView la view de la fenetre du jeu qu'on lance
     */
    public void play(PlayGameView gameView) {
        pane.removeAll();
        pane.add(gameView);
        // pack();
        setSize(new Dimension(995, 800));
        setLocationRelativeTo(null);
        revalidate();
    }

    /**
     * Ajoute un JPanel qui permet de choisir les options d'un personnage
     */
    public JPanel ajoutPanelPersoDomino(int i) {
        JPanel res = new JPanel();

        res.setLayout(new GridLayout(3, 1));
        JPanel a = new JPanel();
        a.setLayout(new GridLayout(1, 2));
        a.add(new JLabel("Pseudo : "));
        JTextField NomPerso = new JTextField("Joueur" + i);
        a.add(NomPerso);
        res.add(a);
        JPanel b = new JPanel();
        b.setLayout(new GridLayout(1, 2));
        JButton bot = new JButton("Bot");
        b.add(bot);
        JLabel infoBot = new JLabel("oui");
        b.add(infoBot);
        res.add(b);
        bot.addActionListener(event -> {
            if (infoBot.getText().equals("oui")) {
                infoBot.setText("non");
            } else {
                infoBot.setText("oui");
            }
        });
        JButton valider = new JButton("Valider");
        res.add(valider);
        valider.addActionListener(event -> {
            if (NomPerso.getText().length() != 0) {
                valider.setEnabled(false);
                bot.setEnabled(false);
                NomPerso.setEnabled(false);

                if (infoBot.getText().equals("oui")) {
                    controller.ajoutBotDomino(NomPerso.getText());
                } else {
                    controller.ajoutPersoDomino(NomPerso.getText());
                }
                // Si tous les joueurs ont validé, on lance le jeu
                nombreDeJoueurValide++;
                if (nombreDeJoueurValide == nombreDeJoueur) {
                    controller.playDomino();

                }
            }
        });
        return res;
    }

    /**
     * Ajoute un JPanel qui permet de choisir les options d'un personnage
     */
    public JPanel ajoutPanelPersoCarcassonne(int i) {
        JPanel res = new JPanel();

        res.setLayout(new GridLayout(4, 1));
        JPanel a = new JPanel();
        a.setLayout(new GridLayout(1, 2));
        a.add(new JLabel("Pseudo : "));
        JTextField NomPerso = new JTextField("Joueur" + i);
        a.add(NomPerso);
        res.add(a);
        JPanel b = new JPanel();
        b.setLayout(new GridLayout(1, 2));
        JButton bot = new JButton("Bot");
        b.add(bot);
        JLabel infoBot = new JLabel("oui");
        b.add(infoBot);
        res.add(b);
        bot.addActionListener(event -> {
            if (infoBot.getText().equals("oui")) {
                infoBot.setText("non");
            } else {
                infoBot.setText("oui");
            }
        });
        Color[] couleurs = { Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK, Color.MAGENTA, Color.CYAN,
                Color.ORANGE };
        JComboBox<Color> choixCouleur = new JComboBox<Color>(couleurs);
        res.add(choixCouleur);
        JButton valider = new JButton("Valider");
        res.add(valider);
        valider.addActionListener(event -> {
            if (NomPerso.getText().length() != 0) {
                valider.setEnabled(false);
                bot.setEnabled(false);
                choixCouleur.setEnabled(false);
                NomPerso.setEnabled(false);

                if (infoBot.getText().equals("oui")) {
                    controller.ajoutBotCarcassonne(NomPerso.getText(),
                            (Color) choixCouleur.getSelectedItem());
                } else {
                    controller.ajoutPersoCarcassonne(NomPerso.getText(),
                            (Color) choixCouleur.getSelectedItem());
                }
                // Si tous les joueurs ont validé, on lance le jeu
                nombreDeJoueurValide++;
                if (nombreDeJoueurValide == nombreDeJoueur) {
                    controller.playCarcassonne();
                }
            }
        });
        return res;
    }

}
