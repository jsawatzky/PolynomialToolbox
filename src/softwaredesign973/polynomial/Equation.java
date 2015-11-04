package softwaredesign973.polynomial;

public class Equation implements PolynomialCompenent {
    
    public boolean valid = true;;
    
    private Expression leftSide;
    private Expression rightSide;
    
    public Equation(String eq) {
        
        eq = eq.replace(" ", "");
        
        String[] ex = eq.split("=");
        
        leftSide = new Expression(ex[0]);
        rightSide = new Expression(ex[1]);
        
    }
    
}
