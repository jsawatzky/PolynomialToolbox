package softwaredesign973.gui;

import softwaredesign973.polynomial.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Jacob on 2015-11-12.
 */
public class Graph extends JPanel {

    private AffineTransform transform;

    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private MouseWheelListener mouseWheelListener;

    private boolean mouseInside;
    private int mouseX, mouseY;
    private int lastMouseClickX, lastMouseClickY;

    private double xCenter = 0, yCenter = 0;
    private int xOffset, yOffset;
    private double scale = 3;
    private int size;

    private ArrayList<Function> functions = new ArrayList<>();

    public Graph() {

        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                lastMouseClickX = e.getX();
                lastMouseClickY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                update();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseInside = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseInside = false;
            }
        };
        this.addMouseListener(mouseListener);
        mouseMotionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                int newX = e.getX() - lastMouseClickX;
                int newY = e.getY() - lastMouseClickY;

                lastMouseClickX += newX;
                lastMouseClickY += newY;

                xCenter += newX;
                yCenter += newY;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (mouseInside) {
                    update();
                }
            }
        };
        this.addMouseMotionListener(mouseMotionListener);
        mouseWheelListener = new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    scale /= 2;
                } else {
                    scale *= 2;
                }
                System.out.println(scale);
            }
        };
        this.addMouseWheelListener(mouseWheelListener);


    }

    public void update() {

//        if (transform == null) {
//            transform = new AffineTransform();
//            transform.translate(getWidth()/2, getHeight()/2);
//            transform.scale(1, 1);
//        }

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setStroke(new BasicStroke(6));

//        g.setTransform(transform);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        size = Math.min(getWidth(), getHeight());
        xOffset = (getWidth() - size) / 2;
        yOffset = (getHeight() - size) / 2;

        g.setColor(Color.BLACK);
        g.fillRect(getPixelPointX(0), 0, 2, getHeight());
        g.fillRect(0, getPixelPointY(0), getWidth(), 2);

        for (int Px = 0; Px < getWidth(); Px++) {

            double x = getPlanePointX(Px);

            for (Function f: functions) {

                Polynomial p = f.getPolynomial();

                double y = p.evaluateAt(x)*-1;
                int Py = getPixelPointY(y);

                if (this.contains(Px, Py)) {
                    g.setColor(f.getColor());
                    g.fillRect(Px, Py, 1, 1);
                }

            }

        }

        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();


    }

    @Override
    public void paint(Graphics g) {
        update();
    }

    public void setFunctions(ArrayList<Function> functions) {
        this.functions = functions;
    }

    private double getAppropriateLabelInterval() {

        return 0.0;

    }

    private double getPlanePointX(int Px) {

        return (((Px-xOffset) - (size/2)) / (size/scale)) + xCenter;

    }

    private double getPlanePointY(int Py) {

        return (((Py-yOffset) - (size/2)) / (size/scale)) + yCenter;

    }

    private int getPixelPointX(double x) {

        return (int)((x - xCenter) * (size/scale) + (size/2) + xOffset);

    }

    private int getPixelPointY(double y) {

        return (int)((y - yCenter) * (size/scale) + (size/2) + yOffset);

    }

}
