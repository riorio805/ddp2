/*
Additional challenge #1:
Write a complete program to print your own name 100 times with line number
*/
import java.util.Scanner;

class NameDuplication {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What's your name: ");
        String name = input.nextLine();
        System.out.println("Here's your name 100 times: ");

        for (int i = 1; i <= 100; i++){
            System.out.println(i + " " + name);
        }
    }
}
