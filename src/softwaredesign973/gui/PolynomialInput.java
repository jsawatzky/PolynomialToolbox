package softwaredesign973.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JTextField name = new JTextField("f", 1);
    private ArrayList<JComponent[]> terms = new ArrayList<>();
    private Color color = Color.BLUE;
    private JButton colorChooser = new JButton("Change Color", new Icon() {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, getIconWidth(), getIconHeight());
        }

        @Override
        public int getIconWidth() {
            return 10;
        }

        @Override
        public int getIconHeight() {
            return 10;
        }
    });
    private JButton removeTerm = new JButton("Remove Term");
    private JButton addTerm = new JButton("Add Term");

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
                                        term[i] = new JSpinner(new SpinnerCircularModel(new String[]{"+", "-"}));
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

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

                for (JComponent[] term: terms) {
                    for (int i = 0; i < term.length; i++) {
                        if (mouseEvent.getSource().equals(term[i])) {
                            term[i] = new JLabel(((JSpinner)term[i]).getValue().toString());
                            term[i].addMouseListener(mouseListener);
                        }
                    }
                }

                update();

            }
        };

        colorChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Pick a new Color", color);
                if (newColor != null) {
                    color = newColor;
                }
                update();
            }
        });
        addTerm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTerm();
            }
        });
        removeTerm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTerm();
            }
        });

        update();
        
    }
    
    public void addTerm() {
        if (numTerms < 10) {
            numTerms++;
            removeTerm.setEnabled(true);
        }
        if (numTerms == 10) {
            addTerm.setEnabled(false);
        }
        update();
    }
    
    public void removeTerm() {
        if (numTerms > 1) {
            numTerms--;
            addTerm.setEnabled(true);
        }
        if (numTerms == 1) {
            removeTerm.setEnabled(false);
        }
        update();
    }
    
    private void update() {

        removeAll();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);

        add(new JLabel("New Polynomial: "), c);

        c.gridx += 1;
        add(name, c);

        c.gridx += 1;
        add(new JLabel("(x) = "), c);
        
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

            c.gridx += 1;
            c.insets = new Insets(0, 5, 0, 5);
            add(term[0], c);

            c.gridx += 1;
            c.insets = new Insets(0, 0, 0, 0);
            add(term[1], c);

            c.gridx += 1;
            add(new JLabel("x^"), c);

            c.gridx += 1;
            add(term[2], c);
        }

        c.gridx += 1;
        c.insets = new Insets(0, 15, 0, 0);
        add(removeTerm, c);

        c.gridx += 1;
        c.insets = new Insets(0, 0, 0, 0);
        add(addTerm, c);

        c.gridx += 1;
        c.insets = new Insets(0, 15, 0, 0);
        add(colorChooser, c);

        revalidate();
        
    }

    public void reset() {

        numTerms = 5;

        name = new JTextField("f", 1);
        terms = new ArrayList<>();
        color = Color.BLUE;

        update();

    }
    
    public Function getFuntion() {
        
        Term[] polyTerms = new Term[numTerms];
        
        for (int i = 0; i < numTerms; i++) {
            
            JComponent[] term = terms.get(i);

            String sign;
            String coef;
            String vari;
            if (term[0] instanceof JSpinner) {
                sign = ((JSpinner)term[0]).getValue().toString();
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
        
        return new Function(name.getText(), new Polynomial(polyTerms), color);
        
    }
    
}
