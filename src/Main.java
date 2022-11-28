import java.util.Scanner;

import Domino.PlayDomino;

/**
 * Class permetant l'éxécution des parties.
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayDomino p = new PlayDomino(7, 1, 1, 1);
        p.play(sc);
    }
}
