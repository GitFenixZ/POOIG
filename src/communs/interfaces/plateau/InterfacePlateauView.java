package communs.interfaces.plateau;

import java.util.Scanner;

import communs.objets.Direction;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauModel;
import communs.objets.player.PlayerControleur;

/**
 * Interface de la vue du Plateau de jeu.
 * 
 * V est le types des valeur qui apparaissent sur les pièces qui
 * pourront être placé sur le plateau.
 * Exemple : Integer dans le domino.
 */
public interface InterfacePlateauView<V> {
    // setter
    public void setModel(PlateauModel<V> model);

    public void setPiece();

    /**
     * Raffraichi l'affichage graphique.
     */
    public void actualiser();

    /**
     * Crée un String qui représente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public void afficher();

    /**
     * Méthode qui permet de mettre un message d'erreur en fonction du deplacement
     * effectué.
     * 
     * @param deplacement deplacement qui est realise
     */
    public void deplacement(Direction deplacement);

    /**
     * Affiche le plateau et la main d'un joueur
     * 
     * @param player joueur dont il faut afficher la main
     */
    public void affichePlateauEtJoueur(PlayerControleur<PieceControleur<V>> player);

    /**
     * Méthode qui demande au joueur si il pense pouvoir jouer.
     * Si il ne peut pas alors il repioche, si il peut, la Méthode l'invite a
     * chercher.
     * 
     * @param sc     scanner qui attends les reponses au questions
     * @param player joueur qui est en train de jouer
     * @return si le joueur peut jouer ou non
     */
    public boolean pensezVousPouvoirJouer(Scanner sc, PlayerControleur<PieceControleur<V>> player);
}
