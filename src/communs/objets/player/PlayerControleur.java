package communs.objets.player;

import communs.interfaces.player.InterfacePlayerControleur;
import communs.objets.Sac;

/**
 * Class contrôlant un joueur
 * P est le type des pièces que peut avoir le joueur
 */
public class PlayerControleur<P> implements InterfacePlayerControleur<P> {
    protected PlayerModel<P> model;
    protected PlayerView<P> view;

    /**
     * Retourne un String décrivant le joueur actuel
     */
    @Override
    public String toString() {
        return view.toString();
    }

    public PlayerControleur() {
    }

    /**
     * Constructeur
     * 
     * @param name initialise le nom du joueur
     */
    public PlayerControleur(String name) {
        model = new PlayerModel<P>(name);
        view = new PlayerView<P>(model);
    }

    /**
     * Méthode qui fait piocher un joueur dans un sac
     * 
     * @param sac Sac dans lequel pioche le joueur.
     */
    @Override
    public void piocher(Sac<P> sac) {
        model.piocher(sac);
        view.actualiser();
    }

    /**
     * Méthode qui supprime la main d'un joueur.
     */
    @Override
    public void jeter() {
        model.jeter();
    }

    /**
     * Increment le score de i points
     * 
     * @param i Valeur a ajouter au score
     */
    @Override
    public void scoreAdd(int i) {
        model.scoreAdd(i);
    }

    // Getters
    @Override
    public P getMain() {
        return model.getMain();
    }

    @Override
    public String getName() {
        return model.getName();
    }

    @Override
    public int getScore() {
        return model.getScore();
    }
}
