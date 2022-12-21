package communs.objets.plateau;

import java.util.Scanner;

import communs.exceptions.positionInvalide;
import communs.interfaces.Direction;
import communs.interfaces.plateau.InterfacePlateauControleur;
import communs.objets.Bot;
import communs.objets.Player;
import communs.objets.Sac;
import communs.objets.piece.PieceControleur;

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
    public PlateauControleur(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        model = new PlateauModel<V>(hauteur, largeur, hauteurPiece, largeurPiece);
        view = new PlateauView<V>();
        view.setModel(model);
    }

    public PlateauControleur() {
    }

    /**
     * Affiche l'intégralité des pièce du tableau.
     * Ligne par ligne.
     */
    @Override
    public String toString() {
        return view.toString();
    }

    @Override
    /**
     * Place la pièce qu'a le player dans sa main sur le plateau.
     * 
     * @param player Joueur qui place sa piece
     * @param sc     System.in permettra de lire la reponse de l'utilisateur et de
     *               savoir si le joueur veux placer sa piece.
     */
    public void placerPiece(Player<PieceControleur<V>> player, Scanner sc) {
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
                System.out.println(view.afficher());
                // affiche la main du joueur
                System.out.println(player.getMain());

                demandeDeplacement = PlateauView.demandeBoolean(sc,
                        "Voulez vous vous deplacer sur le plateau? (oui/non)");
                if (demandeDeplacement) {
                    deplacement = PlateauView.demandeDirection(sc, "De quel coté? (haut/bas/droite/gauche)");
                    switch (deplacement) {
                        case RIGHT:
                            try {
                                model.allerADroite();
                            } catch (positionInvalide e) {
                                System.out.println("Erreur : vous ne pouvez pas vous deplacer à droite.");
                            }
                            break;
                        case LEFT:
                            try {
                                model.allerAGauche();
                            } catch (positionInvalide e) {
                                System.out.println("Erreur : vous ne pouvez pas vous deplacer à gauche.");
                            }
                            break;
                        case UP:
                            try {

                                model.allerEnHaut();
                            } catch (positionInvalide e) {
                                System.out.println("Erreur : vous ne pouvez pas vous deplacer en haut.");
                            }
                            break;
                        case DOWN:
                            try {
                                model.allerEnBas();
                            } catch (positionInvalide e) {
                                System.out.println("Erreur : vous ne pouvez pas vous deplacer en bas.");
                            }
                            break;
                        default:
                            System.out.println("Erreur : reponse invalide.");
                            break;
                    }
                } else {
                    demandeDeplacement = false;
                }
            }
            demandeRotation = true;
            // boucle qui demande la rotation voulu par le joueur
            while (demandeRotation) {

                // affiche la partie du plateau ou l'on est.
                System.out.println(view.afficher());
                // affiche la main du joueur
                System.out.println(player.getMain());

                demandeRotation = PlateauView.demandeBoolean(sc, "Voulez vous pivotez la pièce ? (oui/non)");
                if (demandeRotation) {
                    rotation = PlateauView.demandeDirection(sc, "De quel coté? (droite/gauche)");
                    switch (rotation) {
                        case RIGHT:
                            player.getMain().pivotDroite();
                            break;
                        case LEFT:
                            player.getMain().pivotGauche();
                            break;
                        default:
                            System.out.println("Erreur : reponse invalide.");
                            break;
                    }
                } else {
                    demandeRotation = false;
                }
            }
            placerPiece = PlateauView.demandeBoolean(sc, "Voulez vous placer la pièce ici ?(oui/non)");
            if (placerPiece) {
                // verifie si la position est valide
                valide = model.possibleDePlacer(player.getMain(), model.getActuelX(), model.getActuelY());
                if (!valide) {
                    System.out.println("Erreur : position utilisé invalide.");
                } else {
                    // place la piece a la position demande.
                    try {
                        model.setPiece(model.getActuelX(), model.getActuelY(), player.getMain());
                    } catch (positionInvalide e) {
                        // si il rencontre un problème lors du depot de la pièce (ne doit jamais arrivé)
                        valide = false;
                        System.out.println("Erreur : position utilisé invalide.");
                    }
                }
            }
        }
        // On ajoute les points gagne au joueur
        player.scoreadd(model.calculePoint(model.getActuelX(), model.getActuelY()));
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
    public String afficher() {
        return view.afficher();
    }

    /**
     * Methode qui permet d'initialiser le plateau avec un pièce en son centre.
     * 
     * @param sac sac du quel est tiré la pièce.
     */
    @Override
    public void start(Sac<PieceControleur<V>> sac) {
        model.start(sac);
    }

    @Override
    /**
     * Donnes un coordonnée ou l'on peut placer la piece.
     * 
     * @param piece Piece a placer
     * @return des coordonnée ou l'on peut placer la pièce
     */
    public int[] peutPlacer(PieceControleur<V> piece) {
        return model.peutPlacer(piece);
    }

    @Override
    /**
     * Permet a un bot de placer une pièce ou il le veut.
     * 
     * @param bot bot qui vas jouer
     * @param x   colonne a laquel on veut la placer
     * @param y   ligne a laquel on veut la placer
     */
    public void setPiece(Bot<V> bot, int x, int y) {
        try {
            model.setPiece(x, y, bot.getMain());
            bot.scoreadd(model.calculePoint(x, y));
        } catch (positionInvalide e) {
        }
    }
}
