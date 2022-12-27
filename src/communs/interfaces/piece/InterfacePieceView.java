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
}
