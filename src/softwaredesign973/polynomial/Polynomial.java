package softwaredesign973.polynomial;

import java.util.ArrayList;

public class Polynomial {
    
    private ArrayList<Term> terms = new ArrayList<>();
    
    public Polynomial() {
        

        
    }

    public Term[] getTerms() {
        return (Term[]) terms.toArray();
    }
    
}
