import java.util.Scanner;

import javax.swing.JFrame;

import carcassonne.PlayCarcassonne;
import communs.PlayGame;
import domino.PlayDomino;
import domino.joueurs.DominoBot;

/**
 * Class permetant l'éxécution des parties.
 */

public class Affichage extends JFrame {
    public static JFrame main;
    public static PlayGame game;

    Affichage() {
        main = new JFrame("Menu");
        main.setSize(600, 600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Scanner sc = new Scanner(System.in);
        game = new PlayDomino(10, 3, sc);
        // game = new PlayCarcassonne(3, sc);

        main.setLocationRelativeTo(null);
        main.add(game.getPlateau().getView().getImagePlateau());
        main.setVisible(true);
        if (game instanceof PlayDomino) {
            ((PlayDomino) game).play();
        }
        if (game instanceof PlayCarcassonne) {
            ((PlayCarcassonne) game).play();
        }

    }

    public static void main(String[] args) {
        new Affichage();
    }
}
