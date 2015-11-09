package softwaredesign973.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import softwaredesign973.polynomial.Polynomial;
import softwaredesign973.polynomial.Term;

public class PolynomialInput extends JPanel {
    
    private GridBagConstraints c = new GridBagConstraints();
    
    private int numTerms = 2;
    
    private ArrayList<JSpinner[]> terms = new ArrayList<>();

    public PolynomialInput() {
        
        super(new GridBagLayout());
        
    }
    
    public void addTerm() {
        numTerms++;
        update();
    }
    
    public void removeTerm() {
        numTerms--;
        update();
    }
    
    private void update() {
        
        int width = getPreferredSize().width/numTerms;
        int height = getPreferredSize().height;
        
        for (int i = 0; i < numTerms; i++) {
            
            JSpinner sign = new JSpinner(new SpinnerListModel(new String[] {"+", "-"}));
            JSpinner coef = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
            JLabel vari = new JLabel("x");
            JSpinner exp = new JSpinner(new SpinnerNumberModel(numTerms-i-1, 0, 10, 1));
            
            terms.add(new JSpinner[]{sign, coef, exp});
            
            
            
        }
        
    }
    
    public Polynomial getPolynomial() {
        
        Term[] polyTerms = new Term[numTerms];
        
        for (int i = 0; i < numTerms; i++) {
            
            JSpinner[] term = terms.get(i);
            
            polyTerms[i] = new Term(Integer.parseInt((String)term[0].getValue()+(Integer)term[1].getValue()), (Integer)term[2].getValue());
            
        }
        
        return new Polynomial(polyTerms);
        
    }
    
}
