package ddp2.kelasA.tp3;

import javax.swing.*;

public class MoveStickMan {
    // main class
    public static void main (String[] args) {
        // Create and set up a frame
        JFrame frame = new JFrame("move stickman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel
        frame.add(new MovePanel());
        frame.pack();
        frame.setVisible(true);

    }
}
