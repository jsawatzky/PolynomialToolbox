package softwaredesign973.gui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import softwaredesign973.polynomial.Polynomial;
import softwaredesign973.polynomial.Term;

public class PolynomialInput extends JPanel {
    
    private GridBagConstraints c = new GridBagConstraints();
    
    private int numTerms = 5;
    
    private ArrayList<JComponent[]> terms = new ArrayList<>();

    public PolynomialInput() {
        
        super(new GridBagLayout());
        update();
        
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
        
        for (int i = 0; i < numTerms; i++) {

            if (terms.size() < i+1) {

                JComboBox<String> sign = new JComboBox<>(new String[]{"+", "-"});
                sign.setBackground(getBackground());
                JSpinner coef = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
                coef.setBackground(getBackground());
                JSpinner exp = new JSpinner(new SpinnerNumberModel(numTerms - i - 1, 0, 10, 1));
                exp.setBackground(getBackground());

                terms.add(new JComponent[]{sign, coef, exp});

            }

            JComponent[] term = terms.get(i);

            c.gridy = 0;

            c.gridx = i*4;
            c.gridheight = 2;
            add(term[0], c);

            c.gridx = i*4+1;
            add(term[1], c);

            c.gridx = i*4+2;
            add(new JLabel("x"), c);

            c.gridx = i*4+3;
            c.gridheight = 1;
            add(term[2], c);
        }
        
    }
    
    public Polynomial getPolynomial() {
        
        Term[] polyTerms = new Term[numTerms];
        
        for (int i = 0; i < numTerms; i++) {
            
            JComponent[] term = terms.get(i);
            
            polyTerms[i] = new Term(Integer.parseInt((String)((JComboBox)term[0]).getSelectedItem()+((JSpinner)term[1]).getValue()), (Integer)((JSpinner)term[2]).getValue());
            
        }
        
        return new Polynomial(polyTerms);
        
    }
    
}
