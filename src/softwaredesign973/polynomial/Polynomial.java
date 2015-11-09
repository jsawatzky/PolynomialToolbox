package softwaredesign973.polynomial;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial {
    
    private ArrayList<Term> terms = new ArrayList<>();
    
    public Polynomial(Term[] terms) {
        
        this.terms.addAll(Arrays.asList(terms));
        
    }

    public Term[] getTerms() {
        return (Term[]) terms.toArray();
    }
    
    public Polynomial getFullPoly() {
        int exp = this.terms.get(0).getExponent();
        
        Term[] fullPoly = new Term[exp+1];
        int [] sample = new int[exp+1];
        int n = exp;
        int counter = 0;
        while (n != 0) {
            sample[counter] = n;
            n = n - 1;
            counter++;
        }
        
        for (int i = 0; i < this.getTerms().length; i++) {
            for (int j = 0; j < exp + 1; j++) {
                if (sample[j] == )
            }
         }
        
        return 
    }
    
    public Polynomial add(Polynomial other) {
        Term[] terms1 = new Term[this.getTerms().length];
        Term[] terms2 = new Term[other.getTerms().length];
      
        int numTerms = this.getTerms().length + other.getTerms().length;
        Term[] newTerms = new Term[numTerms];
        
        for (int i = 0; i < numTerms; i += 2) {
            newTerms[i] = terms1[i];
            newTerms[i+1] = terms2[i];
        }
        
        return new Polynomial(newTerms);
    }
}
