package communs.objets.plateau;

import java.util.ArrayList;
import java.util.Scanner;

import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import communs.exceptions.positionInvalide;
import communs.interfaces.plateau.InterfacePlateauView;
import communs.objets.Direction;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;

/**
 * Class modélisant la vue du plateau
 * 
 * V est le types des valeurs qui apparaissent sur les pièces qui
 * pourront être placé sur le plateau.
 * Exemple : Integer dans le domino.
 */
public class PlateauView<V> extends JPanel implements InterfacePlateauView<V> {
    private PlateauModel<V> model;

    public PlateauView() {
        super();
        setVisible(true);
    }

    @Override
    /**
     * Raffraichi l'affichage graphique.
     */
    public void refreshGridLayout() {
        removeAll();
        setLayout(new GridLayout(model.getHauteur(), model.getLargeur(), 0, 0));
        for (ArrayList<PieceControleur<V>> ligne : model.getTableau()) {
            for (PieceControleur<V> v : ligne) {
                if (v != null) {
                    v.getView().setSize(new Dimension(50, 50));
                    add(v.getView());
                } else {
                    add(new JLabel());
                }
            }
        }
    }

    // setter
    @Override
    public void setModel(PlateauModel<V> model) {
        this.model = model;
    }

    @Override
    public void setPiece() {
        refreshGridLayout();
        revalidate();
    }

    @Override
    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public String afficher() {
        String res = "";

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

        res += "-".repeat((model.getLargeurPiece() * 2 + 2) * (xfin - xdepart) + 1) + "\n";

        for (int y = ydepart; y < yfin; y++) {
            for (int i = 0; i < model.getLargeurPiece(); i++) {
                res += "|";
                for (int x = xdepart; x < xfin; x++) {
                    try {
                        Point point = new Point(model.getActuelX() + x - 1, model.getActuelY() + y - 1);
                        if (model.getPiece(point) != null) {
                            res += model.getPiece(point).getligne(i)
                                    + "|";
                        } else {
                            res += " ".repeat(model.getLargeurPiece() * 2 + 1) + "|";
                        }
                    } catch (positionInvalide e) {
                    }
                }
                res += "\n";
            }
            res += "-".repeat((model.getLargeurPiece() * 2 + 2) * (xfin - xdepart) + 1)
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
                    try {
                        Point point = new Point(j, i);
                        if (model.getPiece(point) != null) {
                            affichage[i * model.getHauteurPiece() + k] += model.getPiece(point).getligne(k) + "|";
                        } else {
                            affichage[i * model.getHauteurPiece() + k] += " ".repeat(model.getLargeurPiece() * 2 + 1)
                                    + "|";
                        }
                    } catch (positionInvalide e) {
                    }
                }
            }
        }
        String res = "-".repeat((model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                + "\n";
        for (int i = 0; i < affichage.length; i++) {
            res += "|" + affichage[i] + "\n";
            if (i % model.getHauteurPiece() == model.getHauteurPiece() - 1) {
                res += "-".repeat((model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
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
