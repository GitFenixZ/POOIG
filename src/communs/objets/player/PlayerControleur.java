package communs.objets.player;

import communs.interfaces.player.InterfacePlayerControleur;
import communs.objets.Sac;

/**
 * Class controlant un joueur
 * P est le type des pieces que peut avoir le joueur
 */
public class PlayerControleur<P> implements InterfacePlayerControleur<P> {
    protected PlayerModel<P> model;
    protected PlayerView<P> view;

    /**
     * Retourne un String decrivant le joueur actuel
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
     * Methode qui fait piocher un joueur dans un sac
     * 
     * @param sac Sac dans lequel pioche le joueur.
     */
    @Override
    public void piocher(Sac<P> sac) {
        model.piocher(sac);
        view.actualiser();
    }

    /**
     * Methode qui supprime la main d'un joueur.
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
    public void scoreadd(int i) {
        model.scoreadd(i);
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
    public int getscore() {
        return model.getscore();
    }
}
