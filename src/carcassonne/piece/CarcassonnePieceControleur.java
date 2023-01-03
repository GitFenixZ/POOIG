package carcassonne.piece;

import carcassonne.joueurs.CarcassonnePlayerControleur;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import java.util.ArrayList;
import carcassonne.Partisan;

/**
 * Modelise le controleur des piece de plateau de carcassonne.
 */
public class CarcassonnePieceControleur extends PieceControleur<Terrain> {
    /**
     * Constructeur
     * Initialise une piece du jeu de carcassonne
     */
    public CarcassonnePieceControleur(Terrain[][] map, int id) {
        super();
        CarcassonnePieceModel model = new CarcassonnePieceModel(map);
        CarcassonnePieceView view = new CarcassonnePieceView(id);
        view.setModel(model);
        setModel(model);
        setView(view);
    }

    /**
     * Place un partisant du joueur au coordoonee du point
     * 
     * @param point  coordonnees auquel sera placer le partisant sur la piece
     * @param player joueur qui va placer un partisant
     */
    public void placerPartisant(Point point, CarcassonnePlayerControleur player) {
        ((CarcassonnePieceModel) getModel()).placerPartisant(point, player);
    }

    @Override
    public void tournerDroite() {
        getModel().tournerDroite();
        ((CarcassonnePieceView) getView()).tournerDroite();
    }

    @Override
    public void tournerGauche() {
        getModel().tournerGauche();
        ((CarcassonnePieceView) getView()).tournerGauche();
    }

    public int getId() {
        return ((CarcassonnePieceView) getView()).getId();
    }

    public ArrayList<ArrayList<Partisan>> getPartisan() {
        return ((CarcassonnePieceModel) getModel()).getPartisan();
    }
}
