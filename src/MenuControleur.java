import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class MenuControleur extends JFrame {
    private MenuModel model;
    private MenuView view;

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

    public void playDomino() {
        view.play();
        revalidate();
        model.playDomino();
    }

    public void playDominoTerminale(int nombreDeJoueur) {
        setVisible(false);
        Scanner sc = new Scanner(System.in);
        model.initDominoTerminale(nombreDeJoueur, sc);
        model.playDominoTerminale(sc);
    }

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

    public void playCarcassonne() {
        view.play();
        model.playCarcassonne();
    }

    public void ajoutPersoDomino(String nom) {
        model.ajoutPersoDomino(nom);
    }

    public void ajoutPersoCarcassonne(String nom, Color couleur) {
        model.ajoutPersoCarcassonne(nom, couleur);
    }

    public void ajoutBotDomino(String nom) {
        model.ajoutBotDomino(nom);
    }

    public void ajoutBotCarcassonne(String nom, Color couleur) {
        model.ajoutBotCarcassonne(nom, couleur);
    }

    public static void main(String[] args) {
        new MenuControleur();
    }

}
