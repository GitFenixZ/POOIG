import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import carcassonne.PlayCarcassonneControleur;
import domino.PlayDominoControleur;

import java.awt.GridLayout;

/**
 * Class permettant de voir une partis
 */
public class MenuView extends JPanel {
    private MenuModel model;
    private MenuControleur controleur;

    private int nombreDeJoueurValide;
    private int nombreDeJoueur;

    public void setControleur(MenuControleur controleur) {
        this.controleur = controleur;
    }

    MenuView(MenuModel model) {
        super();
        this.model = model;

        nombreDeJoueurValide = 0;

        JButton playDominoTerminal = new JButton("Domino Terminal");
        JButton playDomino = new JButton("Domino");
        JButton playCarcassonne = new JButton("Carcassonne");

        JTextArea questionNombreDeJoueur = new JTextArea("Combien de joueur voulez vous ?");
        JTextField nombreDeJoueur = new JTextField(15);

        setLayout(new GridLayout(2, 3));

        add(questionNombreDeJoueur);
        add(nombreDeJoueur);
        add(new JLabel());
        add(playDominoTerminal);
        add(playDomino);
        add(playCarcassonne);

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

    public void init(int nombreDeJoueur) {
        this.nombreDeJoueur = nombreDeJoueur;
        removeAll();
        setLayout(new GridLayout(1, nombreDeJoueur));
    }

    public void initDomino(int nombreDeJoueur) {
        init(nombreDeJoueur);
        for (int i = 0; i < nombreDeJoueur; i++) {
            add(ajoutPersoDomino(i));
        }
        revalidate();
    }

    public void playDominoTerminale(int nombreDeJoueur) {
        init(nombreDeJoueur);
        for (int i = 0; i < nombreDeJoueur; i++) {
            add(ajoutPersoDomino(i));
        }
        revalidate();
    }

    public void initCarcassonne(int nombreDeJoueur) {
        init(nombreDeJoueur);
        for (int i = 0; i < nombreDeJoueur; i++) {
            add(ajoutPersoCarcassonne(i));
        }
        revalidate();
    }

    public void play() {
        removeAll();
        add(model.getGame().getView());
        revalidate();
    }

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

    public JPanel ajoutPersoCarcassonne(int i) {
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

                if (infoBot.getText().equals("oui")) {
                    controleur.ajoutBotCarcassonne(NomPerso.getText());
                } else {
                    controleur.ajoutPersoCarcassonne(NomPerso.getText());
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
