package Communs;

import java.util.ArrayList;

/**
 * Class modélisant le plateau de jeu. C'est un tableau de tableau de piece.
 */
public class PlateauModel extends Carre {
    /** Plateau de jeu en 2D qui contient des pièces */
    private ArrayList<ArrayList<PieceControleur>> plateau;
    /** Hauteur des pieces contenue par le plateau */
    private int hauteurPiece;
    /** Largeur des pieces contenue par le plateau */
    private int largeurPiece;
    /** Colonne sur laquel le plateau est centree */
    private int actuelX;
    /** Ligne sur laquel le plateau est centree */
    private int actuelY;

    /**
     * Constructeur
     * 
     * @param hauteur      Hauteur du plateau
     * @param largeur      Largeur du plateau
     * @param hauteurPiece Hauteur des pieces contenue par le plateau
     * @param largeurPiece Largeur des pieces contenue par le plateau
     */
    public PlateauModel(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        if (hauteur <= 0) {
            hauteur = 1;
        }
        if (largeur <= 0) {
            largeur = 1;
        }
        plateau = new ArrayList<ArrayList<PieceControleur>>();
        // initialise chaque piece du plateau par null
        for (int i = 0; i < hauteur; i++) {
            plateau.add(new ArrayList<PieceControleur>());
            for (int j = 0; j < largeur; j++) {
                plateau.get(i).add(null);
            }
        }
        this.hauteurPiece = hauteurPiece;
        this.largeurPiece = largeurPiece;
        actuelX = 0;
        actuelY = 0;
    }

    // getters
    public int getActuelX() {
        return actuelX;
    }

    public int getActuelY() {
        return actuelY;
    }

    public int getHauteur() {
        return plateau.size();
    }

    public int getLargeur() {
        if (plateau.size() >= 1) {
            return plateau.get(0).size();
        }
        return 0;
    }

    public int getHauteurPiece() {
        return hauteurPiece;
    }

    public int getLargeurPiece() {
        return largeurPiece;
    }

    /**
     * Getter permettant d'obtenir la piece de la colonne x et de la ligne y du
     * plateau.
     * 
     * @param x colonne
     * @param y ligne
     * @return la piece de la colonne x et de la ligne y
     */
    public PieceControleur getPiece(int x, int y) {
        return plateau.get(y).get(x);
    }

    /**
     * Setter permettant de placer la piece sur la colonne x et de la ligne y du
     * plateau.
     * 
     * @param x     colonne
     * @param y     ligne
     * @param piece La piece a placer
     */
    public void setPiece(int x, int y, PieceControleur piece) {
        plateau.get(y).set(x, piece);
    }

    /**
     * Permet de se deplacer d'une colonne sur la droite sur le plateau.
     */
    public void allerADroite() {
        if (actuelX != getLargeur() - 1) {
            actuelX++;
        } else {
            System.out.println("Erreur : vous ne pouvez pas vous deplacer à droite.");
        }
    }

    /**
     * Permet de se deplacer d'une colonne sur la gauche sur le plateau.
     */
    public void allerAGauche() {
        if (actuelX != 0) {
            actuelX--;
        } else {
            System.out.println("Erreur : vous ne pouvez pas vous deplacer à gauche.");
        }
    }

    /**
     * Permet de se deplacer d'une ligne vers le bas sur le plateau.
     */
    public void allerEnBas() {
        if (actuelY != getHauteur() - 1) {
            actuelY++;
        } else {
            System.out.println("Erreur : vous ne pouvez pas vous deplacer en bas.");
        }
    }

    /**
     * Permet de se deplacer d'une ligne vers le haut sur le plateau.
     */
    public void allerEnHaut() {
        if (actuelY != 0) {
            actuelY--;
        } else {
            System.out.println("Erreur : vous ne pouvez pas vous deplacer en haut.");
        }
    }

    /**
     * Verifie si le tableau est vide
     * 
     * @return true si le tableau est vide,false si il y a au moins une piece.
     */
    public boolean isEmpty() {
        for (ArrayList<PieceControleur> ligne : plateau) {
            for (PieceControleur piece : ligne) {
                if (piece != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Permet d'etendre le tableau du plateau dans une direction donnee
     * 
     * @param dir cote que l'on souhaite agrandir.
     */
    public void ajouterUnCote(Direction dir) {
        switch (dir) {
            case LEFT:
                for (ArrayList<PieceControleur> ligne : plateau) {
                    // ajouter un element a la fin de chaque ligne
                    ligne.add(null);
                    for (int i = getLargeur() - 1; i > 0; i--) {
                        // deplacer tout les element vers la droite
                        ligne.set(i, ligne.get(i - 1));
                    }
                    // supprimer le premiere element
                    ligne.set(0, null);
                }
                // deplacer la position actuel vers la droite.
                actuelX++;
                break;
            case DOWN:
                // ajouter une ligne a la fin du plateau
                plateau.add(new ArrayList<PieceControleur>());
                // remplir cette ligne d'autant de colone qu'il faut
                for (int i = 0; i < getLargeur(); i++) {
                    plateau.get(plateau.size() - 1).add(null);
                }
                break;
            case UP:
                // ajouter une nouvelle ligne a la fin du plateau
                ArrayList<PieceControleur> newLine = new ArrayList<PieceControleur>();
                plateau.add(newLine);
                // ajoute autant de colonne qu'il faut
                for (int i = 0; i < getLargeur(); i++) {
                    newLine.add(null);
                }
                // deplacer tout les lignes sur les lignes du dessous
                for (int i = getHauteur() - 1; i > 0; i--) {
                    plateau.set(i, plateau.get(i - 1));
                }
                // remplacer la ligne du debut par une ligne vide
                plateau.set(0, newLine);
                // deplacer la position actuel vers le bas.
                actuelY++;
                break;
            case RIGHT:
                // ajoute un nouvelle element null a la fin de chaque ligne
                for (ArrayList<PieceControleur> ligne : plateau) {
                    ligne.add(null);
                }
                break;
            default:
                return;
        }
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
        if (getPiece(x + 1, y) != null) {
            res += getPiece(x + 1, y).somme(Direction.LEFT);
        }
        if (getPiece(x - 1, y) != null) {
            res += getPiece(x - 1, y).somme(Direction.RIGHT);
        }
        if (getPiece(x, y + 1) != null) {
            res += getPiece(x, y + 1).somme(Direction.UP);
        }
        if (getPiece(x, y - 1) != null) {
            res += getPiece(x, y - 1).somme(Direction.DOWN);
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
        if (getPiece(x, y) != null) {
            return false;
        }
        // si le plateau n'est pas vide
        if (!isEmpty()) {
            boolean cote1 = false;
            boolean cote2 = false;
            boolean cote3 = false;
            boolean cote4 = false;

            // si chaque cote adjacent et soit vide soir possede un cote adjacent commun a
            // la piece.
            if (y != 0) {
                if (getPiece(x, y - 1) == null || piece.comparer(Direction.UP, getPiece(x, y - 1))) {
                    cote1 = true;
                }
            } else
                cote1 = true;

            if (x != 0) {
                if (getPiece(x - 1, y) == null || piece.comparer(Direction.LEFT, getPiece(x - 1, y))) {
                    cote2 = true;
                }
            } else
                cote2 = true;

            if (y != getHauteur() - 1) {
                if (getPiece(x, y + 1) == null || piece.comparer(Direction.DOWN, getPiece(x, y + 1))) {
                    cote3 = true;
                    ;
                }
            } else
                cote3 = true;

            if (x != getLargeur() - 1) {
                if (getPiece(x + 1, y) == null || piece.comparer(Direction.RIGHT, getPiece(x + 1, y))) {
                    cote4 = true;
                }
            } else
                cote4 = true;

            // Aucun cote ne pose probleme au reglement. De plus il y a au moins une piece
            // qui est
            // adjacente a celle que je suis en train de pauser. En effet si il n'y a pas de
            // piece a cote. Cela veut dire que je ne pose pas une piece en fonction de ses
            // voisins mais qu'elle est independante des autres.
            if (cote1 && cote2 && cote3 && cote4
                    && ((y != 0 && getPiece(x, y - 1) != null) || (x != 0 && getPiece(x - 1, y) != null)
                            || (y != getHauteur() - 1 && getPiece(x, y + 1) != null)
                            || (x != getLargeur() - 1 && getPiece(x + 1, y) != null))) {
                return true;
            }
            return false;
        }
        // si le plateau est vide on peut la placer n'importe ou !
        return true;
    }
}
