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
     * Méthode qui permet de calculer le nombre de point que repporte une piece
     * quand elle vient d'être placer.
     * 
     * @param point position sur laquelle la piece a ete placee
     * @return le nombre de point que la piece fait gagner
     */
    public int calculePoint(Point point);

    /**
     * Vérifie s'il existe un emplacement sur le plateau où
     * on peut poser la pièce
     * tout en respectant les règles
     * 
     * @param piece La piece que l'on veut placer
     * @return true s'il existe un emplacement;
     *         false sinon
     */
    public boolean existeEmplacement(PieceControleur<V> piece);

    /**
     * Regarde si l'on peut placer une piece a de certaine coordonnées
     * 
     * @param piece Piece a placer
     * @param point position sur laquelle on veut essayer de placer la piece
     * @return si c'est possible de placer la piece en respcetant les regles.
     */
    public boolean possibleDePlacer(PieceControleur<V> piece, Point point);

    /**
     * Donnes un coordonnée ou l'on peut placer la piece.
     * 
     * @param piece Piece a placer
     * @return des coordonnée ou l'on peut placer la pièce
     */
    public Point getEmplacementPossible(PieceControleur<V> piece);

    /**
     * Méthode qui permet d'initialiser le plateau avec un pièce en son centre.
     * 
     * @param sac sac du quel est tiré la pièce.
     */
    public void start(Sac<PieceControleur<V>> sac);

    /**
     * Méthode qui permet de déplacer le centre du plateau (la position ou l'on est
     * en se moment)
     * 
     * @param deplacement la direction dans laquelle on veut aller.
     * @throws positionInvalide  si l'on sort du tableau
     * @throws directionInvalide Actuel
     */
    public void deplacement(Direction deplacement) throws positionInvalide, directionInvalide;

    /**
     * Méthode qui permet de tourner une piece.
     * 
     * @param sens   sens dans le quel on veur tourner la piece.
     * @param player joueur qui fait tourner sa piece.
     * @throws directionInvalide si la direction n'est ni LEFT ni RIGHT.
     */
    public void rotation(Direction sens, PlayerControleur<PieceControleur<V>> player) throws directionInvalide;
}
