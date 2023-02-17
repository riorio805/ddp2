/*
Additional challenge #2:
Do the same thing as #1 but only output prime numbers up to 100
*/
import java.util.Scanner;

class PrimeName {
    static boolean PrimeTest (int num){
        if (num < 2){return false;}

        for (int i = 2; i <= Math.round(Math.sqrt(num)); i++){
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What's your name: ");
        String name = input.nextLine();
        System.out.println("Here's your name \"100\" times: ");

        for (int i = 1; i <= 100; i++){
            if (PrimeTest(i)) {
                System.out.println(i + " " + name);
            }
        }
    }
}