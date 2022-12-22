package communs.objets.piece;

import java.util.ArrayList;

import communs.exceptions.directionInvalide;
import communs.interfaces.Direction;
import communs.interfaces.piece.InterfacePieceModel;

/**
 * Class modélisant une piece de jeu, c'est à dire les vauleurs qu'elle prends.
 * Ainsi que ses dimensions.
 * 
 * V est le types des valeur qui apparaissent sur la pièce.
 * Exemple : Integer dans le domino.
 */

public abstract class PieceModel<V> implements InterfacePieceModel<V> {
    /** Element vide par defaut */
    private V vide;
    /** Valeurs contenue dans la piece */
    protected ArrayList<ArrayList<V>> valeurs;
    /** Hauteur de la piece */
    private int hauteur;
    /** Largeur de la piece */
    private int largeur;

    /**
     * Constructeur
     * 
     * @param hauteur La hauteur de la pièce
     * @param largeur La largeur de la pièce
     */
    public PieceModel(int hauteur, int largeur, V vide) {
        this.vide = vide;
        this.hauteur = hauteur;
        this.largeur = largeur;
        valeurs = new ArrayList<ArrayList<V>>();
        // initialise le tableau par des -1. Par conséquent -1 signifie que la case n'a
        // pas de valeur spécifié.
        for (int i = 0; i < hauteur; i++) {
            valeurs.add(new ArrayList<V>());
            for (int j = 0; j < largeur; j++) {
                valeurs.get(i).add(vide);
            }
        }
    }

    // getters

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }

    @Override
    public V getVide() {
        return vide;
    }

    @Override
    /**
     * Getters du tableau de valeur
     * 
     * @return une copy du tableau.
     */
    public ArrayList<ArrayList<V>> getValeurs() {
        ArrayList<ArrayList<V>> res = new ArrayList<ArrayList<V>>();
        for (int i = 0; i < hauteur; i++) {
            res.add(new ArrayList<V>());
            for (int j = 0; j < largeur; j++) {
                res.get(i).add(valeurs.get(i).get(j));
            }
        }
        return res;
    }

    @Override
    /**
     * Getter d'un cote sous forme d'un tableau contenant les valeur du coté
     * souhaite. En faisant une copie.
     * 
     * @param cote le cote dont on veut obtenir les valeurs
     * @return un tableau avec les valeur du cote
     * @throws directionInvalide Si la valeur de cote est ACTUEL.
     */
    public ArrayList<V> getCote(Direction cote) throws directionInvalide {
        switch (cote) {
            case UP:
                return getCoteUp();
            case DOWN:
                return getCoteDown();
            case LEFT:
                return getCoteLeft();
            case RIGHT:
                return getCoteRight();
            default:
                throw new directionInvalide();
        }
    }

    /**
     * Retourn le cote haut de la piece en faisant une copie
     * 
     * @return la premiere ligne du tableau de valeur
     */
    private ArrayList<V> getCoteUp() {
        ArrayList<V> res = new ArrayList<V>();
        for (V v : valeurs.get(0)) {
            res.add(v);
        }
        return res;
    }

    /**
     * Retourn le cote bas de la piece en faisant une copie
     * 
     * @return la derniere ligne du tableau de valeurs
     */
    private ArrayList<V> getCoteDown() {
        ArrayList<V> res = new ArrayList<V>();
        for (V v : valeurs.get(hauteur - 1)) {
            res.add(v);
        }
        return res;
    }

    /**
     * Retourne le cote gauche de la piece en faisant une copie
     * 
     * @return un tableau contenant chaque premiere valeur de chaque ligne du
     *         tableau valeurs.
     */
    private ArrayList<V> getCoteLeft() {
        ArrayList<V> res = new ArrayList<V>();
        for (ArrayList<V> v : valeurs) {
            res.add(v.get(0));
        }
        return res;
    }

    /**
     * Retourne le cote droit de la piece en faisant une copie
     * 
     * @return un tableau contenant chaque derniere valeur de chaque ligne du
     *         tableau valeurs.
     */
    private ArrayList<V> getCoteRight() {
        ArrayList<V> res = new ArrayList<V>();
        for (ArrayList<V> v : valeurs) {
            res.add(v.get(largeur - 1));
        }
        return res;
    }

    @Override
    /**
     * Compare le coté adjacent de deux pièce.
     * Par exemple si je fait this.comparer (LEFT, piece) je vais comparer le cote
     * LEFT de la piece 'this', avec le cote RIGHT de la piece 'piece'.
     * 
     * @param cote  Le coté que je veux comparer de la pièce this.
     * @param piece Pièce avec la quel je veux comparer le coté.
     * @return Si les deux coté sont bien identique
     * @throws directionInvalide Si la direction est ACTUEL
     */
    public boolean comparer(Direction cote, PieceModel<V> piece) throws directionInvalide {
        ArrayList<V> cote1;
        ArrayList<V> cote2;
        switch (cote) {
            case LEFT:
                // compare le cote LEFT de this avec le cote RIGHT de piece.
                cote1 = this.getCoteLeft();
                cote2 = piece.getCoteRight();
                break;
            case RIGHT:
                // compare le cote RIGHT de this avec le cote LEFT de piece.
                cote1 = this.getCoteRight();
                cote2 = piece.getCoteLeft();
                break;
            case UP:
                // compare le cote UP de this avec le cote DOWN de piece.
                cote1 = this.getCoteUp();
                cote2 = piece.getCoteDown();
                break;
            case DOWN:
                // compare le cote DOWN de this avec le cote UP de piece.
                cote1 = this.getCoteDown();
                cote2 = piece.getCoteUp();
                break;
            default:
                throw new directionInvalide();
        }
        // compare les deux cote selectionnees.
        if (cote1.size() != cote2.size()) {
            return false;
        }
        return (compare(cote1, cote2));
    }

    /**
     * Methode qui compare si deux coté son campatibles
     */
    protected boolean compare(ArrayList<V> cote1, ArrayList<V> cote2) {
        for (int j = 0; j < cote1.size(); j++) {
            if (!cote1.get(j).equals(cote2.get(j))) {
                return false;
            }
        }
        return true;
    }

    @Override
    /**
     * Methode qui tourne une piece à 90 degrès sur la droite.
     * Les valeur qui etait en haut seront à droite.
     * Les valeur qui etait a droite seront en bas.
     * Les valeur qui etait en bas seront à gauche.
     * Les valeur qui etait a gauche seront en haut.
     */
    public void pivotDroite() {
        ArrayList<ArrayList<V>> res = new ArrayList<ArrayList<V>>();
        for (int i = 0; i < hauteur; i++) {
            res.add(new ArrayList<V>());
            for (int j = 0; j < largeur; j++) {
                res.get(i).add(vide);
            }
        }
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                res.get(j).set(largeur - 1 - i, valeurs.get(i).get(j));
            }
        }
        valeurs = res;
    }

    @Override
    /**
     * Methode qui tourne une piece à 90 degrès sur la gauche.
     * Les valeur qui etait en haut seront à gauche.
     * Les valeur qui etait a gauche seront en bas.
     * Les valeur qui etait en bas seront à droite.
     * Les valeur qui etait a droite seront en haut.
     */
    public void pivotGauche() {
        ArrayList<ArrayList<V>> res = new ArrayList<ArrayList<V>>();
        for (int i = 0; i < hauteur; i++) {
            res.add(new ArrayList<V>());
            for (int j = 0; j < largeur; j++) {
                res.get(i).add(vide);
            }
        }
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                res.get(i).set(j, valeurs.get(j).get(largeur - 1 - i));
            }
        }
        valeurs = res;
    }
}
