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
     * Contructeur d'un player
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
     * Prends un partisant de la collection et le retire.
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

    /**
     * Methode permettant d'afficher la fenetre donnant accès au joueur la
     * posibilité de placer un partisant sur la piece du joueur qui est en train de
     * jouer.
     * 
     * @param game parti qui est en cours.
     */
    public void placerPartisant(PlayCarcassonneControleur game) {
        ((CarcassonnePlayerView) view).placerPartisant(game);
    }

    /**
     * Place un partisant au point p du joueur actuel
     * 
     * @param p point sur lequel on place le partisant
     */
    public void placerPartisant(Point p) {
        ((CarcassonnePieceControleur) getMain()).placerPartisant(p, this);
    }
}
