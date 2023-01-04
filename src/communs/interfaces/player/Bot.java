package communs.interfaces.player;

import communs.objets.plateau.PlateauControleur;

public interface Bot<V> {
    public boolean jouer(PlateauControleur<V> plateauControleur);
}
