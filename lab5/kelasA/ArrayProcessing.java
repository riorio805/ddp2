import java.util.Scanner;

public class ArrayProcessing {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize array and selection
        int[] intArray = new int[0];
        String selection;

        do { // while (! selection.equals("0"))
            // Print menu and get input
            printMenu();
            selection = input.nextLine();

            // Switch depending on input
            switch (selection) {
                // Quit program
                case "0" -> System.out.println("Bye!");

                //
                case "1" -> {
                    int elementAmount;
                    System.out.println("How many elements?");
                    try {
                        elementAmount = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Sorry, invalid input");
                        break;
                    }
                    intArray = generateArray(elementAmount);
                }
                case "2" -> selectionSort(intArray);
                case "3" -> {
                    int targetElement;
                    System.out.print("Enter the value to look for: ");
                    try {
                        targetElement = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Sorry, invalid input");
                        break;
                    }
                    int result = linearSearch(intArray, targetElement);
                    if (result == -1) {
                        System.out.println("Not in the list");
                    }
                    else {
                        System.out.printf("Found at location %d\n", result);
                    }
                }
                case "4" -> {
                    for (int i = 0; i < intArray.length; i++) {
                        String idxStr = "[" + i + "]:";
                        System.out.printf("%-6s %4d \n", idxStr, intArray[i]);
                    }
                }
                default -> System.out.println("Sorry, invalid choice");
            }
        } while (! selection.equals("0"));

        input.close();
    }

    /**
     Method to generate an int Array of input length where every element
     is an integer from 1 to 1000 inclusive

     @param length the length of the array
     @return an integer Array with random numbers.
     */
    public static int[] generateArray (int length) {
        int[] out = new int[length];
        for (int i = 0; i < length; i++) {
            out[i] = (int) (1000 * Math.random()) + 1;
        }
        return out;
    }

    /**
     Method to search for a target in
     @param array input array
     @param n target integer to find in array
     @return -1 if target not in array, position of the target otherwise.
     */
    public static int linearSearch (int[] array, int n) {return linearSearchRec(array, n, 0);}

    /**
     Recursive method to find the target in an array using linear search.
     */
    private static int linearSearchRec (int[] array, int n, int index) {
        if (index == array.length) return -1;
        if (array[index] == n) return index;
        return linearSearchRec(array, n, ++index);
    }

    /**
     Method to sort the input array using selection sort
     @param array the array to be sorted
     */
    public static void selectionSort (int[] array) {
        for (int sortedIdx = 0; sortedIdx < array.length - 1; sortedIdx++) {

            int minIndex = sortedIdx;
            
            for (int nextIdxs = sortedIdx + 1; nextIdxs < array.length; nextIdxs++) {
                if (array[minIndex] > array[nextIdxs]) minIndex = nextIdxs;
            }

            // Swap to the lowest if current element is not the lowest
            if (minIndex != sortedIdx) {
                int temp = array[minIndex];
                array[minIndex] = array[sortedIdx];
                array[sortedIdx] = temp;
            }
        }
    }

    /**
     Method to print the menu of the program
     */
    public static void printMenu() {
        System.out.print("""
            
            Menu
            ====
            0: Quit
            1: Create a new list of random elements
            2: Sort the list using selection sort
            3: Find an element in the list using linear search
            4: Print the list
            
            Enter your choice:\s""");
    }
}
