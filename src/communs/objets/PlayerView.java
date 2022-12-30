package communs.objets;

/**
 * Vue du joueur.
 */
public class PlayerView<P> {
    private PlayerModel<P> model;

    public void setModel(PlayerModel<P> model) {
        this.model = model;
    }

    public PlayerModel<P> getModel() {
        return model;
    }

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return "Nom : " + model.getName() + " Score : " + model.getscore() + "\nMain :\n" + model.getMain();
    }

}
