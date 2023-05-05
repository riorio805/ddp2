package ddp2.kelasA.worksheets.ws04;
import java.util.Date;
import java.math.*;

public class Worksheet4 {
    public static void main(String[] args) {
        // problem 1
        Point p = new Point(100, 200);
        int[] a = new int[6];
        int d = 80;
        System.out.println("p: " + p); //Print: Titik(100,200)
        System.out.println("a[3]: " + a[3]); //Print: 0
        m(p, d, a);
        System.out.println("p: " + p); //Print: Titik(180,280)
        System.out.println("d: " + d); //Print: 80
        System.out.println("a[3]: " + a[3]); //Print: 27


        // problem 2
        Chaining.main(null);


        // problem 3
        Test1.main(null);   //Output: null
        Test2.main(null);   //Output: Thu Jan 01 09:07:34 WIB 1970 (7654321 mills since unix epoch)


        // problem 5
        AboutInterface.main(null);
    }

    public static void m(Point pp, int dd, int[] aa) {
        pp.move(dd, dd);    // move pp by dd to the right and up
        dd = 115;           // doesn't do anything
        aa[3] = 27;         // 4th element in aa is 27
    }
}

class Point {
    private int x, y;
    Point() { this(0,0); }
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    public String toString() {
        return "Titik(" + x + "," + y + ")";
    }
}



class Chaining {
    private int[] ar;
    public Chaining() {
        ar = new int[]{1,2,3,4};
    }
    public Chaining swap(int i, int j) {
        int temp = ar[i]; ar[i] = ar[j]; ar[j] = temp; return this;
    }
    public Chaining twice() {
        for (int i = 0; i < ar.length; i++) ar[i] *= 2;
        return this;
    }
    public Chaining cetak() { //prints the array
        for (int i=0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
        return this;
    }
    /*
        Expected output:
        1 2 3 4
        1 3 2 4
        2 6 4 8
    */
    public static void main(String[] args) {
        Chaining ob = new Chaining();
        ob.cetak().swap(1,2).cetak().twice().cetak();
    }
}

class Test1 {
    public static void main(String[] args) {
        Date date = null;
        m1(date);
        System.out.println(date);
    }
    public static void m1(Date date){
        date = new Date();
    }
}

class Test2 {
    public static void main(String[] args) {
        Date date = new Date(1234567);
        m1(date);
        System.out.println(date);
    }
    public static void m1(Date date){
        date.setTime(7654321);
    }
}


class LargeFactorial{
    public static void main(String[] args) {
        System.out.println("50! is:\n" + factorial(50));
        System.out.println("50! is:\n" + factorialRec(50));
    }
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(new BigInteger(i+""));
        }
        return result;
    }
    // 'tail' recursive
    public static BigInteger factorialRec(int n) {
        return factorialRec(n, BigInteger.ONE);
    }
    private static BigInteger factorialRec(int n, BigInteger result) {
        if (n == 0) return result;
        return factorialRec(n-1, result.multiply(new BigInteger(n+"")));
    }
}

