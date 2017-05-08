package polynomialtoolbox.gui;

import polynomialtoolbox.polynomial.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 * Created by Jacob on 2015-11-12.
 */
public class Graph extends JPanel {

    //FEILDS

    //For rounding doubles
    private DecimalFormat df = new DecimalFormat("#.###");

    //Mouse listeners
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private MouseWheelListener mouseWheelListener;

    //Last frame of the graph
    private BufferedImage lastImage;

    //Mouse references
    private boolean mouseInside;
    private int mouseX, mouseY;
    private int lastMouseClickX, lastMouseClickY;

    //Graph properties
    private double xCenter = 0, yCenter = 0;
    private double scale = 3;
    private int size;

    //Funtion to graph
    private Function function = null;

    public Graph() {

        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                //Set the references
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

                //Get the amount the mouse moved since last call
                double xChange = getPlanePointX(e.getX()) - getPlanePointX(lastMouseClickX);
                double yChange = getPlanePointY(e.getY()) - getPlanePointY(lastMouseClickY);

                //Reset the references
                lastMouseClickX = e.getX();
                lastMouseClickY = e.getY();

                //Move the center of the graph based on the change
                xCenter -= xChange;
                yCenter -= yChange;

                update();

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (mouseInside) {
                    //Update references
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
                //Zoom in and out with scroll wheel
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

        //Initialize the image
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        //Add rendering hints
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        //Set the size of the graph so scale is constant on both axes
        size = Math.min(getWidth(), getHeight());

        //Draw main axis lines
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.BLACK);
        if (this.contains(getPixelPointX(0), 0)) {
            g.drawLine(getPixelPointX(0), 0, getPixelPointX(0), getHeight());
        }
        if (this.contains(0, getPixelPointY(0))) {
            g.drawLine(0, getPixelPointY(0), getWidth(), getPixelPointY(0));
        }
        //Draw minor axes lines
        g.setStroke(new BasicStroke(0.5f));
        g.setColor(Color.DARK_GRAY);
        for (double i = getPlanePointX(0)-Math.IEEEremainder(getPlanePointX(0), getAppropriateLabelInterval()); getPixelPointX(i) < getWidth(); i += getAppropriateLabelInterval()) {
            g.drawLine(getPixelPointX(i), 0, getPixelPointX(i), getHeight());
            //Add labels (PARTIALLY WORKING)
            if (this.contains(getPixelPointY(0), 0)) {
                g.drawString(df.format(i), getPixelPointX(i), getPixelPointY(0));
            } else {
                double y = Math.min(Math.abs(getPlanePointY(0)), Math.abs(getPlanePointY(getHeight())));
                g.drawString(df.format(i), getPixelPointX(i), getPixelPointY(y));
            }
        }
        for (double i = getPlanePointY(0)-Math.IEEEremainder(getPlanePointY(0), getAppropriateLabelInterval()); getPixelPointY(i) < getHeight(); i -= getAppropriateLabelInterval()) {
            g.drawLine(0, getPixelPointY(i), getWidth(), getPixelPointY(i));
            //Add labels (PARTIALLY WORKING)
            if (this.contains(getPixelPointX(0), 0)) {
                g.drawString(df.format(i), getPixelPointX(0), getPixelPointY(i));
            } else {
                double x = Math.min(Math.abs(getPlanePointX(0)), Math.abs(getPlanePointX(getWidth())));
                g.drawString(df.format(i), getPixelPointX(x), getPixelPointY(i));
            }
        }

        g.setStroke(new BasicStroke(2));

        if (function != null) {

            //Get polynomial
            Polynomial p = function.getPolynomial();

            double lastY = 0;

            //Graph every point individually
            for (int Px = 0; Px < getWidth(); Px++) {

                double x = getPlanePointX(Px);

                double y = p.evaluateAt(x);
                int Py = getPixelPointY(y);

                if (this.contains(Px, Py)) {
                    g.setColor(function.getColor());
                    g.fillRect(Px, Py, 1, 1);
                    if (Px != 0) {
                        //Connect last two points with line
                        g.drawLine(Px-1, getPixelPointY(lastY), Px, Py);
                    }
                    if (Px == mouseX && Math.abs(Py-mouseY) < 20) {

                        Polynomial tangent = p.getTangentAt(x);

                        g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[] {10}, 0));
                        g.drawLine(0, getPixelPointY(tangent.evaluateAt(getPlanePointX(0))), getWidth(), getPixelPointY(tangent.evaluateAt(getPlanePointX(getWidth()))));
                        g.setStroke(new BasicStroke(2));
                        g.fillOval(Px-5, Py-5, 10, 10);

                    }
                }

                lastY = y;

            }

        }

        lastImage = image;

        //Put image on screen
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(lastImage, 0, 0, null);
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    private double getAppropriateLabelInterval() {

        //Find label interval
        double numTicks = size/50;
        double exactGap = scale/numTicks;
        double gap = Math.pow(2, Math.round(Math.log10(exactGap)/Math.log10(2)));

        return gap;

    }

    private double getPlanePointX(int Px) {

        return ((Px-(getWidth()/2)) * (scale/size)) + xCenter;

    }

    private double getPlanePointY(int Py) {

        return (((-Py+(getHeight()/2))) * (scale/size)) + yCenter;

    }

    private int getPixelPointX(double x) {

        return (int)(((x-xCenter) * (size/scale)) + (getWidth()/2));

    }

    private int getPixelPointY(double y) {

        return (int)(((y-yCenter) * (size/scale)) - ((getHeight()/2)))*-1;

    }

}
