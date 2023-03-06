package ddp2.tp2;

public class MagicSquare {
    private int[][] square;
    private int size;

    /**
     Construct a MagicSquare object
     (precondition: s is greater than 2 ).
     @param s the size of the square
     */
    public MagicSquare(int s)
    {
        size = s;
        // divide into 3 cases
        square = switch (s % 4) {
            case 0 -> generateDoublyEvenMagicSquare(s); // s = 4n
            case 2 -> generateSinglyEvenMagicSquare(s); // s = 4n + 2
            default -> generateOddMagicSquare(s);
        };
    }

    /**
     Generates a Magic Square of order (2n + 1).

     @param s the size of the square
     @return A magic square of size s.
     */
    public int[][] generateOddMagicSquare (int s) {
        int[][] square = new int[s][s];

        // starting position => middle bottom
        // last row, middle column
        int row = s-1;
        int col = s/2;

        // loop i from 1 to s^2
        for (int i = 1; i <= s*s; i++) {
            // set current element to i (post-processing), then move to next position
            square[row++][col++] = i;

            // keep position between 0 and s-1 (use floor mod)
            row = Math.floorMod(row, s);
            col = Math.floorMod(col, s);

            // check next element
            // if not empty go back then go up 1 space == up 2 space, left 1 space
            if (square[ row ][ col ] != 0) {row = row - 2; --col;}

            // keep position between 0 and s-1 (use floor mod)
            row = Math.floorMod(row, s);
            col = Math.floorMod(col, s);
        }

        return square;
    }

    /**
     Generates a Magic Square of order (4n).
     Algorithm is based on <a href="https://mathworld.wolfram.com/MagicSquare.html">an article from Wolfram MathWorld</a>

     @param s the size of the square
     @return A magic square of size s.
     */
    public int[][] generateDoublyEvenMagicSquare (int s) {
        int[][] square = new int[s][s];

        // loop each row and col
        for (int row = 0; row < s; row++) {
            for (int col = 0; col < s; col++) {
                // calculate index of the element
                int idx = s * row + col;

                // If element is on a diagonal of a 4x4, the value is (s*s + index) instead.
                // else (index + 1)
                //  main diagonal of a 4x4
                if ((row % 4) == (col % 4)  || (row % 4) + (col % 4) == 3)
                    square[row][col] = s*s - idx;
                else square[row][col] = idx + 1;


                // other diagonal of a 4x4
            }
        }

        return square;
    }


    /**
     Generates a Magic Square of order (4n + 2).
     Algorithm is based on <a href="https://en.wikipedia.org/wiki/Strachey_method_for_magic_squares">Strachey method</a>
     Modified to work with generateOddMagicSquare(s/2) for the subsquares.

     @param s the size of the square
     @return A magic square of size s.
     */
    public int[][] generateSinglyEvenMagicSquare (int s) {
        // initializing
        int[][] square = new int[s][s]; // output square
        int intTemp;    // temporary value

        // generate a magic square of size s/2
        int[][] subsquare = generateOddMagicSquare(s/2);

        // fill the square from section A to D
        // D B
        // A C
        // section A
        for (int row = s/2; row < s; row++) {
            for (int col = 0; col < s/2; col++) {
                square[row][col] = subsquare[row- (s/2)][col];
            }
        }
        // section B (+ s^2/4)
        for (int row = 0; row < s/2; row++) {
            for (int col = s/2; col < s; col++) {
                square[row][col] = subsquare[row][col - (s/2)] + (s*s/4);
            }
        }
        // section C (+ 2*s^2/4)
        for (int row = s/2; row < s; row++) {
            for (int col = s/2; col < s; col++) {
                square[row][col] = subsquare[row - (s/2)][col - (s/2)] + (s*s/2);
            }
        }
        // section D (+ 3*s^2/4)
        for (int row = 0; row < s/2; row++) {
            for (int col = 0; col < s/2; col++) {
                square[row][col] = subsquare[row][col] + (3*s*s/4);
            }
        }

        // swap (s-2)/4 columns from the left of A and D
        for (int row = 0; row < s/2; row++) {
            int[] temp = new int[(s-2)/4];
            // temp = row of A
            System.arraycopy(square[row], 0, temp, 0, (s-2)/4);
            // row of A = row of D
            System.arraycopy(square[s/2 + row], 0, square[row], 0, (s-2)/4);
            // row of D = temp
            System.arraycopy(temp, 0, square[s/2 + row], 0, (s-2)/4);
        }

        // swap the middle cell of A and D
        // A middle = ( s/4, s/4 ), D middle = ( 3*s/4, s/4 )
        intTemp = square[s/4][s/4];
        square[s/4][s/4] = square[3*s/4][s/4];
        square[3*s/4][s/4] = intTemp;

        // swap the left middle cell of A and D
        // A left middle = ( s/4, 0 ), D left middle = ( 3*s/4, 0 )
        intTemp = square[s/4][0];
        square[s/4][0] = square[3*s/4][0];
        square[3*s/4][0] = intTemp;

        // swap (s-2)/4 - 1 columns from the right of C and B
        for (int row = 0; row < s/2; row++) {
            int[] temp = new int[(s-2)/4 - 1];
            // temp = row of B
            System.arraycopy(square[row], (3*s+2)/4 + 1, temp, 0, (s-2)/4 - 1);
            // row of B = row of C
            System.arraycopy(square[s/2 + row], (3*s+2)/4 + 1, square[row], (3*s+2)/4 + 1, (s-2)/4 - 1);
            // row of C = temp
            System.arraycopy(temp, 0, square[s/2 + row], (3*s+2)/4 + 1, (s-2)/4 - 1);
        }

        // done
        return square;
    }


    /**
     Find the sum of the diagonal.
     @return sum: the sum of the diagonal
     */
    public int diagonalSum()
    {
        int sum = 0;
        for (int idx = 0; idx < size; idx++) {
            sum += square[idx][idx];
        }
        return sum;
    }

    /**
     Find the sum of the opposite diagonal.
     @return sum: the sum of the opposite diagonal
     */
    public int oppositeDiagonalSum()
    {
        int sum = 0;
        for (int idx = 0; idx < size; idx++) {
            sum += square[idx][square.length-idx-1];
        }
        return sum;
    }


    /**
     Add the numbers in a column of the square.
     @param col the column index
     @return the sum of the column
     */
    public int columnSum(int col)
    {
        int sum = 0;
        for (int row = 0; row < size; row++) {
            sum += square[row][col];
        }
        return sum;
    }


    /**
     Add the numbers in a row of the square.
     @param row the row index
     @return the sum of the row
     */
    public int rowSum(int row)
    {
        int sum = 0;
        for (int col = 0; col < size; col++) {
            sum += square[row][col];
        }
        return sum;
    }


    /**
     Gets a string representation of the contents of this square.
     @return a string representation of the square
     */
    public String toString()
    {
        String out = "";
        for (int[] row: square) {
            for (int elem: row) {
                out += String.format("%6d", elem);
            }
            out += "\n";
        }

        return out;
    }
}
