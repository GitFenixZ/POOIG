package communs.objets.plateau;

import java.awt.GridLayout;
import java.util.Scanner;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import communs.exceptions.positionInvalide;
import communs.interfaces.Demander;
import communs.interfaces.plateau.InterfacePlateauView;
import communs.objets.Direction;
import communs.objets.Point;
import communs.objets.piece.PieceControleur;
import communs.objets.player.PlayerControleur;

/**
 * Class modélisant la vue du plateau
 * 
 * V est le types des valeurs qui apparaissent sur les pièces qui
 * pourront être placé sur le plateau.
 * Exemple : Integer dans le domino.
 */
public class PlateauView<V> extends JPanel implements InterfacePlateauView<V>, Demander {
    private PlateauModel<V> model;
    private PlateauControleur<V> controleur;

    public PlateauView(PlateauControleur<V> controleur) {
        super();
        setVisible(true);
        this.controleur = controleur;
    }

    @Override
    /**
     * Rafraîchi l'affichage graphique.
     */
    public void actualiser() {
        removeAll();
        setLayout(new GridLayout(5, 5));
        PieceControleur<V> p;

        // pour toutes les pièces autour de notre position dans un rayon de 2 cases
        for (int j = model.getActuelY() - 2; j <= model.getActuelY() + 2; j++) {
            for (int i = model.getActuelX() - 2; i <= model.getActuelX() + 2; i++) {

                JPanel piece = new JPanel(new GridLayout());
                try {
                    p = model.getPiece(new Point(i, j));
                    if (p != null) {
                        piece.add(p.getView()); // ajoute la représentation de la pièce
                    } else {
                        piece.setBackground(Color.DARK_GRAY); // rend la pièce grisée
                    }
                } catch (positionInvalide e) {
                    piece.setBackground(Color.DARK_GRAY); // rend la pièce grisée
                }
                if (i == model.getActuelX() && j == model.getActuelY()) {
                    piece.setBackground(Color.BLACK); // noirci le centre pour se repérer
                }
                piece.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                add(piece);
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
        actualiser();
        revalidate();
    }

    /**
     * Répète le string n fois,
     * Cette fonction a été crée parce que la fonction String.repeat(int n)
     * n'existe pas en Java 10 qui est la version utilisée par
     * les machines du SCRIPT
     *
     * @param s Le string
     * @param n Le nombre de répétitions
     *
     * @return Le String s répété n fois
     */
    private static String repeatString(String s, int n) {
        if (n < 0)
            return null;
        String res = "";
        for (int i = 0; i < n; i++) {
            res += s;
        }
        return res;
    }

    @Override
    /**
     * Crée un String qui représente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichées, cela
     * les affiches.
     */
    public void afficher() {
        String res = repeatString("-", (model.getLargeurPiece() * 2 + 2) * (5) + 1) + "\n";

        // Parcourt les lignes du plateau
        for (int j = model.getActuelY() - 2; j <= model.getActuelY() + 2; j++) {

            // Parcourt les lignes des pièces du plateau de la ligne actuelle
            for (int k = 0; k < model.getHauteurPiece(); k++) {
                res += "|";

                // Parcourt les colonnes
                for (int i = model.getActuelX() - 2; i <= model.getActuelX() + 2; i++) {

                    try {
                        Point point = new Point(i, j);
                        if (model.getPiece(point) != null) {
                            res += model.getPiece(point).getLigne(k)
                                    + "|";
                        } else {
                            if (i == model.getActuelX() && j == model.getActuelY()) {
                                res += repeatString("+", model.getLargeurPiece() * 2 + 1) + "|";
                            } else {
                                res += repeatString(" ", model.getLargeurPiece() * 2 + 1) + "|";
                            }
                        }
                    } catch (positionInvalide e) {
                        res += repeatString(" ", model.getLargeurPiece() * 2 + 1) + "|";
                    }
                }
                res += "\n";
            }
            res += repeatString("-", (model.getLargeurPiece() * 2 + 2) * (5) + 1)
                    + "\n";
        }
        System.out.println(res);
    }

    /**
     * Affiche l'intégralité des pièce du tableau.
     * Ligne par ligne.
     */
    @Override
    public String toString() {
        String[] affichage = new String[model.getHauteur() * model.getHauteurPiece()];

        // Parcourt les lignes du plateau
        for (int i = 0; i < model.getHauteur(); i++) {

            // Parcourt les colonnes du plateau
            for (int j = 0; j < model.getLargeur(); j++) {

                // Parcourt les lignes de la piece
                for (int k = 0; k < model.getHauteurPiece(); k++) {
                    if (affichage[i * model.getHauteurPiece() + k] == null) {
                        affichage[i * model.getHauteurPiece() + k] = "";
                    }
                    try {
                        Point point = new Point(j, i);
                        if (model.getPiece(point) != null) {
                            affichage[i * model.getHauteurPiece() + k] += model.getPiece(point).getLigne(k) + "|";
                        } else {
                            affichage[i * model.getHauteurPiece() + k] += repeatString(" ",
                                    model.getLargeurPiece() * 2 + 1)
                                    + "|";
                        }
                    } catch (positionInvalide e) {
                    }
                }
            }
        }
        String res = repeatString("-", (model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                + "\n";
        for (int i = 0; i < affichage.length; i++) {
            res += "|" + affichage[i] + "\n";
            if (i % model.getHauteurPiece() == model.getHauteurPiece() - 1) {
                res += repeatString("-", (model.getLargeurPiece() * model.getLargeur() + model.getLargeur()) * 2 + 1)
                        + "\n";
            }
        }
        return res;
    }

    /**
     * Méthode qui permet de mettre un message d'erreur en fonction du deplacement
     * effectué.
     * 
     * @param deplacement deplacement qui est realise
     */
    @Override
    public void deplacement(Direction deplacement) {
        switch (deplacement) {
            case RIGHT:
                System.out.println("Erreur : vous ne pouvez pas vous déplacer à droite.");
                break;
            case LEFT:
                System.out.println("Erreur : vous ne pouvez pas vous déplacer à gauche.");
                break;
            case UP:
                System.out.println("Erreur : vous ne pouvez pas vous déplacer en haut.");
                break;
            case DOWN:
                System.out.println("Erreur : vous ne pouvez pas vous déplacer en bas.");
                break;
            default:
                System.out.println("Erreur : reponse invalide.");
                break;
        }
    }

    /**
     * Affiche le plateau et la main d'un joueur
     * 
     * @param player joueur dont il faut afficher la main
     */
    @Override
    public void affichePlateauEtJoueur(PlayerControleur<PieceControleur<V>> player) {
        // affiche la partie du plateau ou l'on est.
        afficher();
        // affiche les infos du joueur
        System.out.println(player);
    }

    /**
     * Méthode qui demande au joueur si il pense pouvoir jouer.
     * Si il ne peut pas alors il re-pioche, si il peut, la Méthode l'invite a
     * chercher.
     * 
     * @param sc     scanner qui attends les réponses au questions
     * @param player joueur qui est en train de jouer
     * @return si le joueur peut jouer ou non
     */
    @Override
    public boolean pensezVousPouvoirJouer(Scanner sc, PlayerControleur<PieceControleur<V>> player) {
        if (demandeBoolean(sc, "Pensez vous pouvoir jouer ? (oui / non)")) {
            // Si la réponse est "oui"
            if (controleur.existeEmplacement(player.getMain())) {
                System.out.println("Oui ! Vous avez effectivement une ou plusieurs solutions.");
                controleur.placerPiece(player, sc);
                return true;
            } else {
                System.out.println("Vous vous trompez, aucune solution n'est valide!");
                return false;
            }
        } else {
            // Si la réponse est "non"
            if (controleur.existeEmplacement(player.getMain())) {
                System.out.println("Cherchez bien ! Car il y a une ou des solutions!");
                controleur.placerPiece(player, sc);
                return true;
            } else {
                System.out.println("En effet, aucune solution n'est valide.");
                return false;
            }
        }
    }
}
