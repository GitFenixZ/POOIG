package carcassonne.joueurs;

import java.awt.Color;

import carcassonne.piece.Terrain;
import communs.interfaces.player.Bot;
import communs.objets.plateau.PlateauControleur;

/**
 * Class contrôlant un Robot capable de jouer au carcassonne de manière très
 * simple.
 */
public class CarcassonneBotControleur extends CarcassonnePlayerControleur implements Bot<Terrain> {

    /**
     * Constructeur du bot
     * 
     * @param name    pseudo du bot
     * @param couleur couleur de ses partisans
     */
    public CarcassonneBotControleur(String name, Color couleur) {
        super();
        model = new CarcassonneBotModel(name, couleur);
        view = new CarcassonnePlayerView((CarcassonnePlayerModel) model);
        view.setControleur(this);
    }

    /**
     * Méthode a utiliser pour jouer le tour d'un robot.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    @Override
    public boolean jouer(PlateauControleur<Terrain> plateauControleur) {
        return ((CarcassonneBotModel) model).jouer(plateauControleur, this);
    }
}
