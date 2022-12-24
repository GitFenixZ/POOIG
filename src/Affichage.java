import java.util.Scanner;

import javax.swing.JFrame;

import domino.PlayDomino;

/**
 * Class permetant l'éxécution des parties.
 */

public class Affichage extends JFrame {
    public static JFrame main;
    public static PlayDomino game;

    Affichage() {
        main = new JFrame("Menu");
        main.setSize(600, 600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Scanner sc = new Scanner(System.in);
        game = new PlayDomino(20, 3, 1, 1, sc);

        main.setLocationRelativeTo(null);
        main.add(game.getPlateau().getView().getImagePlateau());
        main.setVisible(true);
        game.play();

    }

    public static void main(String[] args) {
        new Affichage();
        // Scanner sc = new Scanner(System.in);
        // lance une partie de Domino
        // PlayDomino p = new PlayDomino(15, 3, 1, 1, sc);
        // PlayCarcassonne p = new PlayCarcassonne(15, 3, 1, 1, sc);
        // p.play(sc);
    }
}
