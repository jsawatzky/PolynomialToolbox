package softwaredesign973.polynomial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Polynomial {
    
    private ArrayList<Term> terms = new ArrayList<>();
    
    public Polynomial(Term[] terms) {
        
        this.terms.addAll(Arrays.asList(terms));
        simplify();
        sort();
        
    }

    private Polynomial(ArrayList<Term> terms) {

        this.terms = terms;
        simplify();
        sort();

    }

    public Term[] getTerms() {
        return (Term[]) terms.toArray();
    }
    
    public Polynomial getFullPolynomial() {

        Term[] newTerms = new Term[terms.get(0).getExponent()];

        int index = 0;
        for (int i = 0; i < newTerms.length; i++) {

            int t1Exp = terms.get(index).getExponent();

            if (t1Exp < newTerms.length-i-1) {
                newTerms[i] = new Term(0, newTerms.length-i-1);
            } else {
                newTerms[i] = terms.get(index);
                index++;
            }

        }

        return new Polynomial(newTerms);

    }

    public Polynomial getFullPolynomial(int degree) {

        Term[] newTerms = new Term[degree];

        int index = 0;
        for (int i = 0; i < degree; i++) {

            int t1Exp = terms.get(index).getExponent();

            if (t1Exp < degree-i-1) {
                newTerms[i] = new Term(0, degree-i-1);
            } else {
                newTerms[i] = terms.get(index);
                index++;
            }

        }

        return new Polynomial(newTerms);

    }
    
    public void add(Polynomial other) {

        this.terms.addAll(other.terms);
        simplify();
        sort();

    }

    public static Polynomial add(Polynomial p1, Polynomial p2) {

        ArrayList<Term> newTerms = new ArrayList<>();

        newTerms.addAll(p1.terms);
        newTerms.addAll(p2.terms);

        Polynomial newPolynomial = new Polynomial(newTerms);

        return newPolynomial;

    }
    
<<<<<<< HEAD
    public Polynomial getDerivative() {
        Term[] initialPolynomial = getTerms();
        ArrayList<Term> newTerms = new ArrayList<>();
        
        for (int i = 0; i < initialPolynomial.length; i++) {
            double newCoef = initialPolynomial[i].getExponent() * initialPolynomial[i].getCoefficient();
            int newExp = initialPolynomial[i].getExponent() - 1;
            Term a = new Term (newCoef,newExp);
            newTerms.add(a);
        }
        return new Polynomial(newTerms);
=======
    public void subtract(Polynomial other) {

        other.multiply(new Polynomial(new Term[] {new Term(-1, 0)}));
        this.terms.addAll(other.terms);
        simplify();
        sort();

    }
    
    public static Polynomial subtract(Polynomial p1, Polynomial p2) {

        ArrayList<Term> newTerms = new ArrayList<>();

        newTerms.addAll(p1.terms);
        Polynomial p2New = multiply(p2, new Polynomial(new Term[] {new Term(-1, 0)}));
        newTerms.addAll(p2New.terms);

        Polynomial newPolynomial = new Polynomial(newTerms);

        return newPolynomial;

    }
    
    public void multiply(Polynomial other) {
        
        ArrayList<Term> newTerms = new ArrayList<>();

        for (Term t1: this.terms) {
            for (Term t2: other.terms) {
                newTerms.add(new Term(t1.getCoefficient()*t2.getCoefficient(), t1.getExponent()+t2.getExponent()));
            }
        }
        
        terms = newTerms;
        simplify();
        sort();

    }
    
    public static Polynomial multiply(Polynomial p1, Polynomial p2) {

        ArrayList<Term> newTerms = new ArrayList<>();

        for (Term t1: p1.terms) {
            for (Term t2: p2.terms) {
                newTerms.add(new Term(t1.getCoefficient()*t2.getCoefficient(), t1.getExponent()+t2.getExponent()));
            }
        }

        Polynomial newPolynomial = new Polynomial(newTerms);

        return newPolynomial;

>>>>>>> origin/master
    }

    private void simplify() {

        ArrayList<Term> newTerms = new ArrayList<>();

        for (int i = 0; i < terms.size(); i++) {

            Term t1 = terms.get(i);
            double coef = t1.getCoefficient();
            int exp = t1.getExponent();

            if (coef == 0 && exp == 0) {
                continue;
            }

            for (int j = i+1; j < terms.size(); j++) {

                Term t2 = terms.get(j);

                if (t2.getExponent() == exp) {

                    coef += t2.getCoefficient();
                    terms.set(j, new Term(0, 0));

                }

            }

            newTerms.add(new Term(coef, exp));

        }

        terms = newTerms;
        sort();

    }

    private void sort() {

        Collections.sort(terms);
        Collections.reverse(terms);

    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Term t: terms) {

            if (t.getCoefficient() < 0) {
                stringBuilder.append(" - ");
            } else {
                stringBuilder.append(" + ");
            }

            if (t.getCoefficient() != 0) {

                stringBuilder.append(Math.abs(t.getCoefficient()));
                if (t.getExponent() > 0) {
                    stringBuilder.append("x");
                    if (t.getExponent() > 1) {
                        stringBuilder.append("^");
                        stringBuilder.append(t.getExponent());
                    }
                }

            }

        }

        if (stringBuilder.indexOf("+") == 1) {
            stringBuilder.delete(0, 2);
        } else {
            stringBuilder.delete(0, 1);
        }

        return stringBuilder.toString();

    }
}
