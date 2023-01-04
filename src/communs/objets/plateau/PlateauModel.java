package communs.objets.plateau;

import communs.exceptions.positionInvalide;
import communs.interfaces.plateau.InterfacePlateauModel;
import communs.objets.Direction;
import communs.objets.Point;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;

/**
 * Class modélisant le plateau de jeu. C'est un tableau de tableau de piece.
 * 
 * V est le types des valeurs qui apparaissent sur les pièces qui
 * pourront être placé sur le plateau.
 * Exemple : Integer dans le domino.
 */
public class PlateauModel<V> extends Extendable<PieceControleur<V>> implements InterfacePlateauModel<V> {
    /** Hauteur des pieces contenue par le plateau */
    private int hauteurPiece;
    /** Largeur des pieces contenue par le plateau */
    private int largeurPiece;

    /**
     * Constructeur
     * 
     * @param hauteurPiece Hauteur des pieces contenue par le plateau
     * @param largeurPiece Largeur des pieces contenue par le plateau
     */
    public PlateauModel(int hauteurPiece, int largeurPiece) {
        super(1, 1);
        this.hauteurPiece = hauteurPiece;
        this.largeurPiece = largeurPiece;
    }

    // getters
    @Override
    public int getHauteurPiece() {
        return hauteurPiece;
    }

    @Override
    public int getLargeurPiece() {
        return largeurPiece;
    }

    @Override
    /**
     * Methode qui permet de calculer le nombre de point que repporte une piece.
     * 
     * @param point position de la piece
     * @return le nombre de point que la piece fait gagner
     */
    public int calculePoint(Point point) {
        return 0;
    }

    @Override
    /**
     * Verifie si il est possible de placer la piece quelque parts sur la plateau
     * 
     * @param piece Piece a placer
     * @return si il y a un endroit sur le plateau ou l'on peut placer la piece en
     *         respectant les regles.
     */
    public boolean possibleDePlacer(PieceControleur<V> piece) {
        for (int pivot = 0; pivot < 4; pivot++) {
            for (int i = 0; i < getHauteur(); i++) {
                for (int j = 0; j < getLargeur(); j++) {
                    // regarde si l'on peu placer la piece a ses coordonnee
                    if (possibleDePlacer(piece, new Point(j, i))) {
                        // fait pivoter la piece jusqu'a ce qu'elle revienne a sa position de depart.
                        for (int pivot2 = pivot; pivot2 < 4; pivot2++) {
                            piece.tournerDroite();
                        }
                        return true;
                    }
                }
            }
            // si l'on ne peut pas la placer sur le plateau comme tel,
            // on fait pivote la piece puis regarde a nouveau si on peut la placer avec
            // cette nouvelle orientation
            piece.tournerDroite();
        }
        return false;
    }

    @Override
    /**
     * Regarde si l'on peut placer une piece a de certaine coordonnee
     * 
     * @param piece Piece a placer
     * @param point Position ou l'on souhaite essayer de placer la piece.
     * @return si c'est possible de placer la piece en respcetant les regles.
     */
    public boolean possibleDePlacer(PieceControleur<V> piece, Point point) {
        // si il y a deja une piece a cette emplacement ce n'est pas possible
        try {
            if (getPiece(point) != null) {
                return false;
            }
        } catch (positionInvalide e) {
            return false;
        }
        // si le plateau n'est pas vide
        if (!isEmpty()) {
            boolean cote1 = false;
            boolean cote2 = false;
            boolean cote3 = false;
            boolean cote4 = false;

            Point haut = new Point(point.getX(), point.getY() - 1);
            Point bas = new Point(point.getX(), point.getY() + 1);
            ;
            Point droite = new Point(point.getX() + 1, point.getY());
            ;
            Point gauche = new Point(point.getX() - 1, point.getY());
            ;

            // si chaque cote adjacent et soit vide soit possede un cote adjacent commun a
            // la piece.
            try {
                if (getPiece(haut) == null || piece.comparer(Direction.UP, getPiece(haut))) {
                    cote1 = true;
                }
            } catch (positionInvalide e) {
                cote1 = true;
            }

            try {
                if (getPiece(gauche) == null || piece.comparer(Direction.LEFT, getPiece(gauche))) {
                    cote2 = true;
                }
            } catch (positionInvalide e) {
                cote2 = true;
            }

            try {
                if (getPiece(bas) == null || piece.comparer(Direction.DOWN, getPiece(bas))) {
                    cote3 = true;
                    ;
                }
            } catch (positionInvalide e) {
                cote3 = true;
            }

            try {
                if (getPiece(droite) == null || piece.comparer(Direction.RIGHT, getPiece(droite))) {
                    cote4 = true;
                }
            } catch (positionInvalide e) {
                cote4 = true;
            }

            // Aucun cote ne pose probleme au reglement. De plus il y a au moins une piece
            // qui est
            // adjacente a celle que je suis en train de pauser. En effet si il n'y a pas de
            // piece a cote. Cela veut dire que je ne pose pas une piece en fonction de ses
            // voisins mais qu'elle est independante des autres.
            if (cote1 && cote2 && cote3 && cote4) {
                try {
                    if (getPiece(haut) != null) {
                        return true;
                    }
                } catch (positionInvalide e) {
                }
                try {
                    if (getPiece(gauche) != null) {
                        return true;
                    }
                } catch (positionInvalide e) {
                }
                try {
                    if (getPiece(bas) != null) {
                        return true;
                    }
                } catch (positionInvalide e) {
                }
                try {
                    if (getPiece(droite) != null) {
                        return true;
                    }
                } catch (positionInvalide e) {
                }
            }
            return false;
        }
        // si le plateau est vide on peut la placer n'importe ou !
        return true;
    }

    @Override
    /**
     * Donnes un coordonnée ou l'on peut placer la piece.
     * 
     * @param piece Piece a placer
     * @return des coordonnée ou l'on peut placer la pièce
     */
    public Point peutPlacer(PieceControleur<V> piece) {
        for (int pivot = 0; pivot < 4; pivot++) {
            for (int i = 0; i < getHauteur(); i++) {
                for (int j = 0; j < getLargeur(); j++) {
                    // regarde si l'on peu placer la piece a ses coordonnee
                    Point res = new Point(j, i);
                    if (possibleDePlacer(piece, res)) {
                        return res;
                    }
                }
            }
            // si l'on ne peut pas la placer sur le plateau comme tel,
            // on fait pivote la piece puis regarde a nouveau si on peut la placer avec
            // cette nouvelle orientation
            piece.tournerDroite();
        }
        return null;
    }

    /**
     * Methode qui permet d'initialiser le plateau avec un pièce en son centre.
     * 
     * @param sac sac du quel est tiré la pièce.
     */
    @Override
    public void start(Sac<PieceControleur<V>> sac) {
        try {
            PieceControleur<V> piece = sac.tire();
            piece.getView().setimagePiece();
            setPiece(new Point(0, 0), piece);
        } catch (positionInvalide e) {
        }
    }
}
