package ddp2.kelasA.lab07.partA;

//*********************************************************
// VoteCounterPanel.java
//
// Demonstrates a graphical user interface and event listeners to
// tally votes
//*********************************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VoteCounterPanel extends Container
{
    // Joe's, Sam's, and Mary's variables, in that order
    private int votesJoe, votesSam, votesMary;
    private JButton buttonJoe, buttonSam, buttonMary;
    private JLabel labelJoe, labelSam, labelMary;



    // Constructor: Sets up the GUI.
    // Create buttons and text for each of the candidates
    public VoteCounterPanel()
    {
        // Set up the GUI's layout
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        // Create the button for joe and hook the button press to the listener
        buttonJoe = new JButton("Vote for Joe");
        buttonJoe.addActionListener(new JoeButtonListener());
        // Initialize votes and label
        votesJoe = 0;
        labelJoe = new JLabel("Votes for Joe: " + votesJoe);
        layout.putConstraint(SpringLayout.WEST, labelJoe, 10, SpringLayout.EAST, buttonJoe);
        layout.putConstraint(SpringLayout.NORTH, labelJoe, 5, SpringLayout.NORTH, buttonJoe);
        // Add the panel to the GUI
        add(buttonJoe);
        add(labelJoe);


        // Do the same for Sam
        buttonSam = new JButton("Vote for Sam");
        buttonSam.addActionListener(new SamButtonListener());
        layout.putConstraint(SpringLayout.NORTH, buttonSam, 10, SpringLayout.SOUTH, buttonJoe);
        votesSam = 0;
        labelSam = new JLabel("Votes for Sam: " + votesSam);
        layout.putConstraint(SpringLayout.WEST, labelSam, 10, SpringLayout.EAST, buttonSam);
        layout.putConstraint(SpringLayout.NORTH, labelSam, 5, SpringLayout.NORTH, buttonSam);
        add(buttonSam);
        add(labelSam);

        // Do the same for Mary
        buttonMary = new JButton("Vote for Mary");
        buttonMary.addActionListener(new MaryButtonListener());
        layout.putConstraint(SpringLayout.NORTH, buttonMary, 10, SpringLayout.SOUTH, buttonSam);
        votesMary = 0;
        labelMary = new JLabel("Votes for Mary: " + votesMary);
        layout.putConstraint(SpringLayout.WEST, labelMary, 10, SpringLayout.EAST, buttonMary);
        layout.putConstraint(SpringLayout.NORTH, labelMary, 5, SpringLayout.NORTH, buttonMary);
        add(buttonMary);
        add(labelMary);

        // Set up the GUI
        setPreferredSize(new Dimension(300, 120));
        setBackground(Color.cyan);
    }


    //***************************************************
    // Represents a listener for button push (action) events
    //***************************************************
    private class JoeButtonListener implements ActionListener
    {
        //----------------------------------------------
        // Updates the counter and label when Vote for Joe
        // button is pushed
        //----------------------------------------------
        public void actionPerformed(ActionEvent event)
        {
            votesJoe++;
            labelJoe.setText("Votes for Joe: " + votesJoe);
        }
    }

    private class SamButtonListener implements ActionListener
    {
        //----------------------------------------------
        // Updates the counter and label when Vote for Joe
        // button is pushed
        //----------------------------------------------
        public void actionPerformed(ActionEvent event)
        {
            votesSam++;
            labelSam.setText("Votes for Joe: " + votesSam);
        }
    }

    private class MaryButtonListener implements ActionListener
    {
        //----------------------------------------------
        // Updates the counter and label when Vote for Joe
        // button is pushed
        //----------------------------------------------
        public void actionPerformed(ActionEvent event)
        {
            votesMary++;
            labelMary.setText("Votes for Joe: " + votesMary);
        }
    }
}