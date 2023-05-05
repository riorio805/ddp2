package ddp2.kelasA.lab07.partA;

//*********************************************************
// VoteCounter.java
//
// Demonstrates a graphical user interface and event listeners to
// tally votes
//*********************************************************
import javax.swing.JFrame;
public class VoteCounter
{
    //----------------------------------------------
    // Creates the main program frame.
    //----------------------------------------------
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Vote Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new VoteCounterPanel());
        frame.pack();
        frame.setVisible(true);
    }
}