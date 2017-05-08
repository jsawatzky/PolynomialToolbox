package polynomialtoolbox.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import polynomialtoolbox.polynomial.Polynomial;
import polynomialtoolbox.polynomial.Term;

public class PolynomialInput extends JPanel {

    //To confusing to document
    
    private GridBagConstraints c = new GridBagConstraints();

    private JTextField name = new JTextField("f", 1);
    private ArrayList<JSpinner[]> terms = new ArrayList<>();
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

    public PolynomialInput() {

        super(new GridBagLayout());

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
        
        for (int i = 0; i < 5; i++) {

            if (terms.size() < i+1) {

                JSpinner sign = new JSpinner(new SpinnerCircularModel(new String[]{"+", "-"}));
                JSpinner coef = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
                JSpinner exp = new JSpinner(new SpinnerNumberModel(5 - i, 0, 10, 1));

                terms.add(new JSpinner[]{sign, coef, exp});

            }

            JSpinner[] term = terms.get(i);

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
        add(colorChooser, c);

        revalidate();
        
    }

    public void reset() {

        name = new JTextField("f", 1);
        terms = new ArrayList<>();
        color = Color.BLUE;

        update();

    }
    
    public Function getFuntion() {
        
        Term[] polyTerms = new Term[5];
        
        for (int i = 0; i < 5; i++) {
            
            JSpinner[] term = terms.get(i);

            String sign = term[0].getValue().toString();
            String coef = term[1].getValue().toString();
            String vari = term[2].getValue().toString();
            
            polyTerms[i] = new Term(Integer.parseInt(sign+coef), Integer.parseInt(vari));
            
        }
        
        return new Function(name.getText(), new Polynomial(polyTerms), color);
        
    }
    
}
