package softwaredesign973.polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Term implements PolynomialCompenent {
    
    private Coefficient coefficient;
    private Variable variable;
    
    public Term(String t) {
        
        Pattern p = Pattern.compile("\\p{L}");
        Matcher m = p.matcher(t);
        if (m.find()) {
            coefficient = new Coefficient(t.substring(0, m.start()));
            variable = new Variable(t.substring(m.start()));
        } else {
            if (t.contains("^")) {
                String[] t2 = t.split("^");
                coefficient = new Coefficient(t2[0]);
                Exponent e = new Exponent(t2[1]);
                if (e.valid) {
                    coefficient.toExponent(e);
                }
            } else {
                coefficient = new Coefficient(t);
            }
            variable = null;
        }
        
    }
    
}
