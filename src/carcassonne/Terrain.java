package carcassonne;

public enum Terrain {
    ABBAYE(0),
    QUARTIER(1),
    QUARTIERBOUCLIER(1),
    CARREFOUR(2),
    CHEMIN(3),
    PRE(4),
    NONE(5);

    private int valeur;

    Terrain(int i) {
        valeur = i;
    }

    public int getValeur() {
        return valeur;
    }
}