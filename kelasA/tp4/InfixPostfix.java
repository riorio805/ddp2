package ddp2.kelasA.tp4;

import javax.swing.*;

public class InfixPostfix {
    // main class
    public static void main (String[] args) {
        // Create and set up a frame
        JFrame frame = new JFrame("Infix -> Postfix Evaluator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel
        frame.add(new InfixPostfixPanel());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
