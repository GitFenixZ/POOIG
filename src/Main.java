import java.util.Scanner;

import Communs.PlateauModel;
import Communs.PlateauView;
import Domino.PlayDomino;

/**
 * Class permetant l'éxécution des parties.
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // lance une partie de Domino
        PlayDomino p = new PlayDomino(7, 1, 1, 1, sc);
        p.play(sc);
    }
}
