package softwaredesign973.gui;

import softwaredesign973.polynomial.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by Jacob on 2015-11-10.
 */
public class MainGUI {

    private PolynomialInput polynomialInput;
    private JPanel panel1;
    private JButton removeTerm;
    private JButton addTerm;
    private JButton addPolynomial;
    private JTextArea info;
    private JScrollPane listPane;
    private JPanel graph;
    private JPanel operations;
    private JButton deletePolynomial;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    public MainGUI() {
        addPolynomial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial p = polynomialInput.getPolynomial();

            }
        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        polynomialInput = new PolynomialInput();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(polynomialInput, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("New Polynomial");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        removeTerm = new JButton();
        removeTerm.setText("-");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0.05;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(removeTerm, gbc);
        addTerm = new JButton();
        addTerm.setText("+");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.weightx = 0.05;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(addTerm, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 0.3;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        addPolynomial = new JButton();
        addPolynomial.setText("Add");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(addPolynomial, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weightx = 0.7;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel2, gbc);
        info = new JTextArea();
        info.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(info, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel3, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
