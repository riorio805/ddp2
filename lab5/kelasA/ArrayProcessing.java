import java.util.Scanner;

public class ArrayProcessing {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[] intArray = new int[10];
        String selection = "";

        while (! selection.equals("0")) {
            printMenu();
            selection = input.nextLine();
            switch (selection) {
                case "0" -> System.out.println("Bye!");
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
        }

        input.close();
    }

    public static int[] generateArray (int length) {
        int[] out = new int[length];
        for (int i = 0; i < length; i++) {
            out[i] = (int) (1000 * Math.random()) + 1;
        }
        return out;
    }

    public static int linearSearch (int[] array, int n) {
        return linearSearchRec(array, n, 0);
    }

    private static int linearSearchRec (int[] array, int n, int index) {
        if (index == array.length) return -1;
        if (array[index] == n) return index;
        return linearSearchRec(array, n, ++index);
    }

    public static void selectionSort (int[] array) {
        for (int sortedIdx = 0; sortedIdx < array.length - 1; sortedIdx++) {
            int minIndex = sortedIdx;
            for (int nextIdxs = sortedIdx + 1; nextIdxs < array.length; nextIdxs++) {
                if (array[minIndex] > array[nextIdxs]) minIndex = nextIdxs;
            }
            if (minIndex != sortedIdx) {
                int temp = array[minIndex];
                array[minIndex] = array[sortedIdx];
                array[sortedIdx] = temp;
            }
        }
    }

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
