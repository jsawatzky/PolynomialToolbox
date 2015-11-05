package softwaredesign973.polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Term extends PolynomialComponent {
    
    public boolean valid = true;
    
    private Coefficient coefficient;
    private Variable variable;
    
    public Term(String t) {
        
        Pattern p = Pattern.compile("\\p{L}");
        Matcher m = p.matcher(t);
        if (m.find()) {
            coefficient = new Coefficient(t.substring(0, m.start()));
            variable = new Variable(t.substring(m.start()));
            
            valid = coefficient.valid && variable.valid;
            
        } else {
            if (t.contains("^")) {
                String[] t2 = t.split("^");
                coefficient = new Coefficient(t2[0]);
                try {
                    int e = Integer.parseInt(t2[1].replace("(", "").replace(")", ""));
                    coefficient.toExponent(e);
                } catch (NumberFormatException ex) {
                    valid = false;
                    return;
                }
            } else {
                coefficient = new Coefficient(t);
            }
            variable = null;
            
            valid = coefficient.valid;
            
        }
        
    }

    @Override
    public void add(PolynomialComponent c) {

    }

    @Override
    public void toExponent(int e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Coefficient getCoefficient() {
        return coefficient;
    }

    public Variable getVariable() {
        return variable;
    }
}
