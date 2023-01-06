package communs.objets.plateau;

import java.util.ArrayList;

import communs.exceptions.directionInvalide;
import communs.exceptions.positionInvalide;
import communs.interfaces.plateau.InterfaceExtendable;
import communs.objets.Direction;
import communs.objets.Point;

/**
 * Class qui représente un tableau en 2D qui contient des objet de type P et qui
 * est de taille infini. C'est a dire qui est entendable tant que l'on souhaite.
 * 
 * P est le types des objet qui seront placer dans la tableau.
 * Exemple: PieceControleur<Integer>
 */
public abstract class Extendable<P> implements InterfaceExtendable<P> {

    /** Tableau de jeu en 2D qui contient des P */
    protected ArrayList<ArrayList<P>> tableau;

    /** Position ou le tableau est centré a l'affichage */
    protected Point actuelPosition;

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
        actuelPosition = new Point(0, 0);
    }

    // getters
    @Override
    public Point getActuelPosition() {
        return actuelPosition;
    }

    @Override
    public int getActuelX() {
        return actuelPosition.getX();
    }

    @Override
    public int getActuelY() {
        return actuelPosition.getY();
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
     * Getters du tableau de valeur
     * 
     * @return une copy du tableau.
     */

    public ArrayList<ArrayList<P>> getTableau() {
        ArrayList<ArrayList<P>> res = new ArrayList<ArrayList<P>>();
        for (int i = 0; i < getHauteur(); i++) {
            res.add(new ArrayList<P>());
            for (int j = 0; j < getLargeur(); j++) {
                res.get(i).add(tableau.get(i).get(j));
            }
        }
        return res;
    }

    @Override
    /**
     * Getter permettant d'obtenir la pièce à la position point du
     * plateau.
     * 
     * @param point position que l'on souhaite
     * @return la pièce de position point
     * @throws positionInvalide Si la valeur les coordonnées sont à l’extérieur du
     *                          tableau.
     */
    public P getPiece(Point point) throws positionInvalide {
        if (point.getX() < 0 || point.getX() >= getLargeur() || point.getY() < 0 || point.getY() >= getHauteur()) {
            throw new positionInvalide();
        }
        return tableau.get(point.getY()).get(point.getX());
    }

    @Override
    /**
     * Setter permettant de placer la pièce le point p du
     * plateau.
     * 
     * @param point position sur laquelle la pièce vas être placée
     * @param piece La piece a placer
     * @throws positionInvalide Si la valeur les coordonnées sont a l’extérieur du
     *                          tableau.
     */
    public void setPiece(Point point, P piece) throws positionInvalide {
        if (point.getX() < 0 || point.getX() > getLargeur() || point.getY() < 0 || point.getY() > getHauteur()) {
            throw new positionInvalide();
        }
        tableau.get(point.getY()).set(point.getX(), piece);
        /*
         * Tableau infini :
         * Si la piece est pose sur un cote. On élargie la tableau sur ce cote en
         * question.
         */
        try {
            if (point.getX() == getLargeur() - 1) {
                ajouterUnCote(Direction.RIGHT);
            }
            if (point.getX() == 0) {
                ajouterUnCote(Direction.LEFT);
                point.allerADroite();
                if (point != actuelPosition) {
                    actuelPosition.allerADroite();
                }
            }
            if (point.getY() == getHauteur() - 1) {
                ajouterUnCote(Direction.DOWN);
            }
            if (point.getY() == 0) {
                ajouterUnCote(Direction.UP);
                point.allerEnBas();
                if (point != actuelPosition) {
                    actuelPosition.allerEnBas();
                }
            }
        } catch (directionInvalide e) {
        }
    }

    @Override
    /**
     * Permet de se déplacer d'une colonne sur la droite sur le plateau.
     * 
     * @throws positionInvalide Si la valeur de actuelX sort du tableau.
     */
    public void allerADroite() throws positionInvalide {
        if (actuelPosition.getX() < getLargeur() - 1) {
            actuelPosition.allerADroite();
        } else {
            throw new positionInvalide();
        }
    }

    @Override
    /**
     * Permet de se déplacer d'une colonne sur la gauche sur le plateau.
     * 
     * @throws positionInvalide Si la valeur de actuelX sort du tableau.
     */
    public void allerAGauche() throws positionInvalide {
        if (actuelPosition.getX() > 0) {
            actuelPosition.allerAGauche();
        } else {
            throw new positionInvalide();
        }
    }

    @Override
    /**
     * Permet de se déplacer d'une ligne vers le bas sur le plateau.
     * 
     * @throws positionInvalide Si la valeur de actuelY sort du tableau.
     */
    public void allerEnBas() throws positionInvalide {
        if (actuelPosition.getY() < getHauteur() - 1) {
            actuelPosition.allerEnBas();
        } else {
            throw new positionInvalide();
        }
    }

    @Override
    /**
     * Permet de se déplacer d'une ligne vers le haut sur le plateau.
     * 
     * @throws positionInvalide Si la valeur de actuelY sort du tableau.
     */
    public void allerEnHaut() throws positionInvalide {
        if (actuelPosition.getY() > 0) {
            actuelPosition.allerEnHaut();
        } else {
            throw new positionInvalide();
        }
    }

    @Override
    /**
     * Vérifie si le tableau est vide
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
     * Permet d’étendre le tableau du plateau dans une direction donnée
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
     * Méthode qui extends le tableau
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
                // déplacer tout les element vers la droite
                ligne.set(i, ligne.get(i - 1));
            }
            // supprimer le premier element
            ligne.set(0, null);
        }
    }

    private void ajouterUnCoteBas() {
        // ajouter une ligne a la fin du plateau
        tableau.add(new ArrayList<P>());
        // remplir cette ligne d'autant de colonne qu'il faut
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
        // déplacer toutes les lignes sur les lignes du dessous
        for (int i = getHauteur() - 1; i > 0; i--) {
            tableau.set(i, tableau.get(i - 1));
        }
        // remplacer la ligne du début par une ligne vide
        tableau.set(0, newLine);
    }
}
