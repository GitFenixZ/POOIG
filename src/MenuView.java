import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * Class permettant de voir une partis
 */
public class MenuView extends JFrame {
    private MenuControleur controleur;

    private JPanel menu;

    private int nombreDeJoueurValide;
    private int nombreDeJoueur;

    MenuView() {
        super("Menu");
        nombreDeJoueurValide = 0;
        initMenu();
        initFrame();

    }

    // Setter
    public void setControleur(MenuControleur controleur) {
        this.controleur = controleur;
    }

    private void initFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(menu);
        pack();
        setVisible(true);
        revalidate();
    }

    /**
     * Initialise le menu
     */
    private void initMenu() {
        menu.setLayout(new CardLayout());

        JPanel card1 = new JPanel();

        JLabel questionNombreDeJoueur = new JLabel("Combien de joueur voulez vous ?");
        JTextField nombreDeJoueur = new JTextField(15);

        card1.add(questionNombreDeJoueur);
        card1.add(nombreDeJoueur);

        JPanel card2 = new JPanel();

        JButton playDominoTerminal = new JButton("Domino Terminal");
        JButton playDomino = new JButton("Domino");
        JButton playCarcassonne = new JButton("Carcassonne");

        card2.add(playDominoTerminal);
        card2.add(playDomino);
        card2.add(playCarcassonne);

        menu.add(card2);
        menu.add(card1);

        // TODO: Gérer l'ordre d'appel des fonctions et leur emplacement pour lancer
        // chaque partie
        playDominoTerminal.addActionListener(event -> {
            if (integer(nombreDeJoueur.getText())) {
                controleur.playDominoTerminale(Integer.valueOf(nombreDeJoueur.getText()));
            }
        });
        playDomino.addActionListener(event -> {
            if (integer(nombreDeJoueur.getText())) {
                controleur.initDomino(Integer.valueOf(nombreDeJoueur.getText()));
            }
        });
        playCarcassonne.addActionListener(event -> {
            if (integer(nombreDeJoueur.getText())) {
                controleur.initCarcassonne(Integer.valueOf(nombreDeJoueur.getText()));
            }
        });
    }

    /**
     * Verifie si un String contient bien un entier et uniquement un entier.
     * 
     * @param s string a verifier
     * @return si le string est un nombre
     */
    public boolean integer(String s) {
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }
        return Integer.valueOf(s) > 1;
    }

    /**
     * initialise une partie de jeu et permet de choisir les options de chaque
     * joueur.
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void init(int nombreDeJoueur) {
        this.nombreDeJoueur = nombreDeJoueur;
        removeAll();
        setLayout(new GridLayout(1, nombreDeJoueur));
    }

    /**
     * initialise une partie de domino
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void initDomino(int nombreDeJoueur) {
        init(nombreDeJoueur);
        for (int i = 0; i < nombreDeJoueur; i++) {
            add(ajoutPersoDomino(i));
        }
        revalidate();
    }

    /**
     * initialise une partie de domino dans le terminal
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void playDominoTerminale(int nombreDeJoueur) {
        init(nombreDeJoueur);
        for (int i = 0; i < nombreDeJoueur; i++) {
            add(ajoutPersoDomino(i));
        }
        revalidate();
    }

    /**
     * initialise une partie de carcassonne
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void initCarcassonne(int nombreDeJoueur) {
        init(nombreDeJoueur);
        for (int i = 0; i < nombreDeJoueur; i++) {
            add(ajoutPersoCarcassonne(i));
        }
        revalidate();
    }

    /**
     * lance la partie
     */
    public void play() {
        removeAll();
        add(model.getGame().getView());
        revalidate();
    }

    /**
     * Ajoute un Jpanel qui permet de choisir les options d'un personnages
     */
    public JPanel ajoutPersoDomino(int i) {
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
                    controleur.ajoutBotDomino(NomPerso.getText());
                } else {
                    controleur.ajoutPersoDomino(NomPerso.getText());
                }
                nombreDeJoueurValide++;
                if (nombreDeJoueurValide == nombreDeJoueur) {
                    ((MenuControleur) controleur).playDomino();
                }
            }
        });
        return res;
    }

    /**
     * Ajoute un Jpanel qui permet de choisir les options d'un personnages
     */
    public JPanel ajoutPersoCarcassonne(int i) {
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
                    controleur.ajoutBotCarcassonne(NomPerso.getText(),
                            (Color) choixCouleur.getSelectedItem());
                } else {
                    controleur.ajoutPersoCarcassonne(NomPerso.getText(),
                            (Color) choixCouleur.getSelectedItem());
                }
                nombreDeJoueurValide++;
                if (nombreDeJoueurValide == nombreDeJoueur) {
                    ((MenuControleur) controleur).playCarcassonne();
                }
            }
        });
        return res;
    }

}
