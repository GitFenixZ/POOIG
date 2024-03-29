package communs.interfaces.plateau;

import java.util.Scanner;

import communs.objets.Point;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;

/**
 * Interface du controleur du Plateau de jeu.
 * 
 * V est le types des valeur qui apparaissent sur les pièces qui
 * pourront être placé sur le plateau.
 * Exemple : Integer dans le domino.
 */
public interface InterfacePlateauControleur<V> {
    /**
     * Place la pièce qu'a le player dans sa main sur le plateau.
     * 
     * @param player Joueur qui place sa piece
     * @param sc     System.in permettra de lire la reponse de l'utilisateur et de
     *               savoir si le joueur veux placer sa piece.
     */
    public void placerPiece(PlayerControleur<PieceControleur<V>> player, Scanner sc);

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
     * Crée un String qui représente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public void afficher();

    /**
     * Méthode qui permet d'initialiser le plateau avec un pièce en son centre.
     * 
     * @param sac sac du quel est tiré la pièce.
     */
    public void start(Sac<PieceControleur<V>> sac);

    /**
     * Donnes un coordonnée ou l'on peut placer la piece.
     * 
     * @param piece Piece a placer
     * @return des coordonnée ou l'on peut placer la pièce
     */
    public Point getEmplacementPossible(PieceControleur<V> piece);

    /**
     * Permet a un joueur de placer une pièce ou il le veut.
     * 
     * @param player joueur qui vas jouer
     * @param point  position de l'objet
     */
    public void setPiece(PlayerControleur<PieceControleur<V>> player, Point p);
}
