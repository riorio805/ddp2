import javax.swing.JOptionPane;

public class CountChars {
    public static void main (String[] args) {
        // loop forever until break
        while (true) {
            // initialize counting variables to 0
            int countA = 0, countE = 0, countI = 0, countO = 0, countU = 0,
                countSpace = 0, countConsonant = 0, countOther = 0;
            // take input from user
            String input = JOptionPane.showInputDialog(null, "Enter a sentence of phrase, quit to end:");
            // break if user inputs "quit"
            if (input.equals("quit")) break;

            // loop through each character
            for (int i = 0; i < input.length(); i++) {
                // take a single character at position i
                char ch = input.charAt(i);
                switch (ch) {
                    case 'a', 'A' -> countA++;  // A
                    case 'e', 'E' -> countE++;  // E
                    case 'i', 'I' -> countI++;  // I
                    case 'o', 'O' -> countO++;  // O
                    case 'u', 'U' -> countU++;  // U
                    case ' ' -> countSpace++;   // blank space
                    default -> {
                        // consonant
                        if (Character.isLetter(ch)) countConsonant++;
                        // others
                        else countOther++;
                    }
                }
            }

            // show the totals to user (using String.format)
            JOptionPane.showMessageDialog(null, String.format("""
                    There are:
                    %d blank spaces,
                    %d A,
                    %d E,
                    %d I,
                    %d O,
                    %d U,
                    %d consonants,
                    %d other characters
                    in the phrase "%s".
                    """, countSpace, countA, countE, countI, countO, countU, countConsonant, countOther, input));

        }
    }
}
