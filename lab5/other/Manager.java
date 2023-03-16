package ddp2.lab5.other;

public class Manager extends Employee {
    double raise;

    /**
     * Create a Manager with name <b>'nama'</b>.
     *
     * @param nama the name of the employee
     */
    public Manager(String nama, double raise) {
        super(nama, "manager");
        this.raise = raise;
    }

    public String toString() {
        return super.toString() + "Role: Manager\n";
    }

    // Override nextYear() to not change jabatan, and calculate gaji (different formula)
    public void nextYears (int years) {
        for (int i = 0; i < years; i++) {
            this.pengalamanKerja++;

            // Update jabatan
            this.jabatan =
                (this.pengalamanKerja <= 5) ? "Junior" :
                (this.pengalamanKerja <= 10) ? "Senior" :
                (this.pengalamanKerja <= 15) ? "Expert" : "Pensiun";

            // Update status
            this.status = ! this.jabatan.equalsIgnoreCase("Pensiun");

            // Calculate gaji (1.25 * previous gaji)
            this.gaji *= raise;

            // Add gaji to netWorth
            this.netWorth += this.gaji;
        }
    }
}
