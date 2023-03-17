package ddp2.lab5.other;

public class Secretary extends Employee {
    final double tunjangan;

    // test case
    public static void main(String[] args) {
        Secretary test = new Secretary("test", 1000000);
        while (test.status) {
            System.out.println(test);
            test.nextYears( (int) (4 * Math.random()) + 1 );
        }
        System.out.println(test);
    }

    /**
     * <p> Create a Secretary with name <b>'nama'</b>.
     * Secretary requires an additional </p>
     *
     * @param nama the name of the employee
     */
    public Secretary(String nama, double tunjangan) {
        super(nama, "secretary");
        this.tunjangan = tunjangan;
    }

    void calculateGaji () {
        // normal calculateGaji
        super.calculateGaji();

        // Secretary adds tunjangan to netWorth every year
        this.netWorth += tunjangan;
    }
}
