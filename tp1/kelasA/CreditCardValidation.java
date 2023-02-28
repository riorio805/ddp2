import javax.swing.JOptionPane;

public class CreditCardValidation {
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

    public static boolean isValid (String number) {
        // trim whitespace
        number = number.trim();

        // check if input is a number (long)
        // invalid if not a number (long)
        try {
           Long.parseLong(number);
        } catch (Exception e) {
            return false;
        }

        // check size of number
        // the number is invalid if smaller than 13 digits or bigger than 16 digits.
        if (number.length() < 13 || number.length() > 16) return false;

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

    public static int sumOfDoubleEvenPlace (String number) {
        // make the first digit even parity
        number = (number.length() % 2 == 1) ? number.substring(1) : number;
        // keep running total
        int total = 0;
        // loop until number is empty, taking 2 characters from the left every time
        while (!number.isEmpty()) {
            // parse digit
            int digit = Integer.parseInt(number.substring(0,1));
            // add double to total
            total += getDigit(2*digit);
            // number is of even length, substring will not return error
            number = number.substring(2);
        }

        return total;
    }

    public static int sumOfOddPlace (String number) {
        // make the first digit even parity
        number = (number.length() % 2 == 0) ? number.substring(1) : number;
        // keep running total
        int total = 0;
        // loop until number is empty, taking 2 characters from the left every time
        while (!number.isEmpty()) {
            // parse digit
            int digit = Integer.parseInt(number.substring(0,1));
            // add to total
            total += getDigit(digit);
            // number is of odd length, will return error unless we only take one when length == 1
            number = number.substring(Math.min(number.length(), 2));
        }

        return total;
    }

    public static int getDigit(int digit) {
        // digit is a 1 digit number, return digit
        return (digit < 10) ? digit :
            // digit is a 2-digit number
            // if a number > 0 is divisible by 9, then the sum of digits is always 9.
            // ^^^ doesn't apply to 0, but that case is already dealt with.
            (digit % 9 == 0) ? 9 : digit % 9;
    }

    public static boolean prefixMatched (String number, int d) {
        // change d to string
        String match = Integer.toString(d);
        // use .startsWith() to match prefix
        return number.startsWith(match);
    }
}