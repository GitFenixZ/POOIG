package carcassonne.joueurs;

import java.awt.Color;
import java.util.ArrayList;

import carcassonne.Partisan;
import carcassonne.piece.Terrain;
import communs.objets.PlayerModel;
import communs.objets.piece.PieceControleur;

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
     */
    public Partisan peekPartisant() {
        return partissants.remove(0);
    }

    /**
     * Verifie si la liste des partisant contient toujours des pièces.
     */
    public boolean partisantsIsEmpty() {
        return partissants.size() == 0;
    }

    /**
     * Renvoie le nombre de partisant qui n'a pas été placé par le joueur.
     */
    public int getNombreDePartisant() {
        return partissants.size();
    }
}
