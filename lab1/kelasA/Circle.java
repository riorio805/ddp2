// part 3

//**********************************************************
// Circle.java
//
// Print the area of a circle with two different radii
//**********************************************************

import java.util.Scanner;
public class Circle
{
    public static void main(String[] args)
    {
        // init stuff
        final double PI = Math.PI; //3.141592653589793
        double radius, area, circumference;
        Scanner input = new Scanner(System.in);

        // repeat twice
        for (int _c = 0; _c < 2; _c++) {
            // input
            System.out.print("Please enter a value for the radius: ");
            radius = input.nextDouble();
            input.nextLine();

            // area
            area = PI * radius * radius;
            System.out.println("The area of a circle with radius " + radius + " is " + area);

            // circumference
            circumference = 2 * PI * radius;
            System.out.println("The circumference of a circle with radius " + radius + " is " + circumference);
        }
    }
}
