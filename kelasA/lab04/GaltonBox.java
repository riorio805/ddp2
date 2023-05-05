package ddp2.kelasA.lab04;

import java.util.Scanner;

public class GaltonBox {
    private final static Scanner input = new Scanner(System.in);

    public static void main (String[] args) {
        // Input from user the number of balls and slots in the machine
        System.out.print("Enter the number of balls: ");
        int numBalls = input.nextInt();
        System.out.print("Enter the number of slots: ");
        int numSlots = input.nextInt();
        System.out.println();

        // Initialize an int array to store the histogram data
        int[] slots = new int[numSlots];
        // Generate a random path for every ball, then increment slots
        // where the ball goes to.
        for (int i = 0; i < numBalls; i++) {
            slots[generatePath(numSlots)]++;
        }

        // Print the histogram.
        System.out.println();
        printHistogram(slots);
    }

    /**
     Generate and print a random path of length numSlots and
     return the slot number (from the left, 0-indexed) that the ball goes to.
     @param numSlots the number of slots
     @return The slot number that a random path goes to.
     */
    public static int generatePath (int numSlots) {
        // Initialize ball position and ball path.
        // ballPos starts at the furthest left.
        // For every 'R', the ballPos moves right 1 position
        // --> ballPos = ballPath.count('R')
        int ballPos = 0;
        String ballPath = "";

        // Generate the path randomly using a coin flip
        for (int i = 1; i < numSlots; i++) {
            if (Math.random() < 0.5) {
                ballPath += 'L';
            }
            else {
                ballPath += 'R';
                ballPos++;
            }
        }
        // Print the ball path, then return ball position.
        System.out.println(ballPath);
        return ballPos;
    }


    /**
    Prints the histogram of a
     */
    public static void printHistogram (int[] slots) {
        // Find the maximum amount of balls in a slot to
        // determine how many lines to print.
        // Also add '-' to bottomRow to print later (done slotLength times)
        String bottomRow = "";
        int max = -1;
        for (int i: slots) {
            // update maximum
            max = Math.max(max, i);
            // add to bottom row
            bottomRow += '-';
        }

        // Print every row of the histogram 1-by-1
        // Each column j has an 'O' if currRow <= slots[j]
        // currRow is counted from the bottom, the lowest row is the 1st.
        for (int i = max; i >= 1; i--) {
            for (int balls: slots) {
                System.out.print( (balls >= i) ? 'O' : ' ' );
            }
            System.out.println();
        }

        // Print the bottom row ('-' * slot length)
        System.out.print(bottomRow);
    }
}
