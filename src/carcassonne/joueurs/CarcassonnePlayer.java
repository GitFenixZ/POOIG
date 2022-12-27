package carcassonne.joueurs;

import java.awt.Color;
import java.util.ArrayList;

import carcassonne.Partissant;
import carcassonne.piece.Terrain;
import carcassonne.piece.CarcassonnePieceControleur;
import communs.objets.Player;
import communs.objets.piece.PieceControleur;

public class CarcassonnePlayer extends Player<PieceControleur<Terrain>> {

    /**
     * Couleur de l'équipe
     */
    private Color couleur;
    /**
     * Partisant pas encore placer sur le plateau de l'équipe
     */
    private ArrayList<Partissant> partissants;

    public CarcassonnePlayer(String name, Color couleur) {
        super(name);
        this.couleur = couleur;
        partissants = new ArrayList<Partissant>();
        for (int i = 0; i < 8; i++) {
            partissants.add(new Partissant(couleur));
        }
    }

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return "Nom : " + getName() + " Score : " + getscore() + "\nMain :\n"
                + ((CarcassonnePieceControleur) getMain());
    }

    /**
     * Prends un partisant de la collection.
     */
    public Partissant peekPartisant() {
        return partissants.remove(0);
    }

    /**
     * Verifie si la liste des partisant contient toujours des pièces.
     */
    public boolean partisantsIsEmpty() {
        return partissants.size() == 0;
    }
}
