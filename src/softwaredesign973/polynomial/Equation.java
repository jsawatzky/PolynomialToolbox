package softwaredesign973.polynomial;

public class Equation extends PolynomialComponent {
    
    public boolean valid = true;;
    
    private Expression leftSide;
    private Expression rightSide;
    
    public Equation(String eq) {
        
        eq = eq.replace(" ", "");
        
        String[] ex = eq.split("=");
        
        leftSide = new Expression(ex[0]);
        rightSide = new Expression(ex[1]);
        
    }

    @Override
    public void add(PolynomialComponent c) {

    }

    @Override
    public void toExponent(int e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Expression getLeftSide() {
        return leftSide;
    }

    public Expression getRightSide() {
        return rightSide;
    }
}
