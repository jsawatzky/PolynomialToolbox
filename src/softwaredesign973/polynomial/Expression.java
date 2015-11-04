package softwaredesign973.polynomial;

import java.util.ArrayList;

public class Expression implements PolynomialCompenent {
    
    public boolean valid = true;
    
    private ArrayList<Term> terms = new ArrayList<>();
    
    public Expression(String e) {
        
        if (!e.startsWith("+") && !e.startsWith("-")) {
            e = "+" + e;
        }
        
        for (int i = 0; i < e.length() - e.replace("+", "").replace("-", "").length(); i++) {
            
            int index = Math.min(e.substring(1).index("+"), e.substring(1).index("-"));
            
            terms.append(new Term(e.substring(0, index)));
            
            e = e.substring(index);
            
        }
        
        for (Term t: terms) {
            if (!t.valid) {
                valid = false;
                break;
            }
        }
        
    }
    
}
