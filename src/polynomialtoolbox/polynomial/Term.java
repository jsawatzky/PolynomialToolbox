package polynomialtoolbox.polynomial;

public class Term implements Comparable {
    
    private double coefficient;
    private int exponent;
    
    public Term(double coefficient, int exponent) {
        
        this.coefficient = coefficient;
        this.exponent = exponent;
        
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public int compareTo(Object o) {

        Term term1 = (Term) o;

        if (this.getExponent() > term1.getExponent()) {
            return 1;
        } else if (this.getExponent() < term1.getExponent()) {
            return -1;
        } else {
            return 0;
        }

    }
}
