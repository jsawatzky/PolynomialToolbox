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
        
        Term[] fullPoly = new Term[exp+1]; //Array to be fully filled with terms
        int [] comparer = new int[exp+1]; //Array of terms with the length of the polynomial degree
        int n = exp;
        int counter = 0;
        while (n != 0) {
            comparer[counter] = n;
            n = n - 1;
            counter++;
        }
        
        for (int i = 0; i < this.getTerms().length; i++) {
            for (int j = 0; j < exp + 1; j++) {
                if (comparer[j] == )
            }
         }
        
        return 
    }
    
    public Polynomial add(Polynomial other) {
        Term[] terms1 = new Term[this.getTerms().length]; //First polynomial to be added
        Term[] terms2 = new Term[other.getTerms().length]; //Second polynomial to be added
      
        int numTerms = this.getTerms().length;
        int numTerms2 = other.getTerms().length;
        
        Term[] newTerms = new Term[numTerms + numTerms2]; //New polynomial array that will put terms together
        
        for (int i = 0; i < numTerms; i++) {
            newTerms[i] = terms1[i];
        }
        
        for (int j = 0; j < numTerms2; j++) {
            newTerms[j] = terms2[j];
        }
        
        return new Polynomial(newTerms);
    }
}
