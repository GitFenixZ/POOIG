import java.util.Scanner;

import javax.swing.JFrame;

import carcassonne.PlayCarcassonneControleur;
import communs.PlayGameControleur;
import domino.PlayDominoControleur;

/**
 * Class permetant l'éxécution des parties.
 */

public class MenuModel {
    private PlayGameControleur game;

    MenuModel() {
    }

    public void PlayDomino() {
        /**
         * Message pour Tony tu dois supprimer ce sc dés que possible !
         * le sc permet de demander des pseudos pour les joueurs dans le terminale.
         */
        Scanner sc = new Scanner(System.in);
        game = new PlayDominoControleur(10, 3, sc);
        ((PlayDominoControleur) game).play();
    }

    public void PlayDominoTerminale() {
        Scanner sc = new Scanner(System.in);
        game = new PlayDominoControleur(10, 3, sc);
        ((PlayDominoControleur) game).playTerminal(sc);
    }

    public void PlayCarcassonne() {
        /**
         * Message pour Tony tu dois supprimer ce sc dés que possible !
         * le sc permet de demander des pseudos pour les joueurs dans le terminale.
         */
        Scanner sc = new Scanner(System.in);
        game = new PlayCarcassonneControleur(3, sc);
        ((PlayCarcassonneControleur) game).play();
    }

    public PlayGameControleur getGame() {
        return game;
    }
}
