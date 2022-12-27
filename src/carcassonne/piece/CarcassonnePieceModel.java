package carcassonne.piece;

import java.util.ArrayList;

import carcassonne.Partissant;
import carcassonne.joueurs.CarcassonnePlayer;
import communs.objets.Point;
import communs.objets.piece.PieceModel;

/**
 * Modelise le model des piece de plateau de carcassonne.
 */
public class CarcassonnePieceModel extends PieceModel<Terrain> {

        /**
         * Seconde representation de la pièce ou l'on pourra placer un partisant.
         */
        private ArrayList<ArrayList<Partissant>> partisant;

        // valeur a utiliser. Comme ça on peut modifier les pièces comme on veut, on
        // aura juste a modifier ça aussi.
        public static final int largeurPiece = 5;
        public static final int hauteurPiece = 5;

        CarcassonnePieceModel(Terrain[][] map) {
                super(5, 5, Terrain.NONE);
                partisant = new ArrayList<ArrayList<Partissant>>();
                for (int i = 0; i < getLargeur(); i++) {
                        partisant.add(new ArrayList<Partissant>());
                        for (int j = 0; j < getLargeur(); j++) {
                                valeurs.get(i).set(j, map[i][j]);
                                partisant.get(i).add(null);
                        }
                }
        }

        /**
         * Place un partisant du joueur au coordoonee du point
         * 
         * @param point  coordonnees auquel sera placer le partisant sur la piece
         * @param player joueur qui va placer un partisant
         */
        public void placerPartisant(Point point, CarcassonnePlayer player) {
                if (!player.partisantsIsEmpty()) {
                        partisant.get(point.getY()).set(point.getX(), player.peekPartisant());
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
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.PRE,
                                                        Terrain.PRE },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.PRE, Terrain.CHEMIN,
                                                        Terrain.CHEMIN },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.CHEMIN, Terrain.CHEMIN,
                                                        Terrain.PRE },
                                        { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

        public static Terrain[][] quatre =
                        /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.CHEMIN, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

        public static Terrain[][] cinq =
                        /* */ { { Terrain.NONE, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                        Terrain.QUARTIERBOUCLIER, Terrain.NONE },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE,
                                                        Terrain.QUARTIERBOUCLIER,
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
                                        { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CARREFOUR, Terrain.CHEMIN,
                                                        Terrain.CHEMIN },
                                        { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                                        { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

        public static Terrain[][] dix =
                        /* */ { { Terrain.NONE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.NONE },
                                        { Terrain.PRE, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER,
                                                        Terrain.PRE },
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
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.QUARTIER, Terrain.PRE, Terrain.PRE, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
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
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE,
                                                        Terrain.PRE },
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
                                        { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CHEMIN,
                                                        Terrain.CHEMIN },
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
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

        public static Terrain[][] vingt =
                        /* */ { { Terrain.NONE, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                        Terrain.QUARTIERBOUCLIER, Terrain.NONE },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.CHEMIN,
                                                        Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER },
                                        { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

        public static Terrain[][] vingtun =
                        /* */ { { Terrain.NONE, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                        Terrain.QUARTIERBOUCLIER, Terrain.NONE },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER },
                                        { Terrain.NONE, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.QUARTIERBOUCLIER, Terrain.NONE } };

        public static Terrain[][] vingtdeux =
                        /* */ { { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE },
                                        { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                                        { Terrain.CHEMIN, Terrain.CHEMIN, Terrain.CARREFOUR, Terrain.CHEMIN,
                                                        Terrain.CHEMIN },
                                        { Terrain.PRE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.PRE },
                                        { Terrain.NONE, Terrain.PRE, Terrain.CHEMIN, Terrain.PRE, Terrain.NONE } };

        public static Terrain[][] vingttrois =
                        /* */ { { Terrain.NONE, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                        Terrain.QUARTIERBOUCLIER, Terrain.NONE },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER,
                                                        Terrain.PRE,
                                                        Terrain.PRE },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.PRE,
                                                        Terrain.PRE },
                                        { Terrain.QUARTIERBOUCLIER, Terrain.QUARTIERBOUCLIER, Terrain.PRE, Terrain.PRE,
                                                        Terrain.PRE },
                                        { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };

        public static Terrain[][] vingtquatre =
                        /* */ { { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.QUARTIER, Terrain.QUARTIER, Terrain.PRE, Terrain.QUARTIER,
                                                        Terrain.QUARTIER },
                                        { Terrain.NONE, Terrain.PRE, Terrain.PRE, Terrain.PRE, Terrain.NONE } };
}
