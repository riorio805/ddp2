import javax.swing.JOptionPane;

public class CreditCardValidation {
    public static void main (String[] args) {
        // initialize variables
        String cardString;
        long cardNum;

        // loop forever until break
        while (true) {
            // take credit card number from user
            cardString = JOptionPane.showInputDialog(null,
                "Enter a credit card / debit card number as a long integer,\nQUIT to end:\n",
                "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE);
            // check if input == "quit", break if so
            if (cardString.equalsIgnoreCase("quit")) break;

            // check if input is a number (long)
            try {
                cardNum = Long.parseLong(cardString);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                    "The number " + cardString + " is invalid.",
                    "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE);
                continue;
            }

            // check if number is valid
            // use ternary operator to display validity
            JOptionPane.showMessageDialog(null,
                "The number " + cardString + " is " + ((isValid(cardNum)) ? "valid." : "invalid."),
                "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static boolean isValid (long number) {
        // check size of number
        // the number is invalid if smaller than 13 digits or bigger than 16 digits.
        if (getSize(number) < 13 || getSize(number) > 16) return false;

        // check if starts with 4, 5, 37, or 6
        if (!  (prefixMatched(number, 4) ||
                prefixMatched(number, 5) ||
                prefixMatched(number, 37) ||
                prefixMatched(number, 6))) return false;

        // get result by adding sum of double even places and sum of odd places.
        // check if the result is divisible by 10
        int result = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        return (result % 10 == 0);
    }

    public static int sumOfDoubleEvenPlace (long number) {
        // remove first digit to set parity to even
        number /= 10;
        // initialize total
        int total = 0;
        // go from right side
        // divide 100 every time until 0
        while (number != 0) {
            // take rightmost digit by mod 10
            // add double it to total after parsing through getDigit
            // getDigit requires int --> use casting
            total += getDigit( (int) (2*(number % 10)) );

            number /= 100;
        }

        return total;
    }

    public static int sumOfOddPlace (long number) {
        // initialize total
        int total = 0;
        // go from right side
        // jump every 2 digit by dividing 100 every time until 0
        while (number != 0) {
            // take rightmost digit by mod 10
            // add it to total
            total += number % 10;

            number /= 100;
        }

        return total;
    }

    public static int getDigit(int digit) {
        // digit is a 1 digit number, return digit
        return (digit < 10) ? digit :
            // digit is a 2-digit number
            // if a number > 0 is divisible by 9, then the sum of digits is always 9.
            // otherwise the sum of d
            // ^^^ doesn't apply to 0, but that case is already dealt with.
            (digit % 9 == 0) ? 9 : digit % 9;
    }

    public static boolean prefixMatched (long number, int d) {
        // find prefix of length of d
        // check if the first digits of number match with d
        return getPrefix(number, getSize(d)) == d;
    }

    public static int getSize (long d) {
        if (-10 < d && d < 10) return 1;    // base case, 1 digit
        return 1 + getSize(d/10);        // recursion, divide 10 to remove 1 digit
    }

    public static long getPrefix (long number, int k) {
        return number / (long) (Math.pow(10, getSize(number) - getSize(k)));
    }
}