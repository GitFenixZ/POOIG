package carcassonne;

import java.util.ArrayList;

import communs.objets.piece.PieceModel;

public class CarcassonnePieceModel extends PieceModel<Terrain> {

    CarcassonnePieceModel(Terrain[][] map) {
        super(5, 5, Terrain.NONE);
        for (int i = 0; i < getLargeur(); i++) {
            for (int j = 0; j < getLargeur(); j++) {
                valeurs.get(i).set(j, map[i][j]);
            }
        }
    }

    @Override
    public String getligne(int indice) {
        String res = "";
        for (int i = 0; i < valeurs.get(indice).size(); i++) {
            switch (valeurs.get(indice).get(i)) {
                case NONE:
                    if (i == 0) {
                        res += getsymbole(valeurs.get(indice).get(i + 1));
                    } else {
                        res += getsymbole(valeurs.get(indice).get(i - 1));
                    }
                    break;
                default:
                    res += getsymbole(valeurs.get(indice).get(i));
                    break;
            }
        }
        return res + " ";
    }

    private String getsymbole(Terrain t) {
        switch (t) {
            case ABBAYE:
                return " A";
            case QUARTIER:
                return " Q";
            case QUARTIERBOUCLIER:
                return " B";
            case CARREFOUR:
                return " C";
            case CHEMIN:
                return " ~";
            case PRE:
                return " P";
            default:
                return "  ";

        }
    }

    /**
     * Methode qui compare si deux coté son campatibles.
     * Deux coté sont compatible si ils ont la même valeurs.
     */
    @Override
    protected boolean compare(ArrayList<Terrain> cote1, ArrayList<Terrain> cote2) {
        for (int j = 0; j < cote1.size(); j++) {
            if (cote1.get(j).getValeur() != cote2.get(j).getValeur()) {
                return false;
            }
        }
        return true;
    }

    public static Terrain[][] un =
            /* */ { { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] deux =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.QUARTIER, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.CHEMIN },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.CHEMIN, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] trois =
            /* */ { { Terrain.NONE, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                    Terrain.QUARTIERBOUCLIER, Terrain.NONE },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.CHEMIN },
                    { Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.CHEMIN, Terrain.CHEMIN, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] quatre =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.CHEMIN, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] cinq =
            /* */ { { Terrain.NONE, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                    Terrain.QUARTIERBOUCLIER, Terrain.NONE },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] six =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.QUARTIER, Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.CHEMIN },
                    { Terrain.QUARTIER, Terrain.PRE, Terrain.CHEMIN, Terrain.CHEMIN, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] sept =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.PRE, Terrain.CHEMIN, Terrain.CHEMIN },
                    { Terrain.PRE, Terrain.CHEMIN, Terrain.CARREFOUR, Terrain.CHEMIN, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] huit =
            /* */ { { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] neuf =
            /* */ { { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CARREFOUR, Terrain.CHEMIN, Terrain.CHEMIN },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] dix =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.PRE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] onze =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.QUARTIER },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.QUARTIER },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.QUARTIER },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] douze =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.QUARTIER, Terrain.PRE, Terrain.PRE, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] treize =
            /* */ { { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.ABBAYE, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] quatroze =
            /* */ { { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.ABBAYE, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] quize =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.PRE },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.QUARTIER, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] seize =
            /* */ { { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] dixsept =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CHEMIN },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] dixhuit =
            /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                    { Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] dixneuf =
            /* */ { { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] vingt =
            /* */ { { Terrain.NONE, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                    Terrain.QUARTIERBOUCLIER, Terrain.NONE },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.CHEMIN, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER },
                    { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

    public static Terrain[][] vingtun =
            /* */ { { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                    Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                            Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER } };

    public static Terrain[][] vingtdeux =
            /* */ { { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CARREFOUR, Terrain.CHEMIN, Terrain.CHEMIN },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                    { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE } };

    public static Terrain[][] vingttrois =
            /* */ { { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.NONE,
                    Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE,
                            Terrain.PRE },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.PRE, Terrain.PRE },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.PRE } };

    public static Terrain[][] vingtquatre =
            /* */ { { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.QUARTIER, Terrain.QUARTIER },
                    { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };
}
