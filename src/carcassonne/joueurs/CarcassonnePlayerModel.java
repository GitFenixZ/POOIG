package carcassonne.joueurs;

import java.awt.Color;
import java.util.ArrayList;

import carcassonne.Partisan;
import carcassonne.piece.Terrain;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerModel;

/**
 * Class modélisant un joueur de carcassonne
 */
public class CarcassonnePlayerModel extends PlayerModel<PieceControleur<Terrain>> {

    /**
     * Couleur de l'équipe
     */
    private Color couleur;
    /**
     * Partisant pas encore placer sur le plateau de l'équipe
     */
    private ArrayList<Partisan> partissants;

    /**
     * Contructeur du model du player
     * 
     * @param name    pseudo du joueur
     * @param couleur couleur des meaples.
     */
    public CarcassonnePlayerModel(String name, Color couleur) {
        super(name);
        this.couleur = couleur;
        partissants = new ArrayList<Partisan>();
        for (int i = 0; i < 8; i++) {
            partissants.add(new Partisan(couleur));
        }
    }

    /**
     * Prends un partisant de la collection.
     * le retire du lot.
     */
    public Partisan peekPartisant() {
        return partissants.remove(0);
    }

    /**
     * Verifie si la liste des partisant est vide (le joueur n'a plus de partisant).
     */
    public boolean partisantsIsEmpty() {
        return partissants.size() == 0;
    }

    /**
     * Renvoie le nombre de partisant qui sont encore dans la main du joueur.
     */
    public int getNombreDePartisant() {
        return partissants.size();
    }
}
