package carcassonne.joueurs;

import java.awt.Color;

import carcassonne.Partisan;
import carcassonne.PlayCarcassonneControleur;
import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.Terrain;
import communs.objets.PlayerControleur;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;

/**
 * Controleur d'une joueur de carcassonne
 */

public class CarcassonnePlayerControleur extends PlayerControleur<PieceControleur<Terrain>> {

    public CarcassonnePlayerControleur(String name, Color couleur) {
        model = new CarcassonnePlayerModel(name, couleur);
        view = new CarcassonnePlayerView((CarcassonnePlayerModel) model);
        view.setControleur(this);
    }

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return ((CarcassonnePlayerView) view).toString();
    }

    /**
     * Prends un partisant de la collection.
     */
    public Partisan peekPartisant() {
        return ((CarcassonnePlayerModel) model).peekPartisant();
    }

    /**
     * Verifie si la liste des partisant contient toujours des pièces.
     */
    public boolean partisantsIsEmpty() {
        return ((CarcassonnePlayerModel) model).partisantsIsEmpty();
    }

    public void placerPartisant(PlayCarcassonneControleur game) {
        ((CarcassonnePlayerView) view).placerPartisant(this, game);
    }

    public void placerPartisant(Point p) {
        ((CarcassonnePieceControleur) getMain()).placerPartisant(p, this);
    }
}
