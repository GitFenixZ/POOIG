package Communs.Class.Piece;

import Communs.Exceptions.directionInvalide;
import Communs.Interfaces.Carre;
import Communs.Interfaces.Piece.InterfacePieceModel;

/**
 * Class modélisant une piece de jeu, c'est à dire les vauleurs qu'elle prends.
 * Ainsi que ses dimensions.
 */

public class PieceModel implements Carre, InterfacePieceModel {
    /** Valeurs contenue dans la piece */
    protected int[][] valeurs;
    /** Hauteur de la piece */
    protected int hauteur;
    /** Largeur de la piece */
    protected int largeur;

    /**
     * Constructeur
     * 
     * @param hauteur La hauteur de la pièce
     * @param largeur La largeur de la pièce
     */
    public PieceModel(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        valeurs = new int[hauteur][largeur];
        // initialise le tableau par des -1. Par conséquent -1 signifie que la case n'a
        // pas de valeur spécifié.
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                valeurs[i][j] = -1;
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
    /**
     * Getters du tableau de valeur
     * 
     * @return une copy du tableau.
     */
    public int[][] getValeurs() {
        int[][] res = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                res[i][j] = valeurs[i][j];
            }
        }
        return res;
    }

    @Override
    /**
     * Getter d'une ligne du tableau sous form de String
     * 
     * @param indice indice de la ligne voulu
     * @return un String representant une ligne du tableau
     */
    public String getligne(int indice) {
        String res = " ";
        for (int e : valeurs[indice]) {
            if (e != -1) {
                res += e + " ";
            } else {
                res += "  ";
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
    public int[] getCote(Direction cote) throws directionInvalide {
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
    private int[] getCoteUp() {
        int[] res = new int[largeur];
        for (int i = 0; i < hauteur; i++) {
            res[i] = valeurs[0][i];
        }
        return res;
    }

    /**
     * Retourn le cote bas de la piece en faisant une copie
     * 
     * @return la derniere ligne du tableau de valeurs
     */
    private int[] getCoteDown() {
        int[] res = new int[largeur];
        for (int i = 0; i < hauteur; i++) {
            res[i] = valeurs[this.valeurs.length - 1][i];
        }
        return res;
    }

    /**
     * Retourne le cote gauche de la piece en faisant une copie
     * 
     * @return un tableau contenant chaque premiere valeur de chaque ligne du
     *         tableau valeurs.
     */
    private int[] getCoteLeft() {
        int[] res = new int[hauteur];
        for (int i = 0; i < hauteur; i++) {
            res[i] = valeurs[i][0];
        }
        return res;
    }

    /**
     * Retourne le cote droit de la piece en faisant une copie
     * 
     * @return un tableau contenant chaque derniere valeur de chaque ligne du
     *         tableau valeurs.
     */
    private int[] getCoteRight() {
        int[] res = new int[hauteur];
        for (int i = 0; i < hauteur; i++) {
            res[i] = valeurs[i][largeur - 1];
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
    public boolean comparer(Direction cote, PieceModel piece) throws directionInvalide {
        int[] cote1 = {};
        int[] cote2 = {};
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
        for (int j = 0; j < cote1.length; j++) {
            if (cote1[j] != cote2[j]) {
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
        int[][] res = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                res[j][largeur - 1 - i] = valeurs[i][j];
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
        int[][] res = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                res[i][j] = valeurs[j][largeur - 1 - i];
            }
        }
        valeurs = res;
    }

    @Override
    /**
     * Somme toutes la valeur d'un coté de la piece
     * 
     * @param cote cote que l'on veut sommer
     * @return La somme de toutes les valeur d'un cote
     */
    public int somme(Direction cote) {
        try {
            int res = 0;
            for (int valeur : this.getCote(cote)) {
                if (valeur != -1) {
                    res += valeur;
                }
            }
            return res;
        } catch (directionInvalide e) {
            return 0;
        }
    }
}
