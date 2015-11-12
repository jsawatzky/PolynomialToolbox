package softwaredesign973.gui;

import softwaredesign973.polynomial.Polynomial;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sawaj6311 on 12/11/2015.
 */
public class Function {

    private String name;
    private Polynomial p;
    private Color color;

    public Function(String name, Polynomial p, Color color) {

        this.name = name;
        this.p = p;
        this.color = color;

    }

    @Override
    public String toString() {
        return name + "(x) = " + p;
    }
}
