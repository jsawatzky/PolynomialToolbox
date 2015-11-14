package softwaredesign973.gui;

import softwaredesign973.polynomial.Polynomial;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sawaj6311 on 12/11/2015.
 */
public class Function {

    //FEILDS
    private String name;
    private Polynomial p;
    private Color color;

    //CONSTRUCTOR
    public Function(String name, Polynomial p, Color color) {

        this.name = name;
        this.p = p;
        this.color = color;

    }

    //Returns color of the function
    public Color getColor() {
        return color;
    }

    //Sets the color of the function (For use in unimplemented feature)
    public void setColor(Color color) {
        this.color = color;
    }

    //Gets the name of the function (For use in unimplemented feature)
    public String getName() {
        return name;
    }

    //Sets the name of the function (For use in unimplemented feature)
    public void setName(String name) {
        this.name = name;
    }

    //Gets the polynomial the function is based on
    public Polynomial getPolynomial() {
        return p;
    }

    //Returns the proper text format of the function
    @Override
    public String toString() {
        return name + "(x) = " + p;
    }
}
