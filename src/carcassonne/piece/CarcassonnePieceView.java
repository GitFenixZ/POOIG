package carcassonne.piece;

import communs.objets.piece.PieceView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import carcassonne.Partisan;

import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class CarcassonnePieceView extends PieceView<Terrain> {

    /**
     * numero permettant d'identifier l'image
     */
    int id;
    /**
     * image qui doit être affiche sur le JPanel
     */
    BufferedImage image;

    /**
     * Contructeur permettant d'initialiser la vue de la piece
     * 
     * @param i numero permettant d'identifier l'image.
     */
    public CarcassonnePieceView(int i) {
        super();
        id = i;
        try {
            image = ImageIO.read(new File("src/carcassonne/images/Screenshot_" + id + ".png"));
        } catch (IOException ex) {
        }
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        ArrayList<ArrayList<Partisan>> piece = ((CarcassonnePieceModel) getModel()).getPartisan();
        for (int i = 0; i < piece.size(); i++) {
            for (int j = 0; j < piece.get(0).size(); j++) {
                if (piece.get(i).get(j) != null) {
                    g.setColor(piece.get(i).get(j).getCouleur());
                    g.fillRect(j * getWidth() / piece.get(j).size(), i * getHeight() / piece.size(), 10, 10);
                }
            }
        }
    }

    /**
     * Methode permettant de faire rotate une image.
     * 
     * @param angle angle que l'on souhaite tourner.
     * @return
     */
    private BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, this);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();

        return rotated;
    }

    /**
     * Tourne l'image de la pièce dans le sent horaire à 90°
     */
    public void tournerDroite() {
        BufferedImage buffered = rotateImageByDegrees(image, 90);
        image = buffered;
        paintComponent(getGraphics());
        revalidate();
    }

    /**
     * Tourne l'image de la pièce dans le sens trigonométrique à 90°
     */
    public void tournerGauche() {
        BufferedImage buffered = rotateImageByDegrees(image, -90);
        image = buffered;
        paintComponent(getGraphics());
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
