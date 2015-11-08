package softwaredesign973.polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Term {
    
    public boolean valid = true;
    
    private int coefficient;
    private int exponent;
    
    public Term(int coefficient, int exponent) {
        
        this.coefficient = coefficient;
        this.exponent = exponent;
        
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }
}
