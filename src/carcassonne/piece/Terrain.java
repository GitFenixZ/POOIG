package carcassonne.piece;

/**
 * Enumeration representant les types de lieux que l'on peut retrouver sur une
 * carte de jeu de carcassonne.
 */
public enum Terrain {
    ABBAYE(0),
    QUARTIER(1),
    QUARTIERBOUCLIER(1),
    CARREFOUR(2),
    CHEMIN(3),
    PRE(4),
    NONE(5);

    /**
     * si deux terrains ont la même valeurs, alors ils peuvent etre mit cote a cote.
     */
    private int valeur;

    Terrain(int i) {
        valeur = i;
    }

    public int getValeur() {
        return valeur;
    }
}