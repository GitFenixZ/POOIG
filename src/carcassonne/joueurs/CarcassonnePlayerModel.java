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
     * partisan pas encore placer sur le plateau de l'équipe
     */
    private ArrayList<Partisan> partisans;

    /**
     * Constructeur du model du player
     * 
     * @param name    pseudo du joueur
     * @param couleur couleur des partisans.
     */
    public CarcassonnePlayerModel(String name, Color couleur) {
        super(name);
        this.couleur = couleur;
        partisans = new ArrayList<Partisan>();
        for (int i = 0; i < 8; i++) {
            partisans.add(new Partisan(couleur));
        }
    }

    /**
     * Prends un partisan de la collection.
     * le retire du lot.
     */
    public Partisan peekPartisan() {
        return partisans.remove(0);
    }

    /**
     * Vérifie si la liste des partisan est vide (le joueur n'a plus de partisan).
     */
    public boolean partisansIsEmpty() {
        return partisans.size() == 0;
    }

    /**
     * Renvoie le nombre de partisan qui sont encore dans la main du joueur.
     */
    public int getNombreDePartisan() {
        return partisans.size();
    }
}
