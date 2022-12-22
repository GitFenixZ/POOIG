package communs.objets;

import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;

public class Bot<V> extends Player<PieceControleur<V>> {
    public Bot(String name) {
        super(name);
    }

    public boolean jouer(PlateauControleur<V> plateauControleur) {
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
}
