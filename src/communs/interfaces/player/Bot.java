package communs.interfaces.player;

import communs.objets.plateau.PlateauControleur;

/**
 * Interface permettant de simuler un robot.
 */
public interface Bot<V> {
    public boolean jouer(PlateauControleur<V> plateauControleur);
}
