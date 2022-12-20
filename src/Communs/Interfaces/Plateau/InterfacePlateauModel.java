package Communs.Interfaces.Plateau;

import Communs.Class.Piece.PieceControleur;

/**
 * Interface du Model du Plateau de jeu.
 */
public interface InterfacePlateauModel {
    // getters

    public int getHauteurPiece();

    public int getLargeurPiece();

    /**
     * Methode qui permet de calculer le nombre de point que repporte une piece
     * quand elle vient d'être placer.
     * 
     * @param x la colonne sur laquel la piece a ete placee
     * @param y la ligne sur laquel la piece a ete placee
     * @return le nombre de point que la piece fait gagner
     */
    public int calculePoint(int x, int y);

    /**
     * Verifie si il est possible de placer la piece quelque parts sur la plateau
     * 
     * @param piece Piece a placer
     * @return si il y a un endroit sur le plateau ou l'on peut placer la piece en
     *         respectant les regles.
     */
    public boolean possibleDePlacer(PieceControleur piece);

    /**
     * Regarde si l'on peut placer une piece a de certaine coordonnee
     * 
     * @param piece Piece a placer
     * @param x     colonne a laquel on veut la placer
     * @param y     ligne a laquel on veut la placer
     * @return si c'est possible de placer la piece en respcetant les regles.
     */
    public boolean possibleDePlacer(PieceControleur piece, int x, int y);

}