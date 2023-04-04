package domino;

import java.util.Scanner;

import communs.PlayGameControleur;
import communs.PlayGameModel;
import communs.PlayGameView;
import communs.interfaces.Demander;

public class PlayDominoTerminalView extends PlayGameView<Integer> implements Demander {
    PlayDominoTerminalView(PlayGameModel<Integer> model, PlayGameControleur<Integer> controleur) {
        super(model, controleur);
    }

    Scanner sc;

    public void initialisationJoueur(int nombreDeJoueur, Scanner sc) {
        this.sc = sc;
        // intialise les joueurs
        for (int i = 1; i <= nombreDeJoueur; i++) {
            if (demandeBoolean(sc, "Est ce une joueur ? (oui / non)")) {
                System.out.println("Comment s'appel le joueur " + i + " ?");
                getControleur().ajoutPerso(sc.nextLine());
            } else {
                ((PlayDominoControleur) getControleur()).ajoutBot("Joueur" + i);
            }
        }
        ((PlayDominoControleur) getControleur()).play();
        getControleur().rejouer();
    }

    @Override
    public void actualiser() {
        ((PlayDominoControleur) getControleur()).pensezVousPouvoirJouer(sc, getModel().getActuelPlayer());
        getControleur().nextPlayer();
    }
}
