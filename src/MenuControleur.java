import javax.swing.JFrame;

public class MenuControleur extends JFrame {
    private MenuModel model;
    private MenuView view;

    public MenuControleur() {
        super("Menu");

        model = new MenuModel();
        view = new MenuView(model);
        view.setControleur(this);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(view);
        /**
         * Message pour Tony :
         * faire revalidate() a chaque fois qu'un joueur pose un piece.
         * Ca actualise les element a afficher sur la fentre.
         * dans le action Listener de Valider !
         */
        revalidate();
    }

    public MenuView getView() {
        return view;
    }

    public void PlayDomino() {
        model.PlayDomino();
        view.removeAll();
        view.add(model.getGame().getView());
        view.revalidate();
    }

    public void PlayDominoTerminale() {
        model.PlayDominoTerminale();
    }

    public void PlayCarcassonne() {
        model.PlayCarcassonne();
        view.removeAll();
        view.add(model.getGame().getView());
        view.revalidate();
    }

    public static void main(String[] args) {
        new MenuControleur();
    }
}
