package softwaredesign973.polynomial;

public class Coefficient extends PolynomialComponent {
    
    public boolean valid = true;
    
    private int value;
    
    public Coefficient(String c) {
        
        try {
            value = Integer.parseInt(c);
        } catch (NumberFormatException e) {
            valid = false;
        }
        
    }

    @Override
    public void add(PolynomialComponent c) {

    }

    @Override
    public void toExponent(int e) {
        
        value = (int) Math.pow(value, e);
    
    }

    public int getValue() {
        return value;
    }
}
