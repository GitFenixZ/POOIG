package communs.objets.plateau;

import java.util.Scanner;

import communs.exceptions.directionInvalide;
import communs.exceptions.positionInvalide;
import communs.interfaces.plateau.InterfacePlateauControleur;
import communs.objets.Direction;
import communs.objets.Point;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;

/**
 * Class permettant de modéliser le contrôleur du plateau permettant les
 * interactions vue modèle.
 * 
 * V est le types des valeurs qui apparaissent sur les pièces qui
 * pourront être placé sur le plateau.
 * Exemple : Integer dans le domino.
 */

public class PlateauControleur<V> implements InterfacePlateauControleur<V> {
    protected PlateauModel<V> model;
    protected PlateauView<V> view;

    /**
     * Constructeur
     * 
     * @param hauteur      La hauteur du tableau
     * @param largeur      La largeur du tableau
     * @param hauteurPiece La hauteur d'une pièce
     * @param largeurPiece La largeur d'une pièce
     */
    public PlateauControleur(int hauteurPiece, int largeurPiece) {
        model = new PlateauModel<V>(hauteurPiece, largeurPiece);
        view = new PlateauView<V>(this);
        view.setModel(model);
        view.actualiser();
    }

    public PlateauControleur() {
    }

    public PlateauModel<V> getModel() {
        return model;
    }

    public PlateauView<V> getView() {
        return view;
    }

    /**
     * Affiche l'intégralité des pièce du tableau.
     * Ligne par ligne.
     */
    @Override
    public String toString() {
        return view.toString();
    }

    /**
     * Méthode qui demande au joueur si il pense pouvoir jouer. Si il ne peut pas
     * alors il re-pioche, si il peut, la méthode l'invite a chercher.
     * 
     * @param sc     scanner qui attends les réponses au questions
     * @param player player joueur qui est en train de jouer
     * @return si le joueur peut jouer ou non
     */
    public boolean pensezVousPouvoirJouer(Scanner sc, PlayerControleur<PieceControleur<V>> player) {
        view.affichePlateauEtJoueur(player);
        return view.pensezVousPouvoirJouer(sc, player);
    }

    @Override
    /**
     * Place la pièce qu'a le player dans sa main sur le plateau.
     * 
     * @param game   partie qui se joue
     * @param player Joueur qui place sa pièce
     * @param sc     System.in permettra de lire la réponse de l'utilisateur et de
     *               savoir si le joueur veux placer sa pièce.
     */
    public void placerPiece(PlayerControleur<PieceControleur<V>> player, Scanner sc) {
        /** valide permet de verifier si la position choisi est valide */
        boolean valide = false;

        /**
         * demandeDeplacement permet de savoir si le player souhaite se déplacer sur le
         * plateau
         */
        boolean demandeDeplacement;
        Direction deplacement = Direction.ACTUEL;
        /**
         * demandeRotation permet de savoir si le player souhaite tourner sa piece
         */
        boolean demandeRotation;
        Direction rotation = Direction.ACTUEL;
        boolean placerPiece;

        // boucle qui continue tant que le choix fait par le joueurs est invalide
        while (!valide) {
            demandeDeplacement = true;
            // boucle qui demande le déplacement voulu par le joueur
            while (demandeDeplacement) {

                // affiche la partie du plateau ou l'on est.
                // et la main du joueur
                view.affichePlateauEtJoueur(player);

                demandeDeplacement = view.demandeBoolean(sc,
                        "Voulez vous vous déplacer sur le plateau? (oui/non)");

                if (demandeDeplacement) {
                    deplacement = view.demandeDirection(sc, "De quel coté? (haut/bas/droite/gauche)");
                    try {
                        model.deplacement(deplacement);
                    } catch (positionInvalide e) {
                        view.deplacement(deplacement);// affiche l'erreur
                    } catch (directionInvalide e) {
                        view.deplacement(deplacement);// affiche l'erreur
                    }
                }
            }

            demandeRotation = true;
            // boucle qui demande la rotation voulu par le joueur
            while (demandeRotation) {

                // affiche la partie du plateau ou l'on est.
                // et la main du joueur
                view.affichePlateauEtJoueur(player);

                demandeRotation = view.demandeBoolean(sc, "Voulez vous pivotez la pièce ? (oui/non)");
                if (demandeRotation) {
                    rotation = view.demandeDirection(sc, "De quel coté? (droite/gauche)");
                    try {
                        model.rotation(rotation, player);
                    } catch (directionInvalide e) {
                        view.erreurReponseInvalide(); // affiche l'erreur
                    }
                }
            }

            placerPiece = view.demandeBoolean(sc, "Voulez vous placer la pièce ici ?(oui/non)");
            if (placerPiece) {
                // essaye de placer la pièce a la position demande.
                try {
                    model.setPiece(model.getActuelPosition(), player.getMain());
                    valide = true;
                } catch (positionInvalide e) {
                    view.erreurReponseInvalide();
                }
            }
        }

        player.jeter();
        // On ajoute les points gagne au joueur
        player.scoreAdd(model.calculePoint(model.getActuelPosition()));
    }

    @Override
    /**
     * Vérifie s'il existe un emplacement sur le plateau où
     * on peut poser la pièce
     * tout en respectant les règles
     * 
     * @param piece La pièce que l'on veut placer
     * @return true s'il existe un emplacement;
     *         false sinon
     */
    public boolean existeEmplacement(PieceControleur<V> piece) {
        return model.existeEmplacement(piece);
    }

    @Override
    /**
     * Créer un String qui représente une partie du plateau.
     * Les 8 positions adjacentes à l'endroit du plateau où l'on est ainsi que la
     * position ou l'on est.
     * Si il y a des pièce dans les positions affichées, cela les affiches.
     */
    public void afficher() {
        view.afficher();
    }

    @Override
    /**
     * Donne une coordonnée où l'on peut placer la pièce.
     * Et rotate la pièce dans le bon sens pour la placer a la position indiqué.
     * 
     * @param piece Piece a placer
     * @return des coordonnée ou l'on peut placer la pièce
     */
    public Point getEmplacementPossible(PieceControleur<V> piece) {
        return model.getEmplacementPossible(piece);
    }

    @Override
    /**
     * Permet a un joueur de placer une pièce ou il le veut.
     * 
     * @param player joueur qui vas jouer
     * @param point  position sur laquelle la piece vas être placée
     */
    public void setPiece(PlayerControleur<PieceControleur<V>> player, Point p) {
        try {
            player.getMain().getView().setImagePiece();
            model.setPiece(p, player.getMain());
            player.scoreAdd(model.calculePoint(p));
            view.setPiece();
            view.actualiser();
            player.getMain().getView().revalidate();
        } catch (positionInvalide e) {
        }
    }

    /**
     * Méthode qui permet d'initialiser le plateau avec un pièce en son centre.
     * 
     * @param sac sac du quel est tiré la pièce.
     */
    @Override
    public void start(Sac<PieceControleur<V>> sac) {
        model.start(sac);
        view.setPiece();
    }

    /**
     * Permet de se déplacer d'une colonne sur la droite sur le plateau.
     */
    public void allerADroite() {
        try {
            model.allerADroite();
            view.actualiser();
        } catch (positionInvalide e) {
        }
    }

    /**
     * Permet de se déplacer d'une colonne sur la gauche sur le plateau.
     */
    public void allerAGauche() {
        try {
            model.allerAGauche();
            view.actualiser();
        } catch (positionInvalide e) {
        }
    }

    /**
     * Permet de se déplacer d'une ligne vers le bas sur le plateau.
     */
    public void allerEnBas() {
        try {
            model.allerEnBas();
            view.actualiser();
        } catch (positionInvalide e) {
        }
    }

    /**
     * Permet de se déplacer d'une ligne vers le haut sur le plateau.
     */
    public void allerEnHaut() {
        try {
            model.allerEnHaut();
            view.actualiser();
        } catch (positionInvalide e) {
        }
    }

    public Point getActuelPosition() {
        return model.getActuelPosition();
    }

    /**
     * Regarde si l'on peut placer la piece d'un joueur, a la position actuel
     */
    public boolean possibleDePlacer(PlayerControleur<PieceControleur<V>> player) {
        return model.possibleDePlacer(player.getMain(), getActuelPosition());
    }
}
