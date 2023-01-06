package communs;

import java.util.ArrayList;

import javax.swing.JPanel;

import communs.interfaces.player.Bot;
import communs.objets.Point;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;
import communs.objets.player.PlayerControleur;

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
    }

    public ArrayList<PlayerControleur<PieceControleur<V>>> getJoueurs() {
        return joueurs;
    }

    public PlayerControleur<PieceControleur<V>> getActuelPlayer() {
        return joueurs.get(indice);
    }

    /**
     * Met l'indice au joueur suivant, et le fait piocher permet au joueur suivant
     * de jouer.
     */
    public void nextPlayer() {
        getActuelPlayer().jeter();
        indice++;
        if (indice >= joueurs.size()) {
            indice = 0;
        }
        getActuelPlayer().piocher(sac);

    }

    /**
     * fait jouer un bot
     * 
     * @param joueurActuel bot a faire jouer.
     * @return si le bot a reussi a jouer.
     */
    public boolean jouer(Bot<V> joueurActuel) {
        return joueurActuel.jouer(plateau);
    }

    /**
     * fait rejouer un joueur
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

    /**
     * Permet d'ajouter un joueur a liste des joueurs.
     */
    public void ajoutPerso(String nom) {
        joueurs.add(new PlayerControleur<PieceControleur<V>>(nom));
    }

    /**
     * Permet de savoir combiens il y a de personne dans les joueurs.
     */
    public int getNombreDeJoueur() {
        return joueurs.size();
    }

    /**
     * Permet de recuperer la view du plateau.
     */
    public JPanel getImagePlateau() {
        return plateau.getView();
    }

    /**
     * permet de faire tourner la piece du joueur actuel dans le sens demande.
     */
    public void tournerDroite() {
        getActuelPlayer().getMain().tournerDroite();
    }

    public void tournerGauche() {
        getActuelPlayer().getMain().tournerGauche();
    }

    /**
     * permet de deplacer la vue du plateau dans le sens demandé.
     */
    public void allerADroite() {
        plateau.allerADroite();
    }

    public void allerAGauche() {
        plateau.allerAGauche();
    }

    public void allerEnBas() {
        plateau.allerEnBas();
    }

    public void allerEnHaut() {
        plateau.allerEnHaut();
    }

    /**
     * Permet de placer la piece du joueur qui est en train de jouer a la position
     * actuel du plateau.
     */
    public void placerPiece() {
        plateau.setPiece(getActuelPlayer(), plateau.getActuelPosition());
    }

    /**
     * Permet d'obtenir la position sur laquel est centrée la vue du plateau.
     * 
     * @return
     */
    public Point getActuelPosition() {
        return plateau.getActuelPosition();
    }

    /**
     * Regarde si l'on peut placer la piece du joueur actuel, a la position actuel
     */
    public boolean possibleDePlacer() {
        return plateau.possibleDePlacer(getActuelPlayer());
    }

    /**
     * Regarde si c'est la fin de la partie.
     * Quand le sac est vide.
     */
    public boolean finDePartie() {
        return sac.isEmpty();
    }

    /**
     * Vérifie s'il existe un emplacement sur le plateau où
     * on peut poser la pièce du joueur actuel
     * tout en respectant les règles
     * 
     * @return true s'il existe un emplacement;
     *         false sinon
     */
    public boolean existeEmplacement() {
        return plateau.existeEmplacement(getActuelPlayer().getMain());
    }
}
