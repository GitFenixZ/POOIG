package carcassonne.piece;

import carcassonne.joueurs.CarcassonnePlayerControleur;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import java.util.ArrayList;
import carcassonne.Partisan;

/**
 * Controleur des piece de plateau de carcassonne.
 */
public class CarcassonnePieceControleur extends PieceControleur<Terrain> {
    /**
     * Constructeur
     * Initialise une pièce du jeu de carcassonne
     * 
     * @param map information de la pièce dans le model.
     * @param id  numéro de l'image de la pièce.
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
     * Place un partisan du joueur aux coordonnée du point
     * 
     * @param point  coordonnées auquel sera placer le partisan sur la pièce
     * @param player joueur qui va placer un partisan
     */
    public void placerpartisan(Point point, CarcassonnePlayerControleur player) {
        ((CarcassonnePieceModel) getModel()).placerPartisan(point, player);
    }

    /**
     * Tourne la pièce du joueur dans le sens horaire
     */
    @Override
    public void tournerDroite() {
        getModel().tournerDroite();
        ((CarcassonnePieceView) getView()).tournerDroite();
    }

    /**
     * Tourne la pièce du joueur dans le sens anti-horaire
     */
    @Override
    public void tournerGauche() {
        getModel().tournerGauche();
        ((CarcassonnePieceView) getView()).tournerGauche();
    }

    /**
     * @return le numéro d'identification de l'image de la pièce.
     */
    public int getId() {
        return ((CarcassonnePieceView) getView()).getId();
    }

    /**
     * @return le tableau de partisan qui est sur la pièce.
     */
    public ArrayList<ArrayList<Partisan>> getPartisan() {
        return ((CarcassonnePieceModel) getModel()).getPartisan();
    }
}
