package carcassonne;

import communs.objets.Player;
import communs.objets.piece.PieceControleur;

public class CarcassonnePlayer extends Player<PieceControleur<Terrain>> {
    CarcassonnePlayer(String name) {
        super(name);
    }
}
