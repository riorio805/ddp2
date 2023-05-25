package ddp2.kelasA.tp4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InfixPostfixPanel extends JPanel {
    private final JTextField inputExp;
    private final JLabel postfixLabel, resultLabel, errorLabel;

    // Panel constructor
    public InfixPostfixPanel() {
        // Set up the Panel
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.gray);
        setFocusable(true);
        // 4 rows by 2 column grid
        GridLayout grid = new GridLayout(4, 2);
        setLayout(grid);

        // Set up panel elements
        Font textFont = new Font("Arial", Font.BOLD, 20);

        // Row 1 col 1: static text
        JLabel label1 = new JLabel("Enter infix expression: ");
        label1.setFont(textFont);
        add(label1);

        // Row 1 col 2: text field
        inputExp = new JTextField();
        inputExp.addActionListener(new EvaluateListener());
        inputExp.setBackground(Color.yellow);
        inputExp.setFont(textFont);
        add(inputExp);

        // Row 2 col 1: static text
        JLabel label2 = new JLabel("Postfix expression: ");
        label2.setFont(textFont);
        add(label2);

        // Row 2 col 2: postfix text
        postfixLabel = new JLabel("");
        postfixLabel.setFont(textFont);
        add(postfixLabel);

        // Row 3 col 1: static text
        JLabel label3 = new JLabel("Result: ");
        label3.setFont(textFont);
        add(label3);

        // Row 3 col 2: result text
        resultLabel = new JLabel("");
        resultLabel.setFont(textFont);
        add(resultLabel);

        // Row 4 col 1: static text
        JLabel label4 = new JLabel("Error messages: ");
        label4.setFont(textFont);
        add(label4);

        // Row 4 col 2: error message text
        errorLabel = new JLabel("");
        errorLabel.setFont(textFont);
        add(errorLabel);
    }

    private class EvaluateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InfixPostfixEval expression = new InfixPostfixEval(inputExp.getText());

            // Set postfix text
            // Expression valid
            if (expression.getStatusCode() != 3) {
                postfixLabel.setText(expression.getPostfix());
            }
            // Expression invalid
            else {
                postfixLabel.setText("");
            }

            // Set result text
            resultLabel.setText( Long.toString(expression.getResult()) );

            // Set error message
            errorLabel.setText(
                switch (expression.getStatusCode()) {
                    case 1 -> "[Missing closed parenthesis]";
                    case 2 -> "[Missing open parenthesis]";
                    case 3 -> "[Not an equation]";
                    case 4 -> "[Missing operand]";
                    case 5 -> "[Missing operator]";
                    case 6 -> "[Division by zero]";
                    default -> "[]";
                }
            );
        }
    }
}
