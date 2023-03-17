package ddp2.lab5.other;

public class Manager extends Employee {
    double raise;

    // test case
    public static void main(String[] args) {
        Manager test = new Manager("test", 1.2);
        while (test.status) {
            System.out.println(test);
            test.nextYears( (int) (4 * Math.random()) + 1 );
        }
        System.out.println(test);
    }

    /**
     * Create a Manager with name <b>'nama'</b>.
     *
     * @param nama the name of the employee
     */
    public Manager(String nama, double raise) {
        super(nama, "manager");
        this.raise = Math.max(1.0, Math.min(1.25, raise));
    }

    public String toString() {
        return super.toString() + "Role: Manager\n";
    }

    void calculateGaji() {
        // Calculate gaji (1.25 * previous gaji)
        this.gaji *= raise;

        // Add gaji to netWorth
        this.netWorth += this.gaji;
    }
}
