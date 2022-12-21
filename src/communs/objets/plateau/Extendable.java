package communs.objets.plateau;

import java.util.ArrayList;

import communs.exceptions.directionInvalide;
import communs.exceptions.positionInvalide;
import communs.interfaces.Direction;
import communs.interfaces.plateau.InterfaceExtendable;

/**
 * Class qui represente un tableau en 2D qui contient des objet de type V et qui
 * est de taille infini. C'est a dire qui est entendable tant que l'on souhaite.
 * 
 * P est le types des objet qui seront placer dans la tableau.
 * Exemple: PieceControleur<Integer>
 */
public abstract class Extendable<P> implements InterfaceExtendable<P> {

    /** Tableau de jeu en 2D qui contient des V */
    protected ArrayList<ArrayList<P>> tableau;
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
        tableau = new ArrayList<ArrayList<P>>();
        // initialise chaque piece du plateau par null
        for (int i = 0; i < hauteur; i++) {
            tableau.add(new ArrayList<P>());
            for (int j = 0; j < largeur; j++) {
                tableau.get(i).add(null);
            }
        }
        actuelX = 0;
        actuelY = 0;
    }

    // getters
    @Override
    public int getActuelX() {
        return actuelX;
    }

    @Override
    public int getActuelY() {
        return actuelY;
    }

    @Override
    public int getHauteur() {
        return tableau.size();
    }

    @Override
    public int getLargeur() {
        if (tableau.size() >= 1) {
            return tableau.get(0).size();
        }
        return 0;
    }

    @Override
    /**
     * Getter permettant d'obtenir la piece de la colonne x et de la ligne y du
     * plateau.
     * 
     * @param x colonne
     * @param y ligne
     * @return la piece de la colonne x et de la ligne y
     * @throws positionInvalide Si la valeur les coorodonnees sont a l'exterieure du
     *                          tableau.
     */
    public P getPiece(int x, int y) throws positionInvalide {
        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur()) {
            throw new positionInvalide();
        }
        return tableau.get(y).get(x);
    }

    @Override
    /**
     * Setter permettant de placer la piece sur la colonne x et de la ligne y du
     * plateau.
     * 
     * @param x     colonne
     * @param y     ligne
     * @param piece La piece a placer
     * @throws positionInvalide Si la valeur les coorodonnees sont a l'exterieure du
     *                          tableau.
     */
    public void setPiece(int x, int y, P piece) throws positionInvalide {
        if (x < 0 || x > getLargeur() || y < 0 || y > getHauteur()) {
            throw new positionInvalide();
        }
        tableau.get(y).set(x, piece);
        /*
         * Tableau infini :
         * Si la piece est pose sur un cote. On elargie la tableau sur ce cote en
         * question.
         */
        try {
            if (x == getLargeur() - 1) {
                ajouterUnCote(Direction.RIGHT);
            }
            if (x == 0) {
                ajouterUnCote(Direction.LEFT);
            }
            if (y == getHauteur() - 1) {
                ajouterUnCote(Direction.DOWN);
            }
            if (y == 0) {
                ajouterUnCote(Direction.UP);
            }
        } catch (directionInvalide e) {
        }
    }

    @Override
    /**
     * Permet de se deplacer d'une colonne sur la droite sur le plateau.
     * 
     * @throws positionInvalide Si la valeur de actuelX sort du tableau.
     */
    public void allerADroite() throws positionInvalide {
        if (actuelX < getLargeur() - 1) {
            actuelX++;
        } else {
            throw new positionInvalide();
        }
    }

    @Override
    /**
     * Permet de se deplacer d'une colonne sur la gauche sur le plateau.
     * 
     * @throws positionInvalide Si la valeur de actuelX sort du tableau.
     */
    public void allerAGauche() throws positionInvalide {
        if (actuelX > 0) {
            actuelX--;
        } else {
            throw new positionInvalide();
        }
    }

    @Override
    /**
     * Permet de se deplacer d'une ligne vers le bas sur le plateau.
     * 
     * @throws positionInvalide Si la valeur de actuelY sort du tableau.
     */
    public void allerEnBas() throws positionInvalide {
        if (actuelY < getHauteur() - 1) {
            actuelY++;
        } else {
            throw new positionInvalide();
        }
    }

    @Override
    /**
     * Permet de se deplacer d'une ligne vers le haut sur le plateau.
     * 
     * @throws positionInvalide Si la valeur de actuelY sort du tableau.
     */
    public void allerEnHaut() throws positionInvalide {
        if (actuelY > 0) {
            actuelY--;
        } else {
            throw new positionInvalide();
        }
    }

    @Override
    /**
     * Verifie si le tableau est vide
     * 
     * @return true si le tableau est vide,false si il y a au moins une piece.
     */
    public boolean isEmpty() {
        for (ArrayList<P> ligne : tableau) {
            for (P piece : ligne) {
                if (piece != null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    /**
     * Permet d'etendre le tableau du plateau dans une direction donnee
     * 
     * @param dir cote que l'on souhaite agrandir.
     * @throws directionInvalide Si la direction est ACTUEL
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
        for (ArrayList<P> ligne : tableau) {
            ligne.add(null);
        }
    }

    private void ajouterUnCoteGauche() {
        for (ArrayList<P> ligne : tableau) {
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
        tableau.add(new ArrayList<P>());
        // remplir cette ligne d'autant de colone qu'il faut
        for (int i = 0; i < getLargeur(); i++) {
            tableau.get(tableau.size() - 1).add(null);
        }
    }

    private void ajouterUnCoteHaut() {
        // ajouter une nouvelle ligne a la fin du plateau
        ArrayList<P> newLine = new ArrayList<P>();
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
