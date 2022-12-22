import java.util.Scanner;

import carcassonne.PlayCarcassonne;
import domino.PlayDomino;

/**
 * Class permetant l'éxécution des parties.
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // lance une partie de Domino
        // PlayDomino p = new PlayDomino(15, 3, 1, 1, sc);
        PlayCarcassonne p = new PlayCarcassonne(15, 3, 1, 1, sc);
        p.play(sc);
    }
}
