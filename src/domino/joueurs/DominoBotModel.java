package domino.joueurs;

import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;
import communs.objets.player.PlayerModel;

public class DominoBotModel extends PlayerModel<PieceControleur<Integer>> {
    DominoBotModel(String nom) {
        super(nom);
    }

    /**
     * Methode a utiliser pour jouer sans le terminal.
     * n'affiche aucune informations.
     * Permet de faire jouer un joueur automatiquement.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    public boolean jouer(PlateauControleur<Integer> plateauControleur, DominoBotControleur controleurBotDomino) {
        Point position = plateauControleur.peutPlacer(getMain());
        if (position != null) {
            plateauControleur.setPiece(controleurBotDomino, position);
            return true;
        } else {
            return false;
        }
    }
}
