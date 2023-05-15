package ddp2.kelasA.tp3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovePanel extends JPanel {
    // constants
    private final int
            WINDOW_WIDTH = 600,
            WINDOW_HEIGHT = 400,
            BASE_STEP = 8,
            ST_CENTER = (WINDOW_WIDTH / 2),
            ST_BOTTOM = (3 * WINDOW_HEIGHT / 4),
            ST_SIZE = 2 * WINDOW_HEIGHT / 5;
    private int STEP = BASE_STEP;   // Effective step size, changes when shift or ctrl is pressed
    private StickFigure stickMan;   // Stick figure object
    private boolean mouseOn = false;

    // Panel constructor
    public MovePanel() {
        // Add key listener and mouse listener
        addKeyListener(new MoveListener());
        addMouseListener(new MoveListener());
        addMouseMotionListener(new MoveListener());

        // Add stickman
        stickMan = new StickFigure(ST_CENTER, ST_BOTTOM, ST_SIZE, Color.BLUE);

        // Set up the Panel
        setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        setBackground(Color.white);
        setFocusable(true);
        setToolTipText("You can manipulate the figure using the keyboard (Arrows (+shift or ctrl), g, s, u, m, d, c, o, t) or using the mouse (Left click, right click).");
    }


    // Draws the figures
    public void paintComponent (Graphics page) {
        super.paintComponent (page);
        stickMan.draw((Graphics2D) page);
    }


    /*
    Class for listening to both keyboard and mouse input
     */
    private class MoveListener implements KeyListener, MouseListener, MouseMotionListener {
        /*
         Handle a key-pressed event:
         Movement:
         Arrow keys cause the figure to move horizontally or vertically
         Arrow keys while holding shift causes the figure to move slower
         Arrow keys while holding control causes the figure to move faster
         Size:
         The g key causes the figure to "grow"
         The s key causes the figure to shrink.
         Appearance:
         the u key causes arms and legs to go up
         the m key puts them in the middle,
         the d key puts them down.
         the c key randomizes the color of the figure
         the o key switches between filling the head and not
         the t key cycles between 3 thicknesses
        */
        public void keyPressed (KeyEvent e) {
            STEP = BASE_STEP;
            if (e.isShiftDown()) STEP = BASE_STEP * 3;
            if (e.isControlDown()) STEP = BASE_STEP / 2;

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> stickMan.move(-STEP, 0);
                case KeyEvent.VK_RIGHT -> stickMan.move(STEP, 0);
                case KeyEvent.VK_UP -> stickMan.move(0, -STEP);
                case KeyEvent.VK_DOWN -> stickMan.move(0, STEP);
                case KeyEvent.VK_L -> {
                    stickMan.moveLeftArm(10, false);
                    stickMan.moveRightArm(30, true);
                }
                case KeyEvent.VK_R -> {
                    stickMan.moveLeftArm(10, true);
                    stickMan.moveRightArm(30, false);
                }
                case KeyEvent.VK_G -> stickMan.resize(1.5);
                case KeyEvent.VK_S -> stickMan.resize(0.5);
                case KeyEvent.VK_U -> stickMan.moveLimbs(-60, 40);
                case KeyEvent.VK_M -> stickMan.moveLimbs(0, 20);
                case KeyEvent.VK_D -> stickMan.moveLimbs(60, 10);
                case KeyEvent.VK_C -> stickMan.changeColor();
                case KeyEvent.VK_O -> stickMan.swapHeadFill();
                case KeyEvent.VK_T -> stickMan.cycleThickness();
                default -> {}
            }

            repaint();
        }


        /*
        Handle a mouse event:
        Location:
        Left-clicking and dragging anywhere on the screen causes the figure to move to the mouse location.
        Reset:
        Right-clicking anywhere resets the stick figure
         */
        public void mousePressed(MouseEvent e) {
            if (!mouseOn) return;
            switch (e.getButton()) {
                case MouseEvent.BUTTON3 -> stickMan = new StickFigure(ST_CENTER, ST_BOTTOM, ST_SIZE, Color.BLUE);
                case MouseEvent.BUTTON1 -> stickMan.moveToMousePos(getMousePosition());
            }
            repaint();
        }
        public void mouseDragged(MouseEvent e) {
            if (!mouseOn) return;
            stickMan.moveToMousePos(getMousePosition());
            repaint();
        }

        public void mouseEntered(MouseEvent e) {mouseOn = true;}
        public void mouseExited(MouseEvent e) {mouseOn = false;}


        // Empty bodies to complete the interfaces
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }
}

