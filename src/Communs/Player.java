package Communs;

/**
 * Class modélisant un joueur avec un nom, une main et un score.
 */
public class Player {
    private PieceControleur main;
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public void piocher(Sac sac) {
        main = sac.tire();
    }

    public PieceControleur getMain() {
        return main;
    }

    public void jeter() {
        main = null;
    }

    public String getName() {
        return name;
    }

    public void scoreadd(int i) {
        score += i;
    }

    public int getscore() {
        return score;
    }
}
