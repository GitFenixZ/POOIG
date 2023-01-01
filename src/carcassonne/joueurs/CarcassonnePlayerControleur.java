package carcassonne.joueurs;

import java.awt.Color;

import carcassonne.Partissant;
import carcassonne.piece.Terrain;
import communs.objets.PlayerControleur;
import communs.objets.piece.PieceControleur;

/**
 * Controleur d'une joueur de carcassonne
 */

public class CarcassonnePlayerControleur extends PlayerControleur<PieceControleur<Terrain>> {

    public CarcassonnePlayerControleur(String name, Color couleur) {
        model = new CarcassonnePlayerModel(name, couleur);
        view = new CarcasonnePlayerView((CarcassonnePlayerModel) model);
    }

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return ((CarcasonnePlayerView) view).toString();
    }

    /**
     * Prends un partisant de la collection.
     */
    public Partissant peekPartisant() {
        return ((CarcassonnePlayerModel) model).peekPartisant();
    }

    /**
     * Verifie si la liste des partisant contient toujours des pièces.
     */
    public boolean partisantsIsEmpty() {
        return ((CarcassonnePlayerModel) model).partisantsIsEmpty();
    }

    @Override
    public void afficheCarte() {
        ((CarcasonnePlayerView) view).afficheCarte();
    }
}
