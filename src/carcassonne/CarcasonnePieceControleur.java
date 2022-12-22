package carcassonne;

import communs.objets.piece.PieceControleur;
import communs.objets.piece.PieceView;

public class CarcasonnePieceControleur extends PieceControleur<Terrain> {
    /**
     * Constructeur
     * Initialise une piece du jeu de carcassonne
     */
    public CarcasonnePieceControleur(Terrain[][] map) {
        CarcassonnePieceModel model = new CarcassonnePieceModel(map);
        PieceView<Terrain> view = new PieceView<Terrain>();
        view.setModel(model);
        setModel(model);
        setView(view);
    }

    @Override
    public String getligne(int indice) {
        return ((CarcassonnePieceModel) model).getligne(indice);
    }
}
