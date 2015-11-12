package softwaredesign973.polynomial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    
    //Finding the derivative of the Polynomial
    public Polynomial getDerivative() {
        
        ArrayList<Term> newTerms = new ArrayList<>(); //New List of Terms
        
        for (int i = 0; i < terms.size(); i++) {
            double newCoef = terms.get(i).getExponent() * terms.get(i).getCoefficient(); //Finding the new Coefficient using the Power Rule
            int newExp = terms.get(i).getExponent() - 1; //Continuing Power Rule
            Term a = new Term (newCoef,newExp); //Building a new term with the new Coef and Exp
            newTerms.add(a); //Adding the new Term to the array of temrms
        }
        
        return new Polynomial(newTerms); //Return the list as a Polynomial
        
    }
    
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

    }
    
    //Finding the Positive End Behaviour of the polynomial
    public String posEndBehaviour () {
        double posEnd = 0; //Set originial value
        for (int i = 0; i < terms.size(); i++) {
            posEnd = posEnd + (terms.get(i).getCoefficient() * Math.pow(1000, terms.get(i).getExponent())); //Check y when x = 1000
        }
        
        if ((posEnd >= 10000)) { //If the value is greater than 10000, assume that it's heading toward positive infinity
            return "+ infinity";
        }
        else if ((posEnd <= -10000)) { //If the value is greater than 10000, assume that it's heading toward negative infinity
            return "- infinity"; 
        }
        
        else { //Unless the numbers are approaching either infinities, it is heading toward a number
            posEnd = Math.round(posEnd); //Round to find that number
            String posEndBehaviour = String.valueOf(posEnd); //Make it into a string
            return posEndBehaviour;
        }
     }
    
    
    //Finding the Negative End Behaviour of the polynomial
    public String negEndBehaviour () {
        double negEnd = 0;
        for (int i = 0; i < terms.size(); i++) {
            negEnd = negEnd + (terms.get(i).getCoefficient() * Math.pow(-1000, terms.get(i).getExponent())); //Check y when x = -1000
        }
        
        if ((negEnd >= 10000)) { //If the value is greater than 10000, assume that it's heading toward positive infinity
            return "+ infinity";
        }
        else if ((negEnd <= -10000)) {//If the value is greater than 10000, assume that it's heading toward negative infinity
            return "- infinity";
        }
        
        else {
            negEnd = Math.round(negEnd);
            String negEndBehaviour = String.valueOf(negEnd);
            return negEndBehaviour;
        }
     }
    
    public double yIntercept() {
        double yInt = 0;
        for (int i = terms.size()-1; i < terms.size(); i++) {
            yInt = yInt + (terms.get(i).getCoefficient() * Math.pow(0, terms.get(i).getExponent()));
        }
        return yInt;
    }
    
    
        public ArrayList xIntercept() {
        ArrayList<Double> xInt = new ArrayList<>();
        for (double i = terms.size()-1; i < terms.size(); i++) {
            xInt = yInt + (terms.get(i).getCoefficient() * Math.pow(0, terms.get(i).getExponent()));
        }
        return yInt;
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
            } else if (t.getCoefficient() != 0) {
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
