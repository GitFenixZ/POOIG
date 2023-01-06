import java.awt.EventQueue;

public class Menu {

    public Menu() {
        // Thread-Safety
        EventQueue.invokeLater(
                () -> {
                    new MenuControleur();
                });
    }

    public static void main(String[] args) {
        new Menu();
    }

}