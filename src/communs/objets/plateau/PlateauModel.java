package communs.objets.plateau;

import communs.exceptions.positionInvalide;
import communs.interfaces.plateau.InterfacePlateauModel;
import communs.objets.piece.PieceControleur;
import domino.DominoPlateauModel;
import communs.interfaces.Direction;

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
     * @param hauteur      Hauteur du plateau
     * @param largeur      Largeur du plateau
     * @param hauteurPiece Hauteur des pieces contenue par le plateau
     * @param largeurPiece Largeur des pieces contenue par le plateau
     */
    public PlateauModel(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        super(hauteur, largeur);
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
     * Methode qui permet de calculer le nombre de point que repporte une piece
     * quand elle vient d'être placer.
     * 
     * @param x la colonne sur laquel la piece a ete placee
     * @param y la ligne sur laquel la piece a ete placee
     * @return le nombre de point que la piece fait gagner
     */
    public int calculePoint(int x, int y) {
        System.out.println("Test !");
        if (this instanceof DominoPlateauModel) {
            System.out.println("Mais t es ou !");
            return ((DominoPlateauModel) this).calculePoint(x, y);
        }
        System.out.println("PAS LA !");
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
                    if (possibleDePlacer(piece, j, i)) {
                        // fait pivoter la piece jusqu'a ce qu'elle revienne a sa position de depart.
                        for (int pivot2 = pivot; pivot2 < 4; pivot2++) {
                            piece.pivotDroite();
                        }
                        return true;
                    }
                }
            }
            // si l'on ne peut pas la placer sur le plateau comme tel,
            // on fait pivote la piece puis regarde a nouveau si on peut la placer avec
            // cette nouvelle orientation
            piece.pivotDroite();
        }
        return false;
    }

    @Override
    /**
     * Regarde si l'on peut placer une piece a de certaine coordonnee
     * 
     * @param piece Piece a placer
     * @param x     colonne a laquel on veut la placer
     * @param y     ligne a laquel on veut la placer
     * @return si c'est possible de placer la piece en respcetant les regles.
     */
    public boolean possibleDePlacer(PieceControleur<V> piece, int x, int y) {
        // si il y a deja une piece a cette emplacement ce n'est pas possible
        try {
            if (getPiece(x, y) != null) {
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

            // si chaque cote adjacent et soit vide soit possede un cote adjacent commun a
            // la piece.
            try {
                if (getPiece(x, y - 1) == null || piece.comparer(Direction.UP, getPiece(x, y - 1))) {
                    cote1 = true;
                }
            } catch (positionInvalide e) {
                cote1 = true;
            }

            try {
                if (getPiece(x - 1, y) == null || piece.comparer(Direction.LEFT, getPiece(x - 1, y))) {
                    cote2 = true;
                }
            } catch (positionInvalide e) {
                cote2 = true;
            }

            try {
                if (getPiece(x, y + 1) == null || piece.comparer(Direction.DOWN, getPiece(x, y + 1))) {
                    cote3 = true;
                    ;
                }
            } catch (positionInvalide e) {
                cote3 = true;
            }

            try {
                if (getPiece(x + 1, y) == null || piece.comparer(Direction.RIGHT, getPiece(x + 1, y))) {
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
                    if (getPiece(x, y - 1) != null) {
                        return true;
                    }
                } catch (positionInvalide e) {
                }
                try {
                    if (getPiece(x - 1, y) != null) {
                        return true;
                    }
                } catch (positionInvalide e) {
                }
                try {
                    if (getPiece(x, y + 1) != null) {
                        return true;
                    }
                } catch (positionInvalide e) {
                }
                try {
                    if (getPiece(x + 1, y) != null) {
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
}
