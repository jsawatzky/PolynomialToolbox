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

    private BufferedImage lastImage;

    private boolean mouseInside;
    private int mouseX, mouseY;
    private int lastMouseClickX, lastMouseClickY;

    private double xCenter = 0, yCenter = 0;
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

                double xChange = getPlanePointX(e.getX()) - getPlanePointX(lastMouseClickX);
                double yChange = getPlanePointY(e.getY()) - getPlanePointY(lastMouseClickY);

                lastMouseClickX = e.getX();
                lastMouseClickY = e.getY();

                xCenter -= xChange;
                yCenter -= yChange;

                update();

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (mouseInside) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    update();
                }
            }
        };
        this.addMouseMotionListener(mouseMotionListener);
        mouseWheelListener = new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    scale /= 1.5;
                } else {
                    scale *= 1.5;
                }
                update();
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

//        g.setTransform(transform);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        size = Math.min(getWidth(), getHeight());

        g.setStroke(new BasicStroke(6));
        g.setColor(Color.BLACK);
        g.fillRect(getPixelPointX(0), 0, 2, getHeight());
        g.fillRect(0, getPixelPointY(0), getWidth(), 2);
        g.setStroke(new BasicStroke(2));

        for (Function f: functions) {

            Polynomial p = f.getPolynomial();

            double lastY = 0;

            for (int Px = 0; Px < getWidth(); Px++) {

                double x = getPlanePointX(Px);

                double y = p.evaluateAt(x);
                int Py = getPixelPointY(y);

                if (this.contains(Px, Py)) {
                    g.setColor(f.getColor());
                    g.fillRect(Px, Py, 1, 1);
                    if (Px != 0) {
                        g.drawLine(Px-1, getPixelPointY(lastY), Px, Py);
                    }
                    if (Px == mouseX && Math.abs(Py-mouseY) < 5) {

                        Polynomial tangent = p.getTangentAt(x);

                        g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[] {10}, 0));
                        g.drawLine(0, getPixelPointY(tangent.evaluateAt(getPlanePointX(0))), getWidth(), getPixelPointY(tangent.evaluateAt(getPlanePointX(getWidth()))));
                        g.setStroke(new BasicStroke(2));

                    }
                }

                lastY = y;

            }

        }

        lastImage = image;

        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(lastImage, 0, 0, null);
    }

    public void setFunctions(ArrayList<Function> functions) {
        this.functions = functions;
    }

    private double getAppropriateLabelInterval() {

        return 0.0;

    }

    private double getPlanePointX(int Px) {

        return ((Px-(getWidth()/2)) * (scale/size)) + xCenter;

    }

    private double getPlanePointY(int Py) {

        return (((Py-(getHeight()/2))*-1) * (scale/size)) + yCenter;

    }

    private int getPixelPointX(double x) {

        return (int)(((x-xCenter) * (size/scale)) + (getWidth()/2));

    }

    private int getPixelPointY(double y) {

        return (int)(((y-yCenter) * (size/scale)) + ((getHeight()/2)))*-1;

    }

}
