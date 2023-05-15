package ddp2.kelasA.tp3;

import java.awt.*;
import java.util.Random;

public class StickFigure {
    // color
    private Color color;
    // position and size
    // baseX, baseY => bottom center of stickman, height => bottom to top in pixels
    private int baseX, baseY, height;
    // Proportions of head
    // Only diameter is required
    private int headDia;
    private boolean headFill = false;
    // Proportions of legs
    // Describes 2 lines from
    // (baseX, baseY - legPos) to (baseX +- legHDelta, baseY)
    private int legPos, legHDelta;
    // Proportions of arms (both of them)
    // Arm position
    private int armPos,
        armHdelta;
    // Left arm
    // Describes a line from
    // (baseX, baseY-armPos) to (baseX +/- armHdelta, baseY - armPos + LArmVdelta)
    // Right arm
    // Describes a line from
    // (baseX, baseY-armPos) to (baseX +/- armHdelta, baseY - armPos + RArmVdelta)
    private int LArmVdelta, RArmVdelta;
    // Determines whether the arms point the correct direction or the opposite.
    private boolean leftArmOpposite = false, rightArmOpposite = false;
    // Effective limb proportions to try and correct long limbs when very small
    private int effLegHdelta;
    private int effRArmVDelta, effLArmVDelta;

    // Thiccness of the figure
    // 0 -> thin, 1 -> medium, 2 -> thicc
    private int thickness = 0;

    // NB:
    // Positive VDelta will move it down
    // HDelta is assumed to always be positive, but negative works as well.



    /**
     Constructs a stick figure with given attributes
     @param center Position of the horizontal center of the stick figure, in pixels from the left
     @param bottom Position of the vertical bottom of the stick figure, in pixels from the top
     @param height Size of the stick figure, which is the pixel length from the bottom to the top
     @param color Color of the stick figure
     */
    public StickFigure(int center, int bottom, int height, Color color) {
        // define a stick figure
        baseX = center;
        baseY = bottom;
        this.color = color;
        this.height = height;

        // set starting pos of legs and arms
        RArmVdelta = 20;
        LArmVdelta = 20;
        legHDelta = 15;

        // update proportions
        update();
    }

    /**
    Draws this figure
     @param page the page to draw the figure on
     */
    public void draw (Graphics2D page) {
        // find useful coordinates and setup page
        int top = baseY - height;
        int armStartY = baseY - armPos;
        int headTopLeftX = baseX - headDia/2;
        page.setColor(color);
        page.setStroke(new BasicStroke(thickness*3 + 1));

        // Draw legs
        page.drawLine(baseX+effLegHdelta, baseY, baseX, baseY-legPos);
        page.drawLine(baseX-effLegHdelta, baseY, baseX, baseY-legPos);

        // Draw body
        page.drawLine(baseX, baseY- legPos, baseX, top + headDia);

        // Draw arms
        if (leftArmOpposite) page.drawLine(baseX, armStartY, baseX+armHdelta, armStartY+effLArmVDelta);
        else page.drawLine(baseX, armStartY, baseX-armHdelta, armStartY+effLArmVDelta);

        if (rightArmOpposite) page.drawLine(baseX, armStartY, baseX-armHdelta, armStartY+effRArmVDelta);
        else page.drawLine(baseX, armStartY, baseX+armHdelta, armStartY+effRArmVDelta);


        // Draw head (filled if headFill==true)
        if (headFill) page.fillOval(headTopLeftX, top, headDia, headDia);
        else page.drawOval(headTopLeftX, top, headDia, headDia);
    }

    /**
     Updates body proportions based on height.
     Updates effective limb proportions.
     */
    private void update() {
        // Update body proportions based on height
        headDia = height / 4;
        legPos = height / 3;
        armPos = 2* height / 3;
        armHdelta = height / 4;

        // Update effective limbs
        // Cap arm vertical delta to height/6
        // Cap leg horizontal delta to height/5
        effRArmVDelta = Math.min(Math.max(RArmVdelta, -height/6), height / 6);
        effLArmVDelta = Math.min(Math.max(LArmVdelta, -height/6), height / 6);

        effLegHdelta = Math.min(Math.max(legHDelta, -height/5), height / 5);
    }


    /**
     * Moves the stick figure by the given deltas
     * @param hDelta Horizontal delta of the target position to the current position
     * @param vDelta Vertical delta of the target position to the current position
     */
    public void move(int hDelta, int vDelta) {
        baseX += hDelta;
        baseY += vDelta;
    }

    /**
     * Moves the stick figure to the coordinates given by the Point object
     * @param mouse Point object, the mouse
     */
    public void moveToMousePos(Point mouse) {
        if (mouse == null) return;
        baseX = (int) mouse.getX();
        baseY = (int) mouse.getY() + height/2;
    }

    /**
     * Resizes the stick figure by a given factor.
     * The height must be at least 2 pixels tall, and will fail to resize if the next height goes below 2 pixels.
     * Implicitly the factor must be positive.
     * @param factor The factor to resize the stick figure by.
     */
    public void resize (double factor) {
        if (height * factor >= 2) height = (int) (height * factor);
        update();
    }

    /**
     * Moves the stick figure's limbs according to the parameters
     * @param armVDelta - V delta of the hand to the start of the arms, positive is downwards
     * @param legHDelta - H delta of the legs from the center
     */
    public void moveLimbs (int armVDelta, int legHDelta) {
        this.RArmVdelta = armVDelta;
        this.LArmVdelta = armVDelta;
        this.legHDelta = legHDelta;
        update();
    }

    public void moveLeftArm (int vDelta, boolean opposite) {
        LArmVdelta = vDelta;
        leftArmOpposite = opposite;
        update();
    }

    public void moveRightArm (int vDelta, boolean opposite) {
        RArmVdelta = vDelta;
        rightArmOpposite = opposite;
        update();
    }

    /**
     * Swaps between filling the head or not
     */
    public void swapHeadFill() {headFill = !headFill;}

    /**
     * Changes the color of the figure to a random color
     */
    public void changeColor() {
        Random rand = new Random();
        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    public void cycleThickness() {
        thickness = ++thickness % 3;
    }
}
