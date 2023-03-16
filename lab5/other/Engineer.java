package ddp2.lab5.other;

public class Engineer extends Employee{
    final int banyakSideJobs;

    // test case
    public static void main(String[] args) {
        Engineer test = new Engineer("test", 8);
        System.out.println(test);
        test.nextYears(7);
        System.out.println(test);
        test.nextYears(7);
        System.out.println(test);
        test.nextYears(4);
        System.out.println(test);
    }


    /**
     Create an Engineer with name <b>'nama'</b>.

     @param nama the name of the employee
     @param banyakSideJobs amount of side jobs
     */
    public Engineer(String nama, int banyakSideJobs) {
        super(nama, "engineer");
        this.banyakSideJobs = banyakSideJobs;
    }

    public String toString() {
        return super.toString() + String.format("""
            Role: Engineer
            Banyak SideJobs: %d
            """, banyakSideJobs);
    }

    // extraThings() now adds (500000 * banyakSideJobs) to netWorth every year
    @Override
    void extraThings() {
        this.netWorth += 500000 * banyakSideJobs;
    }
}
