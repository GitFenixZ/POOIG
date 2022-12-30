package carcassonne.joueurs;

import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.Terrain;
import communs.objets.PlayerView;
import communs.objets.piece.PieceControleur;

/**
 * View d'un joueur de carcassonne
 */
public class CarcasonnePlayerView extends PlayerView<PieceControleur<Terrain>> {

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return "Nom : " + getModel().getName() + " Score : " + getModel().getscore() + " Et a "
                + ((CarcassonnePlayerModel) getModel()).getNombreDePartisant()
                + " partisants.\nPièce en main :\n" + ((CarcassonnePieceControleur) getModel().getMain());
    }
}
