package Communs.Interfaces.Plateau;

import java.util.Scanner;

import Communs.Class.Plateau.PlateauModel;
import Communs.Interfaces.Carre.Direction;

/**
 * Interface de la vue du Plateau de jeu.
 */
public interface InterfacePlateauView {
    // setter
    public void setModel(PlateauModel model);

    /**
     * Creer un String qui represente une partie du plateau.
     * Les 8 position adjacente a l'endroit du plateau ou l'on est ainsi que la
     * position ou l'on est. Si il y a des piece dans les positions affichees, cela
     * les affiches.
     */
    public String afficher();

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
