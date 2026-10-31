package hospitalmanagementsystem.GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

// A panel that draws the hospital logo (Star of Life with a rod and snake) with code, so no image file is needed
public class LogoPanel extends JPanel {

    // Creates a transparent square panel of the given size
    public LogoPanel(int size) {
        setPreferredSize(new Dimension(size, size));
        setOpaque(false);
    }

    // Draws the logo whenever Swing paints this panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        drawLogo(g2, Math.min(getWidth(), getHeight()));
        g2.dispose();
    }

    // Draws the star, its white inner outline, the staff and the snake
    private static void drawLogo(Graphics2D g2, int size) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        Color green = new Color(0, 222, 142);
        double c = size / 2.0;       // center of the logo
        double len = size * 0.96;    // length of each star arm (tip to tip)
        double w = len * 0.30;       // width of each star arm

        // Builds the six-pointed star by merging three bars rotated 0, 60 and 120 degrees
        Area star = new Area();
        for (int i = 0; i < 3; i++) {
            AffineTransform t = new AffineTransform();
            t.translate(c, c);
            t.rotate(Math.toRadians(60 * i));
            star.add(new Area(t.createTransformedShape(new Rectangle2D.Double(-w / 2, -len / 2, w, len))));
        }
        g2.setColor(green);
        g2.fill(star);

        // Draws the thin white outline inside the star by shrinking a copy of it toward the center
        AffineTransform shrink = AffineTransform.getTranslateInstance(c, c);
        shrink.scale(0.88, 0.88);
        shrink.translate(-c, -c);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke((float) (size * 0.018), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(shrink.createTransformedShape(star));

        // Draws the vertical staff
        g2.setStroke(new BasicStroke((float) (size * 0.03), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(new Line2D.Double(c, c - len * 0.34, c, c + len * 0.34));

        // Snake path in units of the star length, relative to the center (head at the top, tail at the bottom)
        Path2D snake = new Path2D.Double();
        snake.moveTo(0.07, -0.28);
        snake.curveTo(-0.05, -0.31, -0.10, -0.21, 0.00, -0.16);
        snake.curveTo(0.10, -0.11, 0.10, -0.04, 0.00, -0.01);
        snake.curveTo(-0.10, 0.03, -0.10, 0.10, 0.00, 0.14);
        snake.curveTo(0.08, 0.17, 0.08, 0.23, 0.00, 0.26);

        // Scales and moves the snake path to the logo's size and center
        AffineTransform place = AffineTransform.getTranslateInstance(c, c);
        place.scale(len, len);
        Shape snakeShape = place.createTransformedShape(snake);

        // Draws a green border under the snake so it looks like it wraps around the white staff
        g2.setColor(green);
        g2.setStroke(new BasicStroke((float) (size * 0.085), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(snakeShape);

        // Draws the white snake itself
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke((float) (size * 0.045), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(snakeShape);
    }

    // Draws the same logo into an image, used for the window's title bar icon
    public static Image getLogoImage(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        drawLogo(g2, size);
        g2.dispose();
        return image;
    }
}