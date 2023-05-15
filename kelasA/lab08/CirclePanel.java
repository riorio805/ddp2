package ddp2.kelasA.lab08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CirclePanel extends JPanel {
    private ArrayList<Circle> listOfCircles = new ArrayList<>();

    private final int
        WINDOW_WIDTH = 600,
        WINDOW_HEIGHT = 400;



    public CirclePanel() {
        addKeyListener(new CircleListener());
        addMouseListener(new CircleListener());
        addMouseMotionListener(new CircleListener());

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);

        listOfCircles.add(new Circle(getPreferredSize()));

    }

    @Override
    protected void paintComponent(Graphics page) {
        super.paintComponent(page);
        for (Circle circle: listOfCircles) {
            circle.draw(page);
        }
    }

    /**
     * Moves every circle specified by the index list to the position specified by the clickpoint.
     * @param toUpdate list of the indices of the circles to be updated
     * @param mouse Clickpoint of the mouse
     */
    private void moveCircles(int[] toUpdate, Point mouse) {
        for (int i: toUpdate) {
            listOfCircles.get(i).move(mouse);
        }
    }


    /**
     * Keeps every circles on the keep list, and deletes the rest
     * @param keep list of indices of circles to keep after deletion
     */
    private void deleteCircles (int[] keep) {
        ArrayList<Circle> outCircle = new ArrayList<>();

        for (int i: keep) {
            outCircle.add(listOfCircles.get(i));
        }

        listOfCircles = outCircle;
    }

    private int[] circlesNotOnPoint (Point mouse) {
        // Indices of the circles that aren't clicked
        int[] noClickCircle = new int[0];

        // Find the circles that isn't clicked by the mouse
        for (int i = 0; i < listOfCircles.size(); i++) {
            Circle circle = listOfCircles.get(i);
            if (!circle.isInside(mouse)) {
                noClickCircle = Arrays.copyOf(noClickCircle, noClickCircle.length + 1);
                noClickCircle[noClickCircle.length-1] = i;
            }
        }

        return noClickCircle;
    }

    private int[] circlesOnPoint (Point mouse) {
        // Indices of the circles that aren't clicked
        int[] clickCircle = new int[0];

        // Find the circles that isn't clicked by the mouse
        for (int i = 0; i < listOfCircles.size(); i++) {
            Circle circle = listOfCircles.get(i);
            if (circle.isInside(mouse)) {
                clickCircle = Arrays.copyOf(clickCircle, clickCircle.length + 1);
                clickCircle[clickCircle.length-1] = i;
            }
        }

        return clickCircle;
    }


    private class CircleListener implements KeyListener, MouseListener, MouseMotionListener{
        // 1. Now modify your CirclesListener class (defined inside CirclePanel) so that instead of creating
        //a new circle every time the user clicks, it moves the existing circle to the clickpoint if a circle already exists.
        //If no circle exists, a new one should be created at the clickpoint. So now a circle of the same color and size
        //should move around the screen.
        public void mouseClicked(MouseEvent e) {
            // Clickpoint of the mouse
            Point mouse = getMousePosition();
            // Indices of the circles that aren't clicked
            int[] noClickCircle = circlesNotOnPoint(mouse);
            int[] clickCircle = circlesOnPoint(mouse);

            // Create a new circle if no circle clicked
            if (noClickCircle.length == listOfCircles.size()) {
                listOfCircles.add(new Circle(mouse));
            }
            // Update their status otherwise
            // Left click moves their position based on the mouse
            // Right click deletes them

            else {
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1 -> moveCircles(clickCircle, mouse);
                    case MouseEvent.BUTTON3 -> deleteCircles(noClickCircle);
                    default -> {}
                }
            }
            repaint();
        }

        //5. Modify the code in CirclePanel.java so that the user can drag an existing circle around as long as the mouse
        //button is depressed (pushed down).
        public void mouseDragged(MouseEvent e) {
            // Clickpoint of the mouse
            Point mouse = getMousePosition();
            // Indices of the circles that aren't clicked
            int[] clickCircle = circlesOnPoint(mouse);

            // Move circles if dragged
            moveCircles(clickCircle, mouse);

            repaint();
        }


        //4. Add bodies for the mouseEntered and mouseExited methods so that when the mouse enters the panel the
        //background turns white, and when it exits the background turns black. Remember that you can set the
        //background color with the setBackground method.
        public void mouseEntered(MouseEvent e) {
            setBackground(Color.white);
            repaint();
        }
        public void mouseExited(MouseEvent e) {
            setBackground(Color.black);
            repaint();
        }

        // Key driven events
        // R key resets the screen to the starting position
        // A key adds a random circle randomly on the screen
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_R: listOfCircles.clear();
                case KeyEvent.VK_A: listOfCircles.add(new Circle(getPreferredSize()));
                default:
            }
            repaint();
        }



        // Empty bodies to fill the rest of the methods.
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }
}
