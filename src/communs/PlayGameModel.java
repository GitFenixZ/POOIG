package communs;

import java.util.ArrayList;

import javax.swing.JPanel;

import carcassonne.joueurs.CarcassonneBot;
import carcassonne.piece.Terrain;
import communs.objets.PlayerControleur;
import communs.objets.Point;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;
import domino.joueurs.DominoBot;

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
     * Met l'indice au joueur suivant
     */
    public void nextPlayer() {
        getactuelPlayer().jeter();
        indice++;
        if (indice >= joueurs.size()) {
            indice = 0;
        }
        getactuelPlayer().piocher(sac);

    }

    public boolean jouer(PlayerControleur joueurActuel) {
        boolean aReussiAJouer = false;
        if (joueurActuel instanceof CarcassonneBot) {
            aReussiAJouer = ((CarcassonneBot) joueurActuel).jouer((PlateauControleur<Terrain>) plateau);
        }
        if (joueurActuel instanceof DominoBot) {
            aReussiAJouer = ((DominoBot) joueurActuel).jouer((PlateauControleur<Integer>) plateau);
        }
        return aReussiAJouer;
    }

    /**
     * le joueur jete sa main.
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

    public void ajoutPerso(String nom) {
        joueurs.add(new PlayerControleur<PieceControleur<V>>(nom));
    }

    public int getNombreDeJoueur() {
        return joueurs.size();
    }

    public JPanel getImagePlateau() {
        return plateau.getView();
    }

    public void pivotDroite() {
        getactuelPlayer().getMain().pivotDroite();
    }

    public void pivotGauche() {
        getactuelPlayer().getMain().pivotGauche();
    }

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

    public void placerPiece() {
        plateau.setPiece(getactuelPlayer(), plateau.getActuelPosition());
    }

    public Point getActuelPosition() {
        return plateau.getActuelPosition();
    }

    /**
     * Regarde si l'on peut placer la piece du joueur actuel, a la position actuel
     */
    public boolean possibleDePlacer() {
        return plateau.possibleDePlacer(getactuelPlayer());
    }

    public boolean finDePartie() {
        return sac.isEmpty();
    }

    public boolean peutplacer() {
        return plateau.peutPlacer(getactuelPlayer().getMain()) != null;
    }
}
