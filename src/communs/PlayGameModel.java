package communs;

import java.util.ArrayList;

import javax.swing.JPanel;

import communs.objets.PlayerControleur;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;

/**
 * Class modélisant une partie de domino qui se joue.
 * V est le type des valeur présente sur les pièces du jeu.
 */

public class PlayGameModel<V> {
    /**
     * Sac de piece de la partie
     */
    protected Sac<PieceControleur<V>> sac;
    /**
     * Liste des joueurs autour de la table
     */
    protected ArrayList<PlayerControleur<PieceControleur<V>>> joueurs;
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
     * @param nombreDePiece Nombre de piece presente dans le sac pour la
     *                      partie
     */
    public PlayGameModel(int nombreDePiece) {
        sac = new Sac<>(nombreDePiece);
        joueurs = new ArrayList<>();
        tourSuivant = false;
    }

    public void setTourSuivant(boolean tourSuivant) {
        this.tourSuivant = tourSuivant;
    }

    public Boolean getTourSuivant() {
        return tourSuivant;
    }

    public PlayerControleur<PieceControleur<V>> getactuelPlayer() {
        return joueurs.get(indice);
    }

    /**
     * Met l'indice au joueur suivant
     */
    public void nextPlayer() {
        indice++;
        if (indice >= joueurs.size()) {
            indice = 0;
        }
    }

    /**
     * Met l'indice au joueur précédent.
     */
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
    public void piocherPiece(PlayerControleur<PieceControleur<V>> player) {
        player.piocher(sac);
    }

    public JPanel getImagePlateau() {
        return plateau.getView();
    }
}
