package communs.interfaces;

/**
 * Interface du Sac dans lequel se trouve des pieces du jeu.
 * 
 * P est le type des objets dans le sac.
 * Exemple : PieceControleur<Integer> dans domino.
 */
public interface InterfaceSac<P> {

    /**
     * Méthode qui tire une piece aleatoire dans le sac
     * 
     * @return un piece du sac
     */
    public P tire();

    /**
     * Méthode qui ajoute une piece dans le sac
     */
    public void ajouter(P piece);

    /**
     * @return le nombre de piece dans le sac en se moment
     */
    public int getNombreDePiece();

    /**
     * @return si le sac est vide
     */
    public boolean isEmpty();

    /**
     * @return si le sac est plein
     */
    public boolean isFull();
}
