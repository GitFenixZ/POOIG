package Communs;

import java.util.Random;

/**
 * Class modélisant une piece de jeu, c'est à dire les vauleurs qu'elle prends.
 * Ainsi que ses dimensions.
 */

public class PieceModel extends Carre {
    private int[][] valeurs;
    private int hauteur;
    private int largeur;

    public PieceModel(int hauteur, int largeur) {
        this.hauteur = hauteur + 2;
        this.largeur = largeur + 2;
        valeurs = new int[hauteur + 2][largeur + 2];
        for (int i = 0; i < hauteur + 2; i++) {
            for (int j = 0; j < largeur + 2; j++) {
                if (((i == 0 || i == hauteur + 1) && j != 0 && j != largeur + 1)
                        || ((j == 0 || j == largeur + 1)) && i != 0 && i != hauteur + 1) {
                    valeurs[i][j] = new Random().nextInt(2);
                } else {
                    valeurs[i][j] = -1;
                }
            }
        }
    }

    public int[][] getValeurs() {
        int[][] res = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                res[i][j] = valeurs[i][j];
            }
        }
        return res;
    }

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

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

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

    private int[] getCoteUp() {
        return this.valeurs[0];
    }

    private int[] getCoteDown() {
        return this.valeurs[this.valeurs.length - 1];
    }

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

    public boolean comparer(Direction cote, PieceModel piece) {
        int[] cote1 = {};
        int[] cote2 = {};
        switch (cote) {
            case LEFT:
                cote1 = this.getCoteLeft();
                cote2 = piece.getCoteRight();
                break;
            case RIGHT:
                cote1 = this.getCoteRight();
                cote2 = piece.getCoteLeft();
                break;
            case UP:
                cote1 = this.getCoteUp();
                cote2 = piece.getCoteDown();
                break;
            case DOWN:
                cote1 = this.getCoteDown();
                cote2 = piece.getCoteUp();
                break;
            case ACTUEL:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (getValeurs()[i][j] != piece.getValeurs()[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
        }
        for (int j = 0; j < cote1.length; j++) {
            if (cote1[j] != cote2[j]) {
                return false;
            }
        }
        return true;
    }

    public void pivotDroite() {
        int[][] res = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                res[j][largeur - 1 - i] = valeurs[i][j];
            }
        }
        valeurs = res;
    }

    public void pivotGauche() {
        int[][] res = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                res[i][j] = valeurs[j][largeur - 1 - i];
            }
        }
        valeurs = res;
    }

    public int somme(Direction cote) {
        int res = 0;
        for (int valeur : this.getCote(cote)) {
            res += valeur;
        }
        return res;
    }
}
