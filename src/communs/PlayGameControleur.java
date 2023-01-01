package communs;

import carcassonne.joueurs.CarcassonneBot;
import communs.objets.PlayerControleur;
import communs.objets.piece.PieceControleur;
import domino.joueurs.DominoBot;

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

    public PlayGameModel<V> getModel() {
        return model;
    }

    public PlayGameView<V> getView() {
        view.revalidate();
        return view;
    }

    /**
     * Met l'indice au joueur suivant
     */
    public void nextPlayer() {
        if (!model.finDePartie()) {
            model.nextPlayer();
            PlayerControleur joueurActuel = getactuelPlayer();
            view.actualiser();
            if (joueurActuel instanceof CarcassonneBot || joueurActuel instanceof DominoBot) {
                if (model.jouer(joueurActuel)) {
                    nextPlayer();
                } else {
                    rejouer();
                }
            }
            view.actualiser();
        } else {
            getactuelPlayer().jeter();
            view.actualiser();
            view.finDePartie();
        }
    }

    /**
     * Met l'indice au joueur précédent.
     */
    public void rejouer() {
        model.rejouer();
        nextPlayer();
    }

    /**
     * Methode qui permet de faire piocher un joueur dans le sac
     * 
     * @param player joueur a faire piocher
     */
    public void piocherPiece(PlayerControleur<PieceControleur<V>> player) {
        model.piocherPiece(player);
    }

    public void ajoutPerso(String nom) {
        model.ajoutPerso(nom);
    }

    public int getNombreDeJoueur() {
        return model.getNombreDeJoueur();
    }

    public void pivotDroite() {
        model.pivotDroite();
        view.actualiser();
    }

    public void pivotGauche() {
        model.pivotGauche();
        view.actualiser();
    }

    public void allerADroite() {
        model.allerADroite();
        view.actualiser();
    }

    public void allerAGauche() {
        model.allerAGauche();
        view.actualiser();
    }

    public void allerEnBas() {
        model.allerEnBas();
        view.actualiser();
    }

    public void allerEnHaut() {
        model.allerEnHaut();
        view.actualiser();
    }

    public void placerPiece() {
        model.placerPiece();
        view.actualiser();
        view.revalidate();
    }
}
