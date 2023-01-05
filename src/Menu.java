import java.awt.EventQueue;

public class Menu {
    private MenuModel model;
    private MenuView view;
    private MenuControleur controller;

    public Menu() {
        // Thread-Safety
        EventQueue.invokeLater(() -> view = new MenuView());
        model = new MenuModel(view);
        controller = new MenuControleur(model, view);

        view.setControleur(controller);

    }

    public static void main(String[] args) {
        new Menu();
    }

}
