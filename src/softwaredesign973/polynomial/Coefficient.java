package softwaredesign973.polynomial;

public class Coefficient implements PolynomialCompenent {
    
    public boolean valid = true;
    
    private int value;
    
    public Coefficient(String c) {
        
        try {
            value = Integer.parseInt(c);
        } catch (NumberFormatException e) {
            valid = false;
        }
        
    }
    
}
