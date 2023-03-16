package ddp2.lab5.other;

public class Secretary extends Employee {
    final double tunjangan;

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

    public String toString() {
        return super.toString() + String.format("""
            Role: Secretary
            Banyak Tunjangan: %.2f
            """, tunjangan);
    }

    // extraThings() now adds tunjangan to netWorth every year
    @Override
    void extraThings() {
        this.netWorth += tunjangan;
    }
}
