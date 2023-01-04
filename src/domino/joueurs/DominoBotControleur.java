package domino.joueurs;

import communs.interfaces.player.Bot;
import communs.objets.piece.PieceControleur;
import communs.objets.plateau.PlateauControleur;
import communs.objets.player.PlayerControleur;
import communs.objets.player.PlayerView;

/**
 * Class controlant un Robot capable de jouer au domino de manière très
 * standart.
 */
public class DominoBotControleur extends PlayerControleur<PieceControleur<Integer>> implements Bot<Integer> {
    public DominoBotControleur(String name) {
        super();
        model = new DominoBotModel(name);
        view = new PlayerView<PieceControleur<Integer>>(model);
    }

    /**
     * Methode a utiliser pour jouer dans le terminal.
     * Affiche les informations importante.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    public boolean jouerTerminal(PlateauControleur<Integer> plateauControleur) {
        return ((DominoBotModel) model).jouerTerminal(plateauControleur, this);
    }

    /**
     * Methode a utiliser pour jouer sans le terminal.
     * n'affiche aucune informations.
     * Permet de faire jouer un joueur automatiquement.
     * 
     * @param plateauControleur Plateau sur lequel on joue en se moment.
     * @return si le robot a pu jouer
     */
    @Override
    public boolean jouer(PlateauControleur<Integer> plateauControleur) {
        return ((DominoBotModel) model).jouer(plateauControleur, this);
    }
}