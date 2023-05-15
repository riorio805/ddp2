package ddp2.kelasA.lab08;

import java.awt.*;
import java.util.Random;


public class Circle {
    // Minimum and maximum radius possible
    private final int
            MIN_RADIUS = 25,
            MAX_RADIUS = 75;
    // Size of the circle as a radius
    private int radius;
    // Position of the center of the circle
    private int posX, posY;
    // Color of the circle
    private Color color;
    // Static random numbers generator object
    static Random rand = new Random();


    /**
     * Constructs a circle with a random position based on the dimensions provided in bounds.
     * @param bounds Dimension, maximum bounds allowed for the center of the circle
     */
    public Circle (Dimension bounds) {
        this(
            (int) (bounds.width * rand.nextFloat()),     // x
            (int) (bounds.height * rand.nextFloat())     // y
        );
    }

    /**
     * Constructs a circle with the position set based on the mouse Point.
     * @param mouse Point, the location of the mouse
     */
    public Circle (Point mouse) {
        this((int) mouse.getX(), (int) mouse.getY());
    }


    /**
     * Constructs a circle with the position set based on the given coordinates,
     * and a randam radius.
     * @param x X position of the circle center
     * @param y Y position of the circle center
     */
    public Circle(int x, int y) {
        move(x,y);
        changeColor();
        radius = rand.nextInt(MIN_RADIUS, MAX_RADIUS+1);
    }


    /**
     * Draws the circle on the page
     * @param page The graphics
     */
    public void draw (Graphics page) {
        //set color
        page.setColor(color);
        //draw circle
        page.fillOval(posX-radius, posY-radius, 2*radius, 2*radius);
    }

    /**
     * Set the circle's location to the coordinates given.
     * @param x X position of the target location
     * @param y Y position of the target location
     */
    public void move(int x, int y) {
        posX = x;
        posY = y;
    }


    /**
     * Changes the color of the circle to a random color
     */
    public void changeColor() {
        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }


    //1. This program creates a new circle each time—you can tell because each circle is a different color and size.
    //Write a method void move(Point p) for your Circle class that takes a Point and moves the circle so its center
    //is at that point.
    /**
     * Moves the circle to the coordinates given by the Point object
     * @param mouse Point object, the mouse
     */
    public void move(Point mouse) {
        if (mouse == null) return;
        posX = (int) mouse.getX();
        posY = (int) mouse.getY();
    }

    /**
     * Checks whether a mouse is inside this circle.
     * @param mouse Point object, the mouse
     * @return A boolean value, Is the Point inside this circle.
     */
    public boolean isInside(Point mouse) {
        if (mouse == null) return false;
        double dx = (posX - mouse.getX());
        double dy = (posY - mouse.getY());
        double distance = Math.pow( (Math.pow(dx, 2) + Math.pow(dy, 2)), 0.5);
        return distance <= radius;
    }

    @Override
    public String toString() {
        return "Circle at: (" + posX + ',' + posY + ") with radius " + radius;
    }
}
