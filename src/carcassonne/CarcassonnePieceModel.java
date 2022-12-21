package carcassonne;

import communs.objets.piece.PieceModel;

public class CarcassonnePieceModel extends PieceModel<Terrain> {
    public CarcassonnePieceModel(int hauteur, int largeur) {
        super(hauteur, largeur, Terrain.NONE);
    }
}
