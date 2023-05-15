package ddp2.kelasA.lab08;

import javax.swing.*;

public class Lab08 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Click the Circles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new CirclePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
