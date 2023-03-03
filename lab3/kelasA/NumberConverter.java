import javax.swing.JOptionPane;

public class NumberConverter {
    public static void main (String[] args) {
        String inputStr, outputStr;
        boolean valid = true;
        long inputNum = 0;

        while (true) {
            // take input from user
            inputStr = JOptionPane.showInputDialog(null,
                "Please enter a positive decimal integer <= " + Integer.MAX_VALUE + " or QUIT to quit:",
                "Number Converter", JOptionPane.PLAIN_MESSAGE);
            // Quit if user inputs "quit"
            if (inputStr.equalsIgnoreCase("QUIT")) break;

            // Reset validity to true
            valid = true;

            // First check: Check if input is a long
            try { inputNum = Long.parseLong(inputStr); }
            catch (Exception e) { valid = false; }

            // Second check: Check if input is between 0 and 2147483647 (Integer max value)
            if (valid) valid = (0 <= inputNum) && (inputNum <= Integer.MAX_VALUE);

            // Set output string based on validity
            if (valid) {
                int decNum = (int) inputNum;    // cast from long to int
                outputStr = "The hex number for decimal number " + decNum + " is:\n" + dec2hex(decNum);
            }
            else {
                outputStr = "Wrong input.";
            }

            // Display the output string
            JOptionPane.showMessageDialog(null,
                outputStr,
                "Number Converter", JOptionPane.PLAIN_MESSAGE);
        }

    }
    public static String dec2hex (int decInt) {
        // initialize output string
        String outStr = "";

        // loop until no more digits left
        while (decInt != 0) {
            // Calculate next hex digit from the right
            int digit = decInt % 16;

            // From the digit, find the next character in the string
            // Use switch to set the char based on digit
            // If between 10-15 -> A-F
            // else between 0-9 stays the same (cast to char).
            char nextChar = switch (decInt % 16) {
                case 10 -> 'A';
                case 11 -> 'B';
                case 12 -> 'C';
                case 13 -> 'D';
                case 14 -> 'E';
                case 15 -> 'F';
                default -> (char) (decInt % 16);
            };

            // Append nextChar to the left of outStr
            outStr = nextChar + outStr;

            // Remove the rightmost hex digit
            decInt /= 16;
        }

        // If outStr is empty (decInt == 0) , output 0.
        // else output outStr
        return outStr.isEmpty() ? "0" : outStr;
    }
}