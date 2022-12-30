package communs;

import communs.objets.PlayerControleur;
import communs.objets.piece.PieceControleur;

/**
 * Class qui controle une partie de domino qui se joue.
 * V est le type des valeur présente sur les pièces du jeu.
 */

public class PlayGameControleur<V> {
    protected PlayGameModel<V> model;
    protected PlayGameView<V> view;

    /**
     * Constructeur
     */
    public PlayGameControleur() {
    }

    public PlayerControleur<PieceControleur<V>> getactuelPlayer() {
        return model.getactuelPlayer();
    }

    public void setTourSuivant(boolean tourSuivant) {
        model.setTourSuivant(tourSuivant);
    }

    public boolean getTourSuivant() {
        return model.getTourSuivant();
    }

    public PlayGameModel<V> getModel() {
        return model;
    }

    public PlayGameView<V> getView() {
        return view;
    }

    /**
     * Met l'indice au joueur suivant
     */
    public void nextPlayer() {
        model.nextPlayer();
    }

    /**
     * Met l'indice au joueur précédent.
     */
    public void rejouer() {
        model.rejouer();
    }

    /**
     * Methode qui permet de faire piocher un joueur dans le sac
     * 
     * @param player joueur a faire piocher
     */
    public void piocherPiece(PlayerControleur<PieceControleur<V>> player) {
        model.piocherPiece(player);
    }
}
