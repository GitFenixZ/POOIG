import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * Controleur qui s'occupe du Menu.
 */
public class MenuControleur {
    private MenuModel model;
    private MenuView view;
    private JFrame frame;

    /**
     * Constructeur
     */
    public MenuControleur(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;
    }

    public MenuView getView() {
        return view;
    }

    /**
     * initialise une partie de carcassonne
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void initCarcassonne(int nombreDeJoueur) {
        frame.setTitle("Carcassonne Game !");
        try {
            Image image = ImageIO.read(new File("src/images/carcassonne.png"));
            frame.setIconImage(image);
        } catch (IOException ex) {
        }
        model.initCarcassonne(nombreDeJoueur);
        view.initCarcassonne(nombreDeJoueur);
        frame.revalidate();
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

}
