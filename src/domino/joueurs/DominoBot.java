package domino.joueurs;

import communs.objets.Player;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;

/**
 * Class modélisant un Robot capable de jouer au domino de manière très
 * standart.
 */
public class DominoBot extends Player<PieceControleur<Integer>> {
    public DominoBot(String name) {
        super(name);
    }

    /**
     * Methode a utiliser pour jouer dans le terminal.
     * Affiche les informations importante.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    public boolean jouerTerminal(PlateauControleur<Integer> plateauControleur) {
        Point position = plateauControleur.peutPlacer(getMain());
        if (position != null) {
            plateauControleur.setPiece(this, position);
            System.out.println("Le bot " + getName() + " a joué.");
            return true;
        } else {
            System.out.println("Le bot " + getName() + " n'a pas pu jouer.");
            return false;
        }
    }

    /**
     * Methode a utiliser pour jouer sans le terminal.
     * n'affiche aucune informations.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    public boolean jouer(PlateauControleur<Integer> plateauControleur) {
        Point position = plateauControleur.peutPlacer(getMain());
        if (position != null) {
            plateauControleur.setPiece(this, position);
            return true;
        } else {
            return false;
        }
    }
}
