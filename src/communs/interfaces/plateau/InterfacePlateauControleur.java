package communs.interfaces.plateau;

import java.util.Scanner;

import communs.objets.Bot;
import communs.objets.Player;
import communs.objets.Point;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;

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
    public void placerPiece(Player<PieceControleur<V>> player, Scanner sc);

    /**
     * Verifie si on peut placer une piece a l'emplacement actuel
     * 
     * @param piece piece que l'on veut placer
     * @return si on peut placer la piece ici.
     */
    public boolean possibleDePlacer(PieceControleur<V> piece);

    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public String afficher();

    /**
     * Methode qui permet d'initialiser le plateau avec un pièce en son centre.
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
    public Point peutPlacer(PieceControleur<V> piece);

    /**
     * Permet a un bot de placer une pièce ou il le veut.
     * 
     * @param bot   bot qui vas jouer
     * @param point position de l'objet
     */
    public void setPiece(Bot<V> bot, Point p);
}
