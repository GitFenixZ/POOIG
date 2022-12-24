package communs;

import java.util.ArrayList;

import communs.objets.Player;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;

/**
 * Class modélisant une partie de domino qui se joue.
 * V est le type des valeur présente sur les pièces du jeu.
 */

public class PlayGame<V> {
    /**
     * Sac de piece de la partie
     */
    protected Sac<PieceControleur<V>> sac;
    /**
     * Liste des joueurs autour de la table
     */
    protected ArrayList<Player<PieceControleur<V>>> joueurs;
    /**
     * indice du jouer en train de jouer dans la liste des joueurs
     */
    private int indice;
    /**
     * Préviens si c'st la fin du tour d'un joueur
     */
    private boolean tourSuivant;
    /**
     * Plateau de jeu pour la partie en cours
     */
    protected PlateauControleur<V> plateau;

    /**
     * Constructeur
     * 
     * @param nombreDePiece  Nombre de piece presente dans le sac pour la partie
     * @param nombreJoueur   Nombre de joueur qui vont jouer
     * @param hauteurPlateau Hauteur du plateau de jeu
     * @param largeurPlateau Largeur du plateau de jeu
     */
    public PlayGame(int nombreDePiece, int hauteurPlateau, int largeurPlateau) {
        sac = new Sac<>(nombreDePiece);
        joueurs = new ArrayList<>();
        // initialise le plateau
        plateau = new PlateauControleur<V>(hauteurPlateau, largeurPlateau, 5, 5);
        tourSuivant = false;
    }

    public PlateauControleur<V> getPlateau() {
        return plateau;
    }

    public int getIndice() {
        return indice;
    }

    public boolean getTourSuivant() {
        return tourSuivant;
    }

    public void setTourSuivant(boolean tourSuivant) {
        this.tourSuivant = tourSuivant;
    }

    public void nextPlayer() {
        indice++;
        if (indice >= joueurs.size()) {
            indice = 0;
        }
    }

    public void rejouer() {
        indice--;
        if (indice < 0) {
            indice = joueurs.size() - 1;
        }
    }

    /**
     * Methode qui permet de faire piocher un joueur dans le sac
     * 
     * @param player joueur a faire piocher
     */
    public void piocherPiece(Player<PieceControleur<V>> player) {
        player.piocher(sac);
    }
}
