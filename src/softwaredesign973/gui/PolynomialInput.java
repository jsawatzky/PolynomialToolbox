package softwaredesign973.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

import softwaredesign973.polynomial.Polynomial;
import softwaredesign973.polynomial.Term;

public class PolynomialInput extends JPanel {
    
    private GridBagConstraints c = new GridBagConstraints();

    private MouseListener mouseListener;
    
    private int numTerms = 5;
    
    private ArrayList<JComponent[]> terms = new ArrayList<>();

    public PolynomialInput() {

        super(new GridBagLayout());

        c.anchor = GridBagConstraints.WEST;

        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {}

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

                for (JComponent[] term: terms) {
                    for (int i = 0; i < term.length; i++) {
                        if (mouseEvent.getSource().equals(term[i])) {
                            if (term[i] instanceof JLabel) {
                                switch (i) {
                                    case 0:
                                        String text = ((JLabel) term[i]).getText();
                                        term[i] = new JComboBox<>(new String[]{"+", "-"});
                                        ((JComboBox)term[i]).setSelectedItem(text);
                                        break;
                                    case 1:
                                        term[i] = new JSpinner(new SpinnerNumberModel(Integer.parseInt(((JLabel) term[i]).getText()), 0, 999, 1));
                                        break;
                                    case 2:
                                        term[i] = new JSpinner(new SpinnerNumberModel(Integer.parseInt(((JLabel) term[i]).getText()), 0 , 10, 1));
                                        break;
                                }
                                term[i].addMouseListener(mouseListener);
                            }
                        }
                    }
                }

                update();

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

                System.out.println("1");
                for (JComponent[] term: terms) {
                    for (int i = 0; i < term.length; i++) {
                        if (mouseEvent.getSource().equals(term[i])) {
                            System.out.println("2");
                            switch (i) {
                                case 0:
                                    term[i] = new JLabel(((JComboBox)term[i]).getSelectedItem().toString());
                                    break;
                                case 1:
                                case 2:
                                    term[i] = new JLabel(((JSpinner)term[i]).getValue().toString());
                                    break;
                            }
                            term[i].addMouseListener(mouseListener);
                        }
                    }
                }

                update();

            }
        };

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

        removeAll();
        
        for (int i = 0; i < numTerms; i++) {

            if (terms.size() < i+1) {

                JLabel sign = new JLabel("+");
                sign.addMouseListener(mouseListener);
                JLabel coef = new JLabel("0");
                coef.addMouseListener(mouseListener);
                JLabel exp = new JLabel(numTerms - i - 1 + "");
                exp.addMouseListener(mouseListener);

                terms.add(new JComponent[]{sign, coef, exp});

            }

            JComponent[] term = terms.get(i);

            c.gridy = 1;

            c.gridx = i*4;
            add(term[0], c);

            c.gridx = i*4+1;
            add(term[1], c);

            c.gridx = i*4+2;
            add(new JLabel("x"), c);

            c.gridx = i*4+3;
            c.gridy = 0;
            add(term[2], c);
        }

        revalidate();
        
    }
    
    public Polynomial getPolynomial() {
        
        Term[] polyTerms = new Term[numTerms];
        
        for (int i = 0; i < numTerms; i++) {
            
            JComponent[] term = terms.get(i);

            String sign;
            String coef;
            String vari;
            if (term[0] instanceof JComboBox) {
                sign = ((JComboBox)term[0]).getSelectedItem().toString();
            } else {
                sign = ((JLabel)term[0]).getText();
            }
            if (term[1] instanceof JSpinner) {
                coef = ((JSpinner)term[1]).getValue().toString();
            } else {
                coef = ((JLabel)term[1]).getText();
            }
            if (term[2] instanceof JSpinner) {
                vari = ((JSpinner)term[2]).getValue().toString();
            } else {
                vari = ((JLabel)term[2]).getText();
            }
            
            polyTerms[i] = new Term(Integer.parseInt(sign+coef), Integer.parseInt(vari));
            
        }
        
        return new Polynomial(polyTerms);
        
    }
    
}
