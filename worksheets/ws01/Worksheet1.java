package ddp2.worksheets.ws01;

class Worksheet1 {
    /*
    1. Edit-Compile-Debug, IDE
    2. Algorithm
    3. Operating System
    4.
    a. //print 6
    b. //print -1
    c. //print true
    d. //print false
    e. //print c680
    */

    // 5. print integers from 1 up to 9999999 which are multiples of 3 or 5
    // while
    static void PrintInt (int type) {
        // type: 0 = while loop, 1 = for loop, 2 =
        int i = 1;
        switch (type) {
            case 0 -> { //while loop
                while (i <= 9999999) {
                    if (i % 3 == 0 || i % 5 == 0) {
                        System.out.print(i + " ");
                    }
                    i++;
                }
            }
            case 1 -> { // for loop
                for (i = 1; i <= 9999999; i++) {
                    if (i % 3 == 0 || i % 5 == 0) {
                        System.out.print(i + " ");
                    }
                }
            }
            case 2 -> { // do while loop
                do {
                    if (i % 3 == 0 || i % 5 == 0) {
                        System.out.print(i + " ");
                    }
                    i++;
                }
                while (i <= 9999999);
            }
            default -> System.out.println("invalid type");
        }
    }

    // 6. (a)

    // 7. i = 2, j = 7  ==> i=3 j=400
    public static void Problem7 (int i, int j) {
        switch (i++) {
            case 2: j++;
            case 3: j=j*50;
            case 4: i=i++; break;
            case 5: j=j+i;
            default:
                System.out.println("aaaaa");
                j = j*5;
                i = i*2;
        }
        System.out.printf("i=%d j=%d\n",i,j);
    }

    // no break
    public static void Problem8a (int day) {
        switch (day % 7) {
            case 0: System.out.println("Sunday"); break;
            case 1: System.out.println("Monday"); break;
            case 2: System.out.println("Tuesday"); break;
            case 3: System.out.println("Wednesday"); break;
            case 4: System.out.println("Thursday"); break;
            case 5: System.out.println("Friday"); break;
            case 6: System.out.println("Saturday"); break;
            default: System.out.println("not a day");
        }
    }

    // with "break"
    public static void Problem8b (int day) {
        switch (day % 7) {
            case 0 -> System.out.println("Sunday");
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            case 4 -> System.out.println("Thursday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            default -> System.out.println("not a day");
        }
    }


    public static void main (String[] args){
        PrintInt(0);
        Problem7(2, 7);
        Problem8a(6);
        Problem8b(4);
    }
}


