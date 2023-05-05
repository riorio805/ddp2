package ddp2.kelasA.worksheets.ws03;

public class Worksheet3 {
    public static void main (String[] args) {
        // Q1
        Circle c1 = new Circle(25);
        Circle c2 = new Circle(25);
        boolean condition = (c1 == c2);
        // What is the value of condition? Explain.
        System.out.println(condition);
        // Output:
        // false
        // Explanation:
        // c1 is different from c2 because they are different
        // objects, even though they are exactly the same.


        // Q2
        // double[] array1 = new double[10];


        // Q3

        int[] array1 = new int[25];
        // Code that will put values in array1
        // Random values from 1 to 100
        for (int i = 0; i < array1.length; i++) {
            array1[i] = (int) (Math.random() * 1001);
        }
        //
        int value = array1[0];
        for (int a = 1; a < array1.length; a++){
            if (array1[a] < value)
                value = array1[a];
        }
        // What is the result of value?
        System.out.println(value);
        // (b) value contains the lowest value in array1


        // Q4
        // Explanation at the bottom
        new TestBankAccount();
    }
}


class Circle {
    private final int radius;
    public Circle(int radius) {
        this.radius = radius;
    }
}


class BankAccount {
    public BankAccount(){
        this("ABC", 0);
        System.out.println("Panggil BankAccount()");
    }
    public BankAccount(String name, int balance) {
        System.out.println("Panggil BankAccount("+name+","+balance+")");
        this.name = name; this.balance = balance;
    }
    public void deposit(int amount) {
        balance = balance + amount;
    }
    public void withDraw(int amount) {
        if (balance >= amount) balance = balance - amount;
        System.out.println("Balance: " + balance);
    }
    public void cetakInfo() {
        System.out.println(name + " punya saldo " + balance);
    }
    private String name;
    private int balance;
}
class TestBankAccount {
    public TestBankAccount() {
        main(new String[0]);
    }
    public static void main(String[] args){
        BankAccount[] bank = new BankAccount[20]; //statement 1
        bank[0] = new BankAccount( "Arjuno", 1000); //statement 2
        bank[1] = new BankAccount(); //statement 3
        bank[0].withDraw(700); //statement 4
        bank[1].withDraw(500); //statement 5
        bank[1].deposit(200); //statement 6
        bank[0].cetakInfo(); //statement 7
        bank[1].cetakInfo(); //statement 8
    }
}

/*
4. Find all the output printed by this program and explain which statement(s) the output comes from.

Statement 1:
Creates an array of 20 null elements, put a reference in the variable bank.

Statement 2:
bank[0] is now a new BankAccount object created using the arg constructor,
with name = "Arjuno" and balance = 1000.
Inside the arg constructor, print "Panggil BankAccount({name},{balance})"

Statement 3:
bank[1] is now a new BankAccount object created using the no-arg constructor,
which calls the arg constructor with name = "ABC" and balance = 0.
After the arg constructor is called, print "Panggil BankAccount()"

Statement 4:
Withdraws 700 from bank[0]'s account. Since bank[0].balance >= 700, this succeeds and
the final balance of bank[0] is 300. Prints "Balance: {balance}", with balance = 300

Statement 5:
Withdraws 500 from bank[1]'s account. Since bank[1].balance < 500, this fails and
the final balance of bank[1] is unchanged. Prints "Balance: {balance}", with balance = 0

Statement 6:
Deposits 200 to bank[1]'s account. Final balance of bank[1] is 0 + 200 = 200.

Statement 7:
Prints info of bank[0]. The format is "{name} punya saldo {balance}", with
name = "Arjuno" and balance = 300

Statement 8:
Prints info of bank[1]. The format is "{name} punya saldo {balance}", with
name = "ABC" and balance = 200

Final output:
Panggil BankAccount(Arjuno,1000)        -> Statement 2
Panggil BankAccount(ABC,0)              -> Statement 3
Panggil BankAccount()                   -> Statement 3
Balance: 300                            -> Statement 4
Balance: 0                              -> Statement 5
Arjuno punya saldo 300                  -> Statement 7
ABC punya saldo 200                     -> Statement 8

 */