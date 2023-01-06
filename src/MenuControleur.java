import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contrôleur qui s'occupe du Menu.
 */
public class MenuControleur {
    private MenuModel model;
    private MenuView view;

    /**
     * Constructeur
     */
    public MenuControleur() {
        this.model = new MenuModel();
        this.view = new MenuView(this, model);
    }

    public MenuView getView() {
        return view;
    }

    // Domino Terminal

    /**
     * initialise une partie de domino dans le terminal
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void prepareDominoTerminal(int nombreDeJoueur) {
        Scanner sc = new Scanner(System.in);
        model.initDominoTerminal(sc, nombreDeJoueur);
    }

    /**
     * Prepare une partie de domino
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void prepareDomino(int nombreDeJoueur) {
        view.setTitle("Domino Game !");
        try {
            Image image = ImageIO.read(new File("src/images/domino.png"));
            view.setIconImage(image);
        } catch (IOException ex) {
        }
        model.initDomino(nombreDeJoueur);
        view.initPanelParametresDomino(nombreDeJoueur);
        view.pack();
        view.setLocationRelativeTo(null);
        view.revalidate();
    }

    /**
     * Lance une partie de domino
     */
    public void playDomino() {
        view.play(model.getGame().getView());
        model.playDomino();
    }

    /**
     * Prepare une partie de carcassonne
     * 
     * @param nombreDeJoueur nombre de joueur qui jouerons la parties
     */
    public void prepareCarcassonne(int nombreDeJoueur) {
        view.setTitle("Carcassonne Game !");
        try {
            Image image = ImageIO.read(new File("src/images/carcassonne.png"));
            view.setIconImage(image);
        } catch (IOException ex) {
        }
        model.initCarcassonne();
        view.initPanelParametresCarcassonne(nombreDeJoueur);
        view.pack();
        view.setLocationRelativeTo(null);
        view.revalidate();
    }

    /**
     * Lance une partie de carcassonne
     */
    public void playCarcassonne() {
        view.play(model.getGame().getView());
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
