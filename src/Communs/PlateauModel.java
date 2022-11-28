package Communs;

import java.util.ArrayList;

/**
 * Class modélisant le plateau de jeu. C'est un tableau de tableau de piece.
 */
public class PlateauModel extends Carre {
    private ArrayList<ArrayList<PieceControleur>> plateau;
    private boolean isEmpty;
    private int hauteurPiece;
    private int largeurPiece;
    private PieceControleur defaultPiece;
    private int actuelX;
    private int actuelY;

    public PlateauModel(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        if (hauteur <= 0) {
            hauteur = 1;
        }
        if (largeur <= 0) {
            largeur = 1;
        }
        plateau = new ArrayList<ArrayList<PieceControleur>>();
        for (int i = 0; i < hauteur; i++) {
            plateau.add(new ArrayList<PieceControleur>());
            for (int j = 0; j < largeur; j++) {
                plateau.get(i).add(defaultPiece);
            }
        }
        isEmpty = true;
        this.hauteurPiece = hauteurPiece;
        this.largeurPiece = largeurPiece;
        actuelX = 0;
        actuelY = 0;
    }

    public int getActuelX() {
        return actuelX;
    }

    public int getActuelY() {
        return actuelY;
    }

    public PieceControleur getPiece(int x, int y) {
        return plateau.get(y).get(x);
    }

    public void setPiece(int x, int y, PieceControleur piece) {
        plateau.get(y).set(x, piece);
        isEmpty = false;
    }

    public void allerADroite() {
        if (actuelX != getLargeur() - 1) {
            actuelX++;
        } else {
            System.out.println("Erreur : vous ne pouvez pas vous deplacer à droite.");
        }
    }

    public void allerAGauche() {
        if (actuelX != 0) {
            actuelX--;
        } else {
            System.out.println("Erreur : vous ne pouvez pas vous deplacer à gauche.");
        }
    }

    public void allerEnBas() {
        if (actuelY != getHauteur() - 1) {
            actuelY++;
        } else {
            System.out.println("Erreur : vous ne pouvez pas vous deplacer en bas.");
        }
    }

    public void allerEnHaut() {
        if (actuelY != 0) {
            actuelY--;
        } else {
            System.out.println("Erreur : vous ne pouvez pas vous deplacer en haut.");
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getHauteur() {
        return plateau.size();
    }

    public int getLargeur() {
        return plateau.get(0).size();
    }

    public int getHauteurPiece() {
        return hauteurPiece;
    }

    public int getLargeurPiece() {
        return largeurPiece;
    }

    public void ajouterUnCote(Direction dir) {
        switch (dir) {
            case LEFT:
                for (ArrayList<PieceControleur> ligne : plateau) {
                    ligne.add(defaultPiece);
                    for (int i = getLargeur() - 1; i > 0; i--) {
                        ligne.set(i, ligne.get(i - 1));
                    }
                    ligne.set(0, defaultPiece);
                }
                actuelX++;
                break;
            case DOWN:
                plateau.add(new ArrayList<PieceControleur>());
                for (int i = 0; i < getLargeur(); i++) {
                    plateau.get(plateau.size() - 1).add(defaultPiece);
                }
                break;
            case UP:
                ArrayList<PieceControleur> newLine = new ArrayList<PieceControleur>();
                plateau.add(newLine);
                for (int i = getHauteur() - 1; i > 0; i--) {
                    plateau.set(i, plateau.get(i - 1));
                }
                plateau.set(0, newLine);
                for (int i = 0; i < plateau.get(1).size(); i++) {
                    newLine.add(defaultPiece);
                }
                actuelY++;
                break;
            case RIGHT:
                for (ArrayList<PieceControleur> ligne : plateau) {
                    ligne.add(defaultPiece);
                }
                break;
            default:
                return;
        }
    }

    public int calculePoint(int x, int y) {
        int res = 0;
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

    public boolean possibleDePlacer(PieceControleur piece) {
        for (int pivot = 0; pivot < 4; pivot++) {
            for (int i = 0; i < getHauteur(); i++) {
                for (int j = 0; j < getLargeur(); j++) {
                    if (possibleDePlacer(piece, j, i)) {
                        for (int pivot2 = pivot; pivot2 < 4; pivot2++) {
                            piece.pivotDroite();
                        }
                        return true;
                    }
                }
            }
            piece.pivotDroite();
        }
        return false;
    }

    public boolean possibleDePlacer(PieceControleur piece, int x, int y) {
        if (getPiece(x, y) != null) {
            return false;
        }
        if (!isEmpty()) {
            boolean cote1 = false;
            boolean cote2 = false;
            boolean cote3 = false;
            boolean cote4 = false;
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

            if (cote1 && cote2 && cote3 && cote4
                    && ((y != 0 && getPiece(x, y - 1) != null) || (x != 0 && getPiece(x - 1, y) != null)
                            || (y != getHauteur() - 1 && getPiece(x, y + 1) != null)
                            || (x != getLargeur() - 1 && getPiece(x + 1, y) != null))) {
                return true;
            }
            return false;
        }
        return true;
    }
}
