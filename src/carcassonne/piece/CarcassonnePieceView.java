package carcassonne.piece;

import communs.objets.piece.PieceView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;

public class CarcassonnePieceView extends PieceView<Terrain> {

    int id;
    BufferedImage image;

    public CarcassonnePieceView(int i) {
        super();
        id = i;
    }

    @Override
    /**
     * Getter d'une ligne du tableau sous form de String
     * 
     * @param indice indice de la ligne voulu
     * @return un String representant une ligne du tableau
     */
    public String getligne(int indice) {
        String res = " ";
        for (int i = 0; i < getModel().getValeurs().size(); i++) {
            if (getModel().getValeurs().get(indice).get(i) != getModel().getVide()) {
                res += (getModel().getValeurs().get(indice).get(i)).toString() + " ";
            } else {
                if (i == 0) {
                    res += (getModel().getValeurs().get(indice).get(i + 1)).toString() + " ";
                } else {
                    res += (getModel().getValeurs().get(indice).get(i - 1)).toString() + " ";
                }
            }
        }
        return res;
    }

    /**
     * Créer un affichage correct pour la fenetre
     */
    public void setimagePiece() {
        ArrayList<ArrayList<Terrain>> valeurs = getModel().getValeurs();
        try {
            image = ImageIO.read(new File("src/carcassonne/Images/Screenshot_" + id + ".png"));
        } catch (IOException ex) {
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void rotateDroit() {
        BufferedImage buffered = new BufferedImage(image.getHeight(), image.getWidth(),
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                buffered.setRGB(image.getHeight() - y - 1, x,
                        image.getRGB(x, y));

            }
        }
        image = buffered;
        paintComponent(getGraphics());
    }

    public void rotateGauche() {
        BufferedImage buffered = new BufferedImage(image.getHeight(), image.getWidth(),
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                buffered.setRGB(y, x, image.getRGB(x, y));
            }
        }
        image = buffered;
        paintComponent(getGraphics());
    }

    public void pivotDroit() {
        rotateDroit();
        revalidate();
    }

    public void pivotGauche() {
        rotateDroit();
        revalidate();
    }

    /**
     * retourne un String représentant une pièce.
     * C'est a dire un String composé de plusieurs lignes avec tout les valeur de a
     * pièce. Et des espace la ou il faut.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < getModel().getValeurs().size(); i++) {
            res += getligne(i) + "\n";
        }
        return res;
    }
}
