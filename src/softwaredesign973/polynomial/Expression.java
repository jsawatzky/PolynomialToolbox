package softwaredesign973.polynomial;

import java.util.ArrayList;

public class Expression extends PolynomialComponent {
    
    public boolean valid = true;
    
    private ArrayList<Term> terms = new ArrayList<>();
    
    public Expression(String e) {
        
        if (!e.startsWith("+") && !e.startsWith("-")) {
            e = "+" + e;
        }
        
        for (int i = 0; i < e.length() - e.replace("+", "").replace("-", "").length() - 1; i++) {
            
            int index1 = e.substring(1).indexOf("+");
            int index2 = e.substring(1).indexOf("-");

            int index;
            if (index1 < index2 && index1 != -1) {
                index = index1;
            } else {
                index = index2;
            }
            
            terms.add(new Term(e.substring(0, index)));
            
            e = e.substring(index);
            
        }
        
        for (Term t: terms) {
            if (!t.valid) {
                valid = false;
                break;
            }
        }
        
    }

    @Override
    public void add(PolynomialComponent c) {

        if (c instanceof Expression) {

            terms.addAll(((Expression) c).terms);

        }

    }

    @Override
    public void toExponent(int e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Term[] getTerms() {
        return (Term[]) terms.toArray();
    }
    
}
