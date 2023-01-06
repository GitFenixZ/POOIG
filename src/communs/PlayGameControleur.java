package communs;

import communs.interfaces.player.Bot;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;

/**
 * Class qui contrôle une partie de domino qui se joue.
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

    public PlayGameModel<V> getModel() {
        return model;
    }

    public PlayGameView<V> getView() {
        view.revalidate();
        return view;
    }

    /**
     * @return je joueur qui est en train de jouer son tour.
     */
    public PlayerControleur getActuelPlayer() {
        return model.getActuelPlayer();
    }

    /**
     * fait jouer le prochain joueur.
     */
    public void nextPlayer() {
        if (!model.finDePartie()) {
            model.nextPlayer();
            PlayerControleur joueurActuel = getActuelPlayer();
            if (joueurActuel instanceof Bot) {
                if (model.jouer((Bot) joueurActuel)) {
                    nextPlayer();
                } else {
                    rejouer();
                }
            } else {
                if (!model.existeEmplacement()) {
                    rejouer();
                } else {
                    view.actualiser();
                }
            }
        } else {
            getActuelPlayer().jeter();
            view.actualiser();
            view.finDePartie();
        }
    }

    /**
     * refait jouer le joueur
     */
    public void rejouer() {
        model.rejouer();
        nextPlayer();
    }

    /**
     * Méthode qui permet de faire piocher un joueur dans le sac
     * 
     * @param player joueur a faire piocher
     */
    public void piocherPiece(PlayerControleur<PieceControleur<V>> player) {
        model.piocherPiece(player);
    }

    /**
     * Ajoute un joueur a la partie.
     * 
     * @param nom pseudo du joueur.
     */
    public void ajoutPerso(String nom) {
        model.ajoutPerso(nom);
    }

    /**
     * @return le nombre de joueur enregistrées dans la partie.
     */
    public int getNombreDeJoueur() {
        return model.getNombreDeJoueur();
    }

    /**
     * tourne la pièce qui est dans la main joueur actuel dans le sens horaire de
     * 90°.
     */
    public void tournerDroite() {
        model.tournerDroite();
        view.actualiser();
    }

    /**
     * tourne la pièce qui est dans la main joueur actuel dans le sens
     * horaire de 90°.
     */
    public void tournerGauche() {
        model.tournerGauche();
        view.actualiser();
    }

    /**
     * fait déplacer le joueur sur le plateau dans la direction indiquée.
     */
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

    /**
     * Place la pièce du joueur actuel sur le plateau.
     */
    public void placerPiece() {
        model.placerPiece();
        view.actualiser();
        view.revalidate();
    }

    /**
     * Méthode a appliquer après que l'on souhaite savoir ou le partisan sera placé
     * pour Carcassonne.
     * Permet de continuer la partie, après qu'un partisan ai été placé.
     */
    public void postPartisan() {
        view.activerBoutonPlacer();
        nextPlayer();
        view.actualiser();
        allerADroite();// actualise l'affichage de la pièce
        allerAGauche();// rudimentaire ... ne pas supprimer
    }

    /**
     * Lance une partie complete. Du début jusqu'a ce qu'il n'y ai plus de pièce
     * dans le sac.
     * 
     * Avec interface Graphique
     */
    public void play() {
        view.revalidate();
        model.play();
    }
}
