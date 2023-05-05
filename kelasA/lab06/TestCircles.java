package ddp2.kelasA.lab06;
import java.util.Scanner;

public class TestCircles {
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D();
        Circle2D c2 = new Circle2D(2, 2, 2.5);
        Circle2D c3 = new Circle2D(5.0, 6.0, 3.1);
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c3: " + c3);
        // Use the getArea() method to get the area of c2
        System.out.printf("Area of c2 is %.2f\n", c2.getArea());
        // Use the getPerimeter() method to get the perimeter of c2
        System.out.printf("Perimeter of c2 is %.2f\n", c2.getPerimeter());
        System.out.println("c2 contains point (3, 3)? "
                + c2.contains(3, 3));
        // Use contains() to check if a circle is contained in c3
        System.out.println("c3 contains circle Circle2D(4, 5, 8.5)? "
                + c3.contains(new Circle2D(4, 5, 8.5)));
        System.out.println("c1 overlaps circle Circle2D(3, 5, 0.3)? "
                + c1.overlaps(new Circle2D(3, 5, 0.3)));
        System.out.println("c2 overlaps c3? "
                + c2.overlaps(c3));
        // Check if c1 contains c1
        System.out.println("c1 contains c1? "
                + c1.contains(c1));
        System.out.println("Circle Circle2D(1, 0, 7) contains c1? "
                + new Circle2D(1,0,7).contains(c1));
        System.out.printf("The hashcode of c1 is %h\n", c1);
        System.out.printf("The hashcode of new Circle2D() is %h\n", new Circle2D());
        Circle2D c4 = new Circle2D("Lingkaran dengan radius 250 hidden here.");
        System.out.println("c4: " + c4);
        // Use static method getNumberOfCircles() to get the number of circles created
        System.out.println("Total number of circles created: " + Circle2D.getNumberOfCircles());
    }
}

class Circle2D {
    private final double x, y;
    private double radius;
    // Initialize static variable numberOfCircle
    private static int numberOfCircle = 0;

    // no-arg constructor, create a default circle with center(0,0) and radius 1
    Circle2D() {

        this(0,0,1);
    }

    // constructor, creates a circle with the specified x, y, and radius
    Circle2D(double x, double y, double radius) {
        numberOfCircle++;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    // constructor, creates a circle with center (0.0,0.0) and radius found in the string parameter
    Circle2D(String input) {
        this();
        // Scanner separates between every number
        // [^0-9]+ matches any non-digit at least 1 times.
        Scanner s = new Scanner(input).useDelimiter("[^0-9]+");
        // Take the first number in input
        this.radius = s.nextDouble();
    }

    // getter methods for X, Y, radius, and static numberOfcircle
    public double getX() { return this.x; }
    public double getY() { return this.y; }
    public double getRadius() { return this.radius; }
    public static int getNumberOfCircles() { return numberOfCircle; }

    //Override toString()
    @Override
    public String toString() {
        return String.format("Circle: center(%.2f,%.2f), radius %.2f, hashcode %h", x, y, radius, this);
    }

    // Return the area = pi * radius^2
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    // Return the perimeter = 2 * pi * radius
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    // Check if this circle contains a point
    public boolean contains(double x, double y){
        double dist = distance(this.x, this.y, x, y);
        return dist <= this.radius;
    }

    // Check if this circle contains another circle
    public boolean contains(Circle2D other){
        double dist = distanceTo(other);
        return (dist + other.getRadius()) <= this.getRadius();
    }

    // Check if this circle overlaps another circle
    public boolean overlaps(Circle2D other){
        double dist = distanceTo(other);
        return dist <= (this.getRadius() + other.getRadius());
    }

    // Static method that returns the distance between 2 points (x1, y1) and (x2, y2)
    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(
            Math.pow((x1-x2), 2) +
            Math.pow((y1-y2), 2));
    }

    // Method that returns the distance between this circle's radius and another circle's radius
    public double distanceTo(Circle2D other){
        return Math.sqrt(
            Math.pow((other.getX()-this.getX()), 2) +
            Math.pow((other.getY()-this.getY()), 2));
    }
}