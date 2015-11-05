package softwaredesign973;

import java.util.Scanner;
import softwaredesign973.polynomial.Expression;

/**
 * @author Jacob Sawatzky
 * @author David Park
 * @author Julian Li
 */
public class SoftwareDesign973 {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter a polynomial: ");
        
        String eq = in.next();
        
        Expression ex = new Expression(eq);
        System.out.println(ex.valid);
        
    }
}
