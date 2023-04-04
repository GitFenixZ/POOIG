package domino.joueurs;

import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;
import communs.objets.player.PlayerModel;

/**
 * Modélise un robot qui peut jouer a domino.
 */
public class DominoBotModel extends PlayerModel<PieceControleur<Integer>> {
    DominoBotModel(String nom) {
        super(nom);
    }

    /**
     * Méthode a utiliser pour jouer dans le terminal.
     * Affiche les informations importante.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    public boolean jouerTerminal(PlateauControleur<Integer> plateauControleur,
            DominoBotControleur controleurBotDomino) {
        Point position = plateauControleur.getEmplacementPossible(getMain());
        if (position != null) {
            plateauControleur.setPiece(controleurBotDomino, position);
            System.out.println("Le bot " + getName() + " a joué.");
            return true;
        } else {
            System.out.println("Le bot " + getName() + " n'a pas pu jouer.");
            return false;
        }
    }

    /**
     * Méthode a utiliser pour jouer sans le terminal.
     * n'affiche aucune informations.
     * Permet de faire jouer un joueur automatiquement.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    public boolean jouer(PlateauControleur<Integer> plateauControleur, DominoBotControleur controleurBotDomino) {
        Point position = plateauControleur.getEmplacementPossible(getMain());
        if (position != null) {
            plateauControleur.setPiece(controleurBotDomino, position);
            return true;
        } else {
            return false;
        }
    }
}
