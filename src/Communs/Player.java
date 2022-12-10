package Communs;

/**
 * Class modélisant un joueur avec un nom, une main et un score.
 */
public class Player {
    /** Piece qu'a le joueur en main */
    private PieceControleur main;
    /** Nom du joueur */
    private String name;
    /** Score actuel du joueur */
    private int score;

    /**
     * Retourne un String decrivant le joueur actuel
     */
    @Override
    public String toString() {
        return "Nom : " + name + " Score : " + score + "\nMain :\n" + main;
    }

    /**
     * Constructeur
     * 
     * @param name initialise le nom du joueur
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Methode qui fait piocher un joueur dans un sac
     * 
     * @param sac Sac dans lequel pioche le joueur.
     */
    public void piocher(Sac sac) {
        main = sac.tire();
    }

    /**
     * Methode qui supprime la main d'un joueur.
     */
    public void jeter() {
        main = null;
    }

    /**
     * Increment le score de i points
     * 
     * @param i Valeur a ajouter au score
     */
    public void scoreadd(int i) {
        score += i;
    }

    // Getters
    public PieceControleur getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public int getscore() {
        return score;
    }
}
