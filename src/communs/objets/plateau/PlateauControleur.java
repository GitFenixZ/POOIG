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
 * Class permtant de modéliser le controleur du plateau permettant les
 * interractions vue modèle.
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
     * Methode qui demande au joueur si il pense pouvoir jouer. Si il ne peut pas
     * alors il repioche, si il peut, la methode l'invite a chercher.
     * 
     * @param sc     scanner qui attends les reponses au questions
     * @param player player joueur qui est en train de jouer
     * @return si le joueur peut jouer ou non
     */
    public boolean pensezVousPouvoirJouer(Scanner sc, PlayerControleur<PieceControleur<V>> player) {
        return view.pensezVousPouvoirJouer(sc, player);
    }

    // @Override
    /**
     * Place la pièce qu'a le player dans sa main sur le plateau.
     * 
     * @param game   partie qui se joue
     * @param player Joueur qui place sa piece
     * @param sc     System.in permettra de lire la reponse de l'utilisateur et de
     *               savoir si le joueur veux placer sa piece.
     */
    public void placerPiece(PlayerControleur<PieceControleur<V>> player, Scanner sc) {
        /** valide permet de verifier si la position choisi est valide */
        boolean valide = false;

        /**
         * demandeDeplacement permet de savoir si le player souhaite se deplacer sur le
         * plateau
         */
        boolean demandeDeplacement;
        Direction deplacement = Direction.ACTUEL;
        /**
         * demandeRotation permet de savoir si le player souhaite rotater sa piece
         */
        boolean demandeRotation;
        Direction rotation = Direction.ACTUEL;
        boolean placerPiece;

        // boucle qui continue tant que le choix fait par le joueurs est invalide
        while (!valide) {
            demandeDeplacement = true;
            // boucle qui demande le deplacement voulu par le joueur
            while (demandeDeplacement) {

                // affiche la partie du plateau ou l'on est.
                // et la main du joueur
                view.affichePlateauEtJoueur(player);

                demandeDeplacement = view.demandeBoolean(sc,
                        "Voulez vous vous deplacer sur le plateau? (oui/non)");

                if (demandeDeplacement) {
                    deplacement = view.demandeDirection(sc, "De quel coté? (haut/bas/droite/gauche)");
                    try {
                        model.deplacement(deplacement);
                    } catch (positionInvalide e) {
                        view.deplacement(deplacement);// affiche l'erreur
                    } catch (directionInvalide e) {
                        view.deplacement(deplacement);// affiche l'erreur
                    }
                } else {
                    demandeDeplacement = false;
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
                } else {
                    demandeRotation = false;
                }
            }

            placerPiece = view.demandeBoolean(sc, "Voulez vous placer la pièce ici ?(oui/non)");
            if (placerPiece) {
                // essaye de placer la piece a la position demande.
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
        player.scoreadd(model.calculePoint(model.getActuelPosition()));
    }

    @Override
    /**
     * Verifie si on peut placer une piece a l'emplacement actuel
     * 
     * @param piece piece que l'on veut placer
     * @return si on peut placer la piece ici.
     */
    public boolean possibleDePlacer(PieceControleur<V> piece) {
        return model.possibleDePlacer(piece);
    }

    @Override
    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public void afficher() {
        view.afficher();
    }

    @Override
    /**
     * Donnes un coordonnée ou l'on peut placer la piece.
     * 
     * @param piece Piece a placer
     * @return des coordonnée ou l'on peut placer la pièce
     */
    public Point peutPlacer(PieceControleur<V> piece) {
        return model.peutPlacer(piece);
    }

    @Override
    /**
     * Permet a un joueur de placer une pièce ou il le veut.
     * 
     * @param player joueur qui vas jouer
     * @param point  position sur laquel la piece vas etre placee
     */
    public void setPiece(PlayerControleur<PieceControleur<V>> player, Point p) {
        try {
            player.getMain().getView().setimagePiece();
            model.setPiece(p, player.getMain());
            player.scoreadd(model.calculePoint(p));
            view.setPiece();
            view.actualiser();
            player.getMain().getView().revalidate();
        } catch (positionInvalide e) {
        }
    }

    /**
     * Methode qui permet d'initialiser le plateau avec un pièce en son centre.
     * 
     * @param sac sac du quel est tiré la pièce.
     */
    @Override
    public void start(Sac<PieceControleur<V>> sac) {
        PieceControleur<V> piece = sac.tire();
        piece.getView().setimagePiece();
        model.start(sac);
        view.setPiece();
    }

    /**
     * Permet de se deplacer d'une colonne sur la droite sur le plateau.
     */
    public void allerADroite() {
        try {
            model.allerADroite();
            view.actualiser();
        } catch (positionInvalide e) {
        }
    }

    /**
     * Permet de se deplacer d'une colonne sur la gauche sur le plateau.
     */
    public void allerAGauche() {
        try {
            model.allerAGauche();
            view.actualiser();
        } catch (positionInvalide e) {
        }
    }

    /**
     * Permet de se deplacer d'une ligne vers le bas sur le plateau.
     */
    public void allerEnBas() {
        try {
            model.allerEnBas();
            view.actualiser();
        } catch (positionInvalide e) {
        }
    }

    /**
     * Permet de se deplacer d'une ligne vers le haut sur le plateau.
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
