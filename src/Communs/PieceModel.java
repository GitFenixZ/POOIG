package Communs;

/**
 * Class modélisant une piece de jeu, c'est à dire les vauleurs qu'elle prends.
 * Ainsi que ses dimensions.
 */

public class PieceModel extends Carre {
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

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

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

    /**
     * Getter d'un cote sous forme d'un tableau contenant les valeur du coté
     * souhaite.
     * 
     * @param cote le cote dont on veut obtenir les valeurs
     * @return un tableau avec les valeur du cote
     */
    public int[] getCote(Direction cote) {
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
                return new int[0];
        }
    }

    /**
     * Retourn le cote haut de la piece
     * 
     * @return la premiere ligne du tableau de valeur
     */
    private int[] getCoteUp() {
        return this.valeurs[0];
    }

    /**
     * Retourn le cote bas de la piece
     * 
     * @return la derniere ligne du tableau de valeurs
     */
    private int[] getCoteDown() {
        return this.valeurs[this.valeurs.length - 1];
    }

    /**
     * Retourne le cote gauche de la piece
     * 
     * @return un tableau contenant chaque premiere valeur de chaque ligne du
     *         tableau valeurs.
     */
    private int[] getCoteLeft() {
        int[] res = new int[hauteur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (j == 0) {
                    res[i] = valeurs[i][j];
                }
            }
        }
        return res;
    }

    /**
     * Retourne le cote droit de la piece
     * 
     * @return un tableau contenant chaque derniere valeur de chaque ligne du
     *         tableau valeurs.
     */
    private int[] getCoteRight() {
        int[] res = new int[hauteur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (j == largeur - 1) {
                    res[i] = valeurs[i][j];
                }
            }
        }
        return res;
    }

    /**
     * Compare le coté adjacent de deux pièce.
     * Par exemple si je fait this.comparer (LEFT, piece) je vais comparer le cote
     * LEFT de la piece 'this', avec le cote RIGHT de la piece 'piece'.
     * 
     * @param cote  Le coté que je veux comparer de la pièce this.
     * @param piece Pièce avec la quel je veux comparer le coté.
     * @return Si les deux coté sont bien identique
     */
    public boolean comparer(Direction cote, PieceModel piece) {
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
            case ACTUEL:
                // compare l'integralite du contenue des 2 pieces (les deux piece sont
                // identique)
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (getValeurs()[i][j] != piece.getValeurs()[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
        }
        // compare les deux cote selectionnees.
        for (int j = 0; j < cote1.length; j++) {
            if (cote1[j] != cote2[j]) {
                return false;
            }
        }
        return true;
    }

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

    /**
     * Somme toutes la valeur d'un coté de la piece
     * 
     * @param cote cote que l'on veut sommer
     * @return La somme de toutes les valeur d'un cote
     */
    public int somme(Direction cote) {
        int res = 0;
        for (int valeur : this.getCote(cote)) {
            res += valeur;
        }
        return res;
    }
}
