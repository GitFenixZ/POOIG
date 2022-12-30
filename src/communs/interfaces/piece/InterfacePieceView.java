package communs.interfaces.piece;

import communs.objets.piece.PieceModel;

/**
 * Interface de la vue des Pieces.
 * 
 * V represente le type des valeurs qui apparait sur les pièces du jeu.
 * Exemple : Integer dans le domino.
 */
public interface InterfacePieceView<V> {
    // setter
    public void setModel(PieceModel<V> model);

    /**
     * Créer un affichage correct pour la fenetre
     */
    public void setimagePiece();

    public PieceModel<V> getModel();

    /**
     * Getter d'une ligne du tableau sous form de String
     * 
     * @param indice indice de la ligne voulu
     * @return un String representant une ligne du tableau
     */
    public String getligne(int indice);
}
