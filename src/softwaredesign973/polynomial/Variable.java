package softwaredesign973.polynomial;

public class Variable extends PolynomialComponent {
    
    public boolean valid = true;
    
    private String symbol;
    private int exponent;
    
    public Variable(String v) {
        
        if (v.contains("^")) {
            
            String[] v2 = v.split("^");
            
            symbol = v2[0];
            
            try {
                exponent = Integer.parseInt(v2[1].replace("(", "").replace(")", ""));
            } catch (NumberFormatException e) {
                valid = false;
            }
        } else {

            symbol = v;

            exponent = 1;

        }
        
    }

    @Override
    public void add(PolynomialComponent c) {



    }

    @Override
    public void toExponent(int e) {
    
        exponent *= e;
    
    }

    public int getExponent() {
        return exponent;
    }

    public String getSymbol() {
        return symbol;
    }
}
