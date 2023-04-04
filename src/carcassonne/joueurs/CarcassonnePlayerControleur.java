package carcassonne.joueurs;

import java.awt.Color;

import carcassonne.Partisan;
import carcassonne.PlayCarcassonneControleur;
import carcassonne.piece.CarcassonnePieceControleur;
import carcassonne.piece.Terrain;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;

/**
 * Controleur d'une joueur de carcassonne met en liens la vue et le model.
 */

public class CarcassonnePlayerControleur extends PlayerControleur<PieceControleur<Terrain>> {

    CarcassonnePlayerControleur() {
    }

    /**
     * Constructeur d'un player
     * 
     * @param name    pseudo du joueur.
     * @param couleur couleur des partisans.
     */
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
     * Prends un partisan de la collection et le retire.
     */
    public Partisan peekpartisan() {
        return ((CarcassonnePlayerModel) model).peekPartisan();
    }

    /**
     * Verifie si la liste des partisan contient toujours des pièces.
     */
    public boolean partisansIsEmpty() {
        return ((CarcassonnePlayerModel) model).partisansIsEmpty();
    }

    /**
     * Méthode permettant d'afficher la fenêtre donnant accès au joueur la
     * posibilité de placer un partisan sur la piece du joueur qui est en train de
     * jouer.
     * 
     * @param game parti qui est en cours.
     */
    public void placerpartisan(PlayCarcassonneControleur game) {
        ((CarcassonnePlayerView) view).placerPartisan(game);
    }

    /**
     * Place un partisan au point p du joueur actuel
     * 
     * @param p point sur lequel on place le partisan
     */
    public void placerpartisan(Point p) {
        ((CarcassonnePieceControleur) getMain()).placerpartisan(p, this);
    }
}
