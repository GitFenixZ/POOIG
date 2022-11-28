package Communs;

import java.util.Scanner;

/**
 * Class permtant de modéliser le controleur du plateau permettant les
 * interractions vue modèle.
 */

public class PlateauControleur extends Carre {
    PlateauModel model;
    PlateauView view;

    public PlateauControleur(int hauteur, int largeur, int hauteurPiece, int largeurPiece) {
        model = new PlateauModel(hauteur, largeur, hauteurPiece, largeurPiece);
        view = new PlateauView();
        view.setModel(model);
    }

    @Override
    public String toString() {
        return view.toString();
    }

    public void placerPiece(Player player, Scanner sc) {
        boolean valide = false;

        boolean demandeDeplacement;
        Direction deplacement = Direction.UP;
        boolean demandeRotation;
        Direction rotation = Direction.ACTUEL;
        boolean placerPiece;

        System.out.println(player.getMain());
        while (!valide) {
            demandeDeplacement = true;
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
                System.out.println(view.afficher());
                System.out.println(player.getMain());
            }
            demandeRotation = true;
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
                System.out.println(view.afficher());
                System.out.println(player.getMain());
            }
            placerPiece = PlateauView.demandeBoolean(sc, "Voulez vous placer la pièce ici ?(oui/non)");
            if (placerPiece) {
                valide = model.possibleDePlacer(player.getMain(), model.getActuelX(), model.getActuelY());
                if (!valide) {
                    System.out.println("Erreur : position utilisé invalide.");
                }
            }
            System.out.println(view.afficher());
            System.out.println(player.getMain());
        }

        model.setPiece(model.getActuelX(), model.getActuelY(), player.getMain());

        /* Tableau infini */
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
        player.scoreadd(model.calculePoint(model.getActuelX(), model.getActuelY()));
    }

    public boolean possibleDePlacer(PieceControleur piece) {
        return model.possibleDePlacer(piece);
    }

    public String afficher() {
        return view.afficher();
    }
}
