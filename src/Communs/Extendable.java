package Communs;

import java.util.ArrayList;

/**
 * Class qui represente un tableau en 2D qui contient des objet de type T et qui
 * est de taille infini. C'est a dire qui est entendable tant que l'on souhaite.
 */
public class Extendable<T> implements Carre {

    /** Tableau de jeu en 2D qui contient des T */
    protected ArrayList<ArrayList<T>> tableau;
    /** Colonne sur laquel le plateau est centree */
    protected int actuelX;
    /** Ligne sur laquel le plateau est centree */
    protected int actuelY;

    /**
     * Constructeur
     * 
     * @param hauteur hauteur de tableau
     * @param largeur largeur du tableau
     */
    Extendable(int hauteur, int largeur) {
        if (hauteur <= 0) {
            hauteur = 1;
        }
        if (largeur <= 0) {
            largeur = 1;
        }
        tableau = new ArrayList<ArrayList<T>>();
        // initialise chaque piece du plateau par null
        for (int i = 0; i < hauteur; i++) {
            tableau.add(new ArrayList<T>());
            for (int j = 0; j < largeur; j++) {
                tableau.get(i).add(null);
            }
        }
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
        return tableau.size();
    }

    public int getLargeur() {
        if (tableau.size() >= 1) {
            return tableau.get(0).size();
        }
        return 0;
    }

    /**
     * Getter permettant d'obtenir la piece de la colonne x et de la ligne y du
     * plateau.
     * 
     * @param x colonne
     * @param y ligne
     * @return la piece de la colonne x et de la ligne y
     */
    public T getPiece(int x, int y) throws positionInvalide {
        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur()) {
            throw new positionInvalide();
        }
        return tableau.get(y).get(x);
    }

    /**
     * Setter permettant de placer la piece sur la colonne x et de la ligne y du
     * plateau.
     * 
     * @param x     colonne
     * @param y     ligne
     * @param piece La piece a placer
     */
    public void setPiece(int x, int y, T piece) throws positionInvalide {
        if (x < 0 || x > getLargeur() || y < 0 || y > getHauteur()) {
            throw new positionInvalide();
        }
        tableau.get(y).set(x, piece);
    }

    /**
     * Permet de se deplacer d'une colonne sur la droite sur le plateau.
     */
    public void allerADroite() throws positionInvalide {
        if (actuelX < getLargeur() - 1) {
            actuelX++;
        } else {
            throw new positionInvalide();
        }
    }

    /**
     * Permet de se deplacer d'une colonne sur la gauche sur le plateau.
     */
    public void allerAGauche() throws positionInvalide {
        if (actuelX > 0) {
            actuelX--;
        } else {
            throw new positionInvalide();
        }
    }

    /**
     * Permet de se deplacer d'une ligne vers le bas sur le plateau.
     */
    public void allerEnBas() throws positionInvalide {
        if (actuelY < getHauteur() - 1) {
            actuelY++;
        } else {
            throw new positionInvalide();
        }
    }

    /**
     * Permet de se deplacer d'une ligne vers le haut sur le plateau.
     */
    public void allerEnHaut() throws positionInvalide {
        if (actuelY > 0) {
            actuelY--;
        } else {
            throw new positionInvalide();
        }
    }

    /**
     * Verifie si le tableau est vide
     * 
     * @return true si le tableau est vide,false si il y a au moins une piece.
     */
    public boolean isEmpty() {
        for (ArrayList<T> ligne : tableau) {
            for (T piece : ligne) {
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
    public void ajouterUnCote(Direction dir) throws directionInvalide {
        switch (dir) {
            case LEFT:
                ajouterUnCoteGauche();
                break;
            case DOWN:
                ajouterUnCoteBas();
                break;
            case UP:
                ajouterUnCoteHaut();
                break;
            case RIGHT:
                ajouterUnCoteDroit();
                break;
            default:
                throw new directionInvalide();
        }
    }

    /**
     * Methode qui extends
     */
    private void ajouterUnCoteDroit() {
        for (ArrayList<T> ligne : tableau) {
            ligne.add(null);
        }
    }

    private void ajouterUnCoteGauche() {
        for (ArrayList<T> ligne : tableau) {
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
    }

    private void ajouterUnCoteBas() {
        // ajouter une ligne a la fin du plateau
        tableau.add(new ArrayList<T>());
        // remplir cette ligne d'autant de colone qu'il faut
        for (int i = 0; i < getLargeur(); i++) {
            tableau.get(tableau.size() - 1).add(null);
        }
    }

    private void ajouterUnCoteHaut() {
        // ajouter une nouvelle ligne a la fin du plateau
        ArrayList<T> newLine = new ArrayList<T>();
        tableau.add(newLine);
        // ajoute autant de colonne qu'il faut
        for (int i = 0; i < getLargeur(); i++) {
            newLine.add(null);
        }
        // deplacer tout les lignes sur les lignes du dessous
        for (int i = getHauteur() - 1; i > 0; i--) {
            tableau.set(i, tableau.get(i - 1));
        }
        // remplacer la ligne du debut par une ligne vide
        tableau.set(0, newLine);
        // deplacer la position actuel vers le bas.
        actuelY++;
    }
}
