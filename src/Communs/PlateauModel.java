package Communs;

/**
 * Class modélisant le plateau de jeu. C'est un tableau de tableau de piece.
 */
public class PlateauModel extends Extendable<PieceControleur> {
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

    public int getHauteurPiece() {
        return hauteurPiece;
    }

    public int getLargeurPiece() {
        return largeurPiece;
    }

    /**
     * Methode qui permet de calculer le nombre de point que repporte une piece
     * quand elle vient d'être placer.
     * 
     * @param x la colonne sur laquel la piece a ete placee
     * @param y la ligne sur laquel la piece a ete placee
     * @return le nombre de point que la piece fait gagner
     */
    public int calculePoint(int x, int y) {
        int res = 0;
        // fait la somme de chaque cote qui a une piece adjacente
        try {
            if (getPiece(x + 1, y) != null) {
                res += getPiece(x + 1, y).somme(Direction.LEFT);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(x - 1, y) != null) {
                res += getPiece(x - 1, y).somme(Direction.RIGHT);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(x, y + 1) != null) {
                res += getPiece(x, y + 1).somme(Direction.UP);
            }
        } catch (positionInvalide e) {
        }
        try {
            if (getPiece(x, y - 1) != null) {
                res += getPiece(x, y - 1).somme(Direction.DOWN);
            }
        } catch (positionInvalide e) {
        }
        return res;
    }

    /**
     * Verifie si il est possible de placer la piece quelque parts sur la plateau
     * 
     * @param piece Piece a placer
     * @return si il y a un endroit sur le plateau ou l'on peut placer la piece en
     *         respectant les regles.
     */
    public boolean possibleDePlacer(PieceControleur piece) {
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

    /**
     * Regarde si l'on peut placer une piece a de certaine coordonnee
     * 
     * @param piece Piece a placer
     * @param x     colonne a laquel on veut la placer
     * @param y     ligne a laquel on veut la placer
     * @return si c'est possible de placer la piece en respcetant les regles.
     */
    public boolean possibleDePlacer(PieceControleur piece, int x, int y) {
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