package softwaredesign973.polynomial;

import java.util.ArrayList;

public class Expression implements PolynomialCompenent {
    
    private ArrayList<Term> terms = new ArrayList<>();
    
    public Expression(String e) {
        
        if (!e.startsWith("+") && !e.startsWith("-")) {
            e = "+" + e;
        }
        
        
        
    }
    
}
