package ddp2.kelasA.tp1;

import javax.swing.JOptionPane;

public class LuhnChecksumValidation {
    public static void main (String[] args) {
        // initialize variables
        String cardString;

        // loop forever until break
        while (true) {
            // take credit card number from user
            cardString = JOptionPane.showInputDialog(null,
                    "Enter a credit card / debit card number as a long integer,\nQUIT to end:\n",
                    "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE);
            // check if input == "quit"
            if (cardString.equalsIgnoreCase("quit")) break;

            // check validity of card number
            if (isValid(cardString)) {
                // card number is valid
                JOptionPane.showMessageDialog(null,
                    String.format("The number %s is valid", cardString),
                    "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE);
            } else {
                // card number is invalid
                JOptionPane.showMessageDialog(null,
                    String.format("The number %s is invalid", cardString),
                    "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    /**
     * Checks if a number is a valid credit card / debit card number according to the Luhn check.
     *
     * @param strNumber The number to be checked as a string.
     * @return The validity of the number.
    */
    public static boolean isValid (String strNumber) {
        long number;

        // check if input is a number (long)
        // invalid if not a number (long)
        try {
            number = Long.parseLong(strNumber);
        } catch (Exception e) {
            return false;
        }

        // check size of number
        // the number is invalid if smaller than 13 digits or bigger than 16 digits.
        if (getSize(number) < 13 || getSize(number) > 16) return false;

        int total = 0;  // running total
        int i = 1;  // parity of the current position from the right
        // loop until all digits of the numbers is checked (number /= 10 until 0)
        while (number != 0) {
            int digit = (int) (number % 10);
            // add to total based on position of digit from the right
            // easy case: digit == 0, not affected by doubling.
            total += (digit == 0) ? 0 :
            // digits in odd positions
            // normal addition to the total
                (i % 2 == 1) ? digit :
            // digits in even positions
            // double the digit, then return the digital root of the digit using mod 9.
            // if a number > 0 is divisible by 9, then the sum of digits is always 9.
            // ^^^ doesn't apply to 0, but that case is already dealt with.
                (2*(digit) % 9 == 0) ? 9 : 2*(digit) % 9;

            // remove the last digit off the number by floor division
            // add to position to change parity
            number /= 10;
            ++i;
        }

        // check if the total is divisible by 10
        return (total % 10 == 0);
    }

    /**
     * Returns the amount of digits in a number d.
     *
     * @param d a long
     * @return length of d as an integer.
     */
    public static int getSize (long d) {
        // if x is a positive natural number,
        // the floor(log10(x)) of a number x is equal to the number of digits in x (base 10) - 1.
        // ex. floor(log10(1)) = 0, floor(log10(5)) = 0, floor(log10(99)) = 1, floor(log10(100)) = 2.
        // since d is a (positive) long and the case where d = 0 is taken care of, this algorithm always works.

        if (d == 0) return 1; // special case where d = 0 (log10(0) = -Inf, instead return 1)
        d = Math.abs(d);    // make d a positive number since sign doesn't matter
        return (int) Math.floor(Math.log10(d)) + 1;
    }
}