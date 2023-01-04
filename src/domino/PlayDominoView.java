package domino;

import java.util.Scanner;

import communs.PlayGameControleur;
import communs.PlayGameModel;
import communs.PlayGameView;
import communs.interfaces.Demander;

public class PlayDominoView extends PlayGameView<Integer> implements Demander {
    PlayDominoView(PlayGameModel<Integer> model, PlayGameControleur<Integer> controleur) {
        super(model, controleur);
    }

    public void initialisationJoueur(Scanner sc, int nombreDeJoueur) {
        // intialise les joueurs
        for (int i = 1; i <= nombreDeJoueur; i++) {
            if (demandeBoolean(sc, "Est ce une joueur ? (oui / non)")) {
                System.out.println("Comment s'appel le joueur " + i + " ?");
                getControleur().ajoutPerso(sc.nextLine());
            } else {
                ((PlayDominoControleur) getControleur()).ajoutBot("Joueur" + i);
            }
        }
        ((PlayDominoControleur) getControleur()).playTerminal(sc);
    }
}
