package Communs;

import java.util.Scanner;

/**
 * Class modélisant la vue du plateau
 */
public class PlateauView extends Carre {
    private PlateauModel model;

    /**
     * Constructeur
     * 
     * @param model
     */
    public void setModel(PlateauModel model) {
        this.model = model;
    }

    /**
     * Retourne un String avec un caractere qui se repete un certain nombre de fois
     * 
     * @param c caractere a repeter
     * @param n nombre de fois a repeter
     * @return un String avec n fois le caractere c
     */
    public static String repeatChar(char c, int n) {
        String res = "";
        for (int i = 0; i < n; i++) {
            res += c;
        }
        return res;
    }

    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public String afficher() {
        int xdepart = 0;
        int xfin = 3;
        int ydepart = 0;
        int yfin = 3;
        if (model.getActuelX() == 0) {
            xdepart++;
        }
        if (model.getActuelX() == model.getLargeur() - 1) {
            xfin--;
        }
        if (model.getActuelY() == 0) {
            ydepart++;
        }
        if (model.getActuelY() == model.getHauteur() - 1) {
            yfin--;
        }

        String res = repeatChar('-', (model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                + "\n";
        for (int y = ydepart; y < yfin; y++) {
            for (int i = 0; i < model.getLargeurPiece(); i++) {
                res += "|";
                for (int x = xdepart; x < xfin; x++) {
                    if (model.getPiece(model.getActuelX() + x - 1, model.getActuelY() + y - 1) != null) {
                        res += model.getPiece(model.getActuelX() + x - 1, model.getActuelY() + y - 1).getligne(i) + "|";
                    } else {
                        res += repeatChar(' ', model.getLargeurPiece() * 2 + 1) + "|";
                    }
                }
                res += "\n";
            }
            res += repeatChar('-', (model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                    + "\n";
        }
        return res;
    }

    /**
     * Affiche l'intégralité des pièce du tableau.
     * Ligne par ligne.
     */
    @Override
    public String toString() {
        String[] affichage = new String[model.getHauteur() * model.getHauteurPiece()];
        for (int i = 0; i < model.getHauteur(); i++) {
            for (int j = 0; j < model.getLargeur(); j++) {
                for (int k = 0; k < model.getHauteurPiece(); k++) {
                    if (affichage[i * model.getHauteurPiece() + k] == null) {
                        affichage[i * model.getHauteurPiece() + k] = "";
                    }
                    if (model.getPiece(j, i) != null) {
                        affichage[i * model.getHauteurPiece() + k] += model.getPiece(j, i).getligne(k) + "|";
                    } else {
                        affichage[i * model.getHauteurPiece() + k] += repeatChar(' ', model.getLargeurPiece() * 2 + 1)
                                + "|";
                    }
                }
            }
        }
        String res = repeatChar('-', (model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                + "\n";
        for (int i = 0; i < affichage.length; i++) {
            res += "|" + affichage[i] + "\n";
            if (i % model.getHauteurPiece() == 4) {
                res += repeatChar('-', (model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                        + "\n";
            }
        }
        return res;
    }

    /**
     * Methode qui permet de demander une direction dans le terminal.
     * 
     * @param sc       System.in permettra de lire la reponse de l'utilisateur
     * @param question La question qui vas être posee.
     * @return retourn le cote correpondant a la reponse.
     */
    public static Direction demandeDirection(Scanner sc, String question) {
        System.out.println(question);
        String demande = sc.nextLine();
        switch (demande.toUpperCase()) {
            case "DROITE":
                return Direction.RIGHT;
            case "GAUCHE":
                return Direction.LEFT;
            case "HAUT":
                return Direction.UP;
            case "BAS":
                return Direction.DOWN;
            default:
                return Direction.ACTUEL;
        }
    }

    /**
     * Methode qui permet de demander un boolean.
     * 
     * @param sc       System.in permettra de lire la reponse de l'utilisateur
     * @param question La question qui vas être posee.
     * @return retourn le boolean correpondant a la reponse.
     */
    public static boolean demandeBoolean(Scanner sc, String question) {
        System.out.println(question);
        String demande = sc.nextLine();
        switch (demande.toUpperCase()) {
            case "OUI":
                return true;
            case "NON":
                return false;
            default:
                System.out.println("Erreur : réponse invalide.");
                return demandeBoolean(sc, question);
        }
    }
}
