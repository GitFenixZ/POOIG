package communs.interfaces.plateau;

import communs.exceptions.directionInvalide;
import communs.exceptions.positionInvalide;
import communs.objets.Direction;
import communs.objets.Point;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;

/**
 * Interface du Model du Plateau de jeu.
 * 
 * V est le types des valeur qui apparaissent sur les pièces qui
 * pourront être placé sur le plateau.
 * Exemple : Integer dans le domino.
 */
public interface InterfacePlateauModel<V> {
    // getters

    public int getHauteurPiece();

    public int getLargeurPiece();

    /**
     * Methode qui permet de calculer le nombre de point que repporte une piece
     * quand elle vient d'être placer.
     * 
     * @param point position sur laquel la piece a ete placee
     * @return le nombre de point que la piece fait gagner
     */
    public int calculePoint(Point point);

    /**
     * TODO: rename existeEmplacement
     * Verifie si il est possible de placer la piece quelque parts sur la plateau
     * 
     * @param piece Piece a placer
     * @return si il y a un endroit sur le plateau ou l'on peut placer la piece en
     *         respectant les regles.
     */
    public boolean possibleDePlacer(PieceControleur<V> piece);

    /**
     * Regarde si l'on peut placer une piece a de certaine coordonnee
     * 
     * @param piece Piece a placer
     * @param point position sur laquel on veut essayer de placer la piece
     * @return si c'est possible de placer la piece en respcetant les regles.
     */
    public boolean possibleDePlacer(PieceControleur<V> piece, Point point);

    /**
     * Donnes un coordonnée ou l'on peut placer la piece.
     * 
     * @param piece Piece a placer
     * @return des coordonnée ou l'on peut placer la pièce
     */
    public Point peutPlacer(PieceControleur<V> piece);

    /**
     * Methode qui permet d'initialiser le plateau avec un pièce en son centre.
     * 
     * @param sac sac du quel est tiré la pièce.
     */
    public void start(Sac<PieceControleur<V>> sac);

    /**
     * Methode qui permet de deplacer le centre du plateau (la position ou l'on est
     * en se moment)
     * 
     * @param deplacement la direction dans laquel on veut aller.
     * @throws positionInvalide  si l'on sort du tableau
     * @throws directionInvalide Actuel
     */
    public void deplacement(Direction deplacement) throws positionInvalide, directionInvalide;

    /**
     * Methode qui permet de rotater une piece.
     * 
     * @param sens   sens dans le quel on veur tourner la piece.
     * @param player joueur qui fait tourner sa piece.
     * @throws directionInvalide si la direction n'est ni LEFT ni RIGHT.
     */
    public void rotation(Direction sens, PlayerControleur<PieceControleur<V>> player) throws directionInvalide;
}
