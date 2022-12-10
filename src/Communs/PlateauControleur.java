package Communs;

import java.util.Scanner;

/**
 * Class permtant de modéliser le controleur du plateau permettant les
 * interractions vue modèle.
 */

public class PlateauControleur extends Carre {
    PlateauModel model;
    PlateauView view;

    /**
     * Constructeur
     * 
     * @param hauteur      La hauteur du tableau
     * @param largeur      La largeur du tableau
     * @param hauteurPiece La hauteur d'une pièce
     * @param largeurPiece La largeur d'une pièce
     */
    public PlateauControleur(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        model = new PlateauModel(hauteur, largeur, hauteurPiece, largeurPiece);
        view = new PlateauView();
        view.setModel(model);
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
     * Place la pièce qu'a le player dans sa main sur le plateau.
     * 
     * @param player Joueur qui place sa piece
     * @param sc     System.in permettra de lire la reponse de l'utilisateur et de
     *               savoir si le joueur veux placer sa piece.
     */
    public void placerPiece(Player player, Scanner sc) {
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

        // print la piece dans la main du joueur
        System.out.println(player.getMain());

        // boucle qui continue tant que le choix fait par le joueurs est invalide
        while (!valide) {
            demandeDeplacement = true;
            // boucle qui demande le deplacement voulu par le joueur
            while (demandeDeplacement) {
                demandeDeplacement = PlateauView.demandeBoolean(sc,
                        "Voulez vous vous deplacer sur le plateau? (oui/non)");
                if (demandeDeplacement) {
                    deplacement = PlateauView.demandeDirection(sc, "De quel coté? (haut/bas/droite/gauche)");
                    switch (deplacement) {
                        case RIGHT:
                            model.allerADroite();
                            break;
                        case LEFT:
                            model.allerAGauche();
                            break;
                        case UP:
                            model.allerEnHaut();
                            break;
                        case DOWN:
                            model.allerEnBas();
                            break;
                        default:
                            System.out.println("Erreur : reponse invalide.");
                            break;
                    }
                } else {
                    demandeDeplacement = false;
                }
                // affiche la partie du plateau ou l'on est.
                System.out.println(view.afficher());
                // affiche la main du joueur
                System.out.println(player.getMain());
            }
            demandeRotation = true;
            // boucle qui demande la rotation voulu par le joueur
            while (demandeRotation) {
                demandeRotation = PlateauView.demandeBoolean(sc, "Voulez vous pivotez la pièce ? (oui/non)");
                if (demandeRotation) {
                    rotation = PlateauView.demandeDirection(sc, "De quel coté? (droite/gauche)");
                    switch (rotation) {
                        case RIGHT:
                            player.getMain().pivotDroite();
                            System.out.println(player.getMain());
                            break;
                        case LEFT:
                            player.getMain().pivotGauche();
                            System.out.println(player.getMain());
                            break;
                        default:
                            System.out.println("Erreur : reponse invalide.");
                            break;
                    }
                } else {
                    demandeRotation = false;
                }
                // affiche la partie du plateau ou l'on est.
                System.out.println(view.afficher());
                // affiche la main du joueur
                System.out.println(player.getMain());
            }
            placerPiece = PlateauView.demandeBoolean(sc, "Voulez vous placer la pièce ici ?(oui/non)");
            if (placerPiece) {
                valide = model.possibleDePlacer(player.getMain(), model.getActuelX(), model.getActuelY());
                if (!valide) {
                    System.out.println("Erreur : position utilisé invalide.");
                }
            }
            // affiche la partie du plateau ou l'on est.
            System.out.println(view.afficher());
            // affiche la main du joueur
            System.out.println(player.getMain());
        }

        // place la piece a la position demande.
        model.setPiece(model.getActuelX(), model.getActuelY(), player.getMain());

        /*
         * Tableau infini :
         * Si la piece est pose sur un cote. On elargie la tableau sur ce cote en
         * question.
         */
        if (model.getActuelX() == model.getLargeur() - 1) {
            model.ajouterUnCote(Direction.RIGHT);
        }
        if (model.getActuelX() == 0) {
            model.ajouterUnCote(Direction.LEFT);
        }
        if (model.getActuelY() == model.getHauteur() - 1) {
            model.ajouterUnCote(Direction.DOWN);
        }
        if (model.getActuelY() == 0) {
            model.ajouterUnCote(Direction.UP);
        }

        // On ajoute les points gagne au joueur
        player.scoreadd(model.calculePoint(model.getActuelX(), model.getActuelY()));
    }

    /**
     * Verifie si on peut placer une piece a l'emplacement actuel
     * 
     * @param piece piece que l'on veut placer
     * @return si on peut placer la piece ici.
     */
    public boolean possibleDePlacer(PieceControleur piece) {
        return model.possibleDePlacer(piece);
    }

    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public String afficher() {
        return view.afficher();
    }
}
