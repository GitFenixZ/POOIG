import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * 
 */
public class MenuControleur extends JFrame {
    private MenuModel model;
    private MenuView view;

    /**
     * constructeur initialise la fenetre et lance le jeu.
     */
    public MenuControleur() {
        super("Menu");

        model = new MenuModel();
        view = new MenuView(model);
        view.setControleur(this);

        setSize(800, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(view);
        revalidate();
    }

    public MenuView getView() {
        return view;
    }

    /**
     * initialise une partie de domino
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void initDomino(int nombreDeJoueur) {
        setTitle("Domino Game !");
        try {
            Image image = ImageIO.read(new File("src/images/domino.png"));
            setIconImage(image);
        } catch (IOException ex) {
        }
        model.initDomino(nombreDeJoueur);
        view.initDomino(nombreDeJoueur);
        revalidate();
    }

    /**
     * Lance une partie de domino
     */
    public void playDomino() {
        view.play();
        revalidate();
        model.playDomino();
    }

    /**
     * initialise une partie de domino dans le terminal
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void playDominoTerminale(int nombreDeJoueur) {
        setVisible(false);
        Scanner sc = new Scanner(System.in);
        model.initDominoTerminale(nombreDeJoueur, sc);
        model.playDominoTerminale(sc);
    }

    /**
     * initialise une partie de carcassonne
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void initCarcassonne(int nombreDeJoueur) {
        setTitle("Carcassonne Game !");
        try {
            Image image = ImageIO.read(new File("src/images/carcassonne.png"));
            setIconImage(image);
        } catch (IOException ex) {
        }
        model.initCarcassonne(nombreDeJoueur);
        view.initCarcassonne(nombreDeJoueur);
        revalidate();
    }

    /**
     * Lance une partie de carcassonne
     */
    public void playCarcassonne() {
        view.play();
        model.playCarcassonne();
    }

    /**
     * Ajoute un joueur a la partie de domino.
     * 
     * @param nom pseudo du joueur.
     */
    public void ajoutPersoDomino(String nom) {
        model.ajoutPersoDomino(nom);
    }

    /**
     * ajoute une personnage aux joueurs de la partie de carcassonne
     * 
     * @param nom     pseudo du personnage a ajouter
     * @param couleur couleur que l'équipe du personnage
     */
    public void ajoutPersoCarcassonne(String nom, Color couleur) {
        model.ajoutPersoCarcassonne(nom, couleur);
    }

    /**
     * Ajout un robots a liste des joueurs de la partie actuel de domino.
     * 
     * @param nom pseudo du robot
     */
    public void ajoutBotDomino(String nom) {
        model.ajoutBotDomino(nom);
    }

    /**
     * ajoute une robot aux joueurs de la partie de carcassonne.
     * 
     * @param nom     pseudo du robot a ajouter
     * @param couleur couleur que l'équipe du robot
     */
    public void ajoutBotCarcassonne(String nom, Color couleur) {
        model.ajoutBotCarcassonne(nom, couleur);
    }

    public static void main(String[] args) {
        new MenuControleur();
    }

}
