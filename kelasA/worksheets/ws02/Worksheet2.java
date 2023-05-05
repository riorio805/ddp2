import java.util.Scanner;

public class Worksheet2 {
    final public static Scanner input = new Scanner(System.in);

    // main class
    public static void main(String[] args) {
        // problem 1
        // System.out.println(max(1, 2));

        /* problem 2
        System.out.print("Enter a number to find the sum of its digits: ");
        long num = input.nextLong();
        System.out.printf("The sum of the digits of %d is %d\n", num, sumDigits(num));
        */

        // problem 3
        // Problem3Series(20);

        // problem 4
        // Problem4.main();

        // problem 5
        Problem5.main();

        // problem 6
        System.out.println("Output of code block A is:");
        Problem6.codeA();
        System.out.println("Output of code block B is:");
        Problem6.codeB();
        System.out.println("Output of code block C is:");
        Problem6.codeC();
        System.out.println("Output of code block D is:");
        Problem6.codeD();

        /* explanations:
        6a.
        The integer max is never changed in main.
        The int max in max() is a local variable and cannot affect the max in main().

        6b.
        method1(i, num) prints every num^k for k from 1 up to i (inclusive), with space separation, then prints a newline.
        main() calls method(i, 2) for i from 1 up to 6

        6c.

        6d.
         */
    }

    /* 1.
    System.out.println(max(1, 2));
    There is confusion as to which method to use as ints can be automatically converted to doubles
    */
    public static double max(int num1, double num2) {
        if (num1 > num2) return num1;
        else return num2;
    }
    public static double max(double num1, int num2) {
        if (num1 < num2) return num2;
        else return num1;
    }

    // 2. Write a method that computes the sum of the digits as an integer.
    public static int sumDigits (long n) {
        int total = 0;
        while (n != 0) {
            total += n%10;
            n /= 10;
        }
        return total;
    }

    /* 3.
    Write a method to compute the following series
    m(i) = 1/3 + 2/4 + 3/5 + ... + i/(i+2)
    and print every i and m(i) from 1 up to 20 in a table */
    public static void Problem3Series (int end) {
        System.out.println("i\t\tm(i)");

        double total = 0;
        for (int i = 1; i <= end; i++) {
            total += (double) i / (i+2);
            System.out.printf("%d\t\t%.4f\n", i, total);
        }
    }
}

class Problem4 {
    // 4.
    // fill the missing parts according to the comments
    public static void main () {
        //create an array for storing 100 integers
        int[] array = new int[100];

        //initialize the array with numbers 2, 4, ..., 200
        for (int i = 0; i < 100; i++) {
            array[i] = 2*(i+1);
        }

        //print the contents of the array in reverse order:
        //200, 198, ..., 2
        for (int i = 99; i >= 0; i--) {
            System.out.printf("%d", array[i]);
            if (i != 0) System.out.print(", ");
        }
    }
}

class Problem5 {
    // 5.
    // Find the output printed by this program
    public static void main () {
        char[] str = {'m', 'e', 'r', 'a', 'p', 'i'};
        int p = str.length - 1;

        // changes char array to {'i', 'p', 'a', 'a', 'p', 'i'}
        for (int i = 0; i <= p; i++) {
            str[i] = str[p-i];
        }

        // prints every char in char array 1 by 1
        // "ipaapi"
        for (char c: str) {
            System.out.print(c);
        }

        // next line
        System.out.println();

        // k = 1
        // k is multiplied by 4 until it is more than 100
        // i.e. k = 4^n with n being the smallest integer such that 4^n > 100
        // k = 256 after this block is run
        int k = 1;
        do {
            k = k*4;
        } while (k < 100);

        // prints k
        // "k: 256"
        System.out.println("k: " + k);

        /* final output:
        ipaapi
        k: 256
         */
    }
}


class Problem6 {
    // 6a.
    public static void codeA () {
        int max = 0;
        max(1, 2, max);
        System.out.println(max);
    }

    public static void max (int v1, int v2, int max) {
        // intelliJ is yelling at me to use Math.max instead
        if (v1 > v2) max = v1;
        else max = v2;
    }


    // 6b.
    public static void codeB () {
        int i = 1;
        while (i <= 6) {
            i++;
        }
    }

    public static void method1 (int i, int num) {

    }


    // 6c.
    public static void codeC () {

    }

    public static void nPrintln (String message, int n) {

    }


    // 6d.
    public static void codeD () {

    }

    public static void method2 (int i) {

    }
}