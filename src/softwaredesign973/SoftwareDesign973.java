package softwaredesign973;

import softwaredesign973.gui.MainGUI;
import softwaredesign973.gui.PolynomialInput;
import softwaredesign973.polynomial.Polynomial;
import softwaredesign973.polynomial.Term;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author Jacob Sawatzky
 * @author David Park
 * @author Julian Li
 */
public class SoftwareDesign973 {

    public static void main(String[] args) {

        Polynomial p1 = new Polynomial(new Term[] {new Term(3, 2), new Term(2, 1), new Term(1, 0)});
        System.out.println("The first polynomial is: " + p1);
        Polynomial p2 = new Polynomial(new Term[] {new Term(5, 3), new Term(3, 1), new Term(2, 0)});
        System.out.println("The second polynomial is: " + p2);

        Polynomial sum = Polynomial.add(p1, p2);
        System.out.println("The sum of them is: " + sum);
        Polynomial dif = Polynomial.subtract(p1, p2);
        System.out.println("The difference of them is: " + dif);
        Polynomial prod = Polynomial.multiply(p1, p2);
        System.out.println("The product of them is: " + prod);
        
        Polynomial der = p1.getDerivative();
        System.out.println("The derivative of p1 is: " + der);
        System.out.println(p2.posEndBehaviour());
        System.out.println(p2.negEndBehaviour());
        
        System.out.println(p2.yIntercept());
        System.out.println(p2.xIntercept().toString());
        
    }
}
