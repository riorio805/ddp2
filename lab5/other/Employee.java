package ddp2.lab5.other;

public class Employee {
    String nama;
    int pengalamanKerja;
    double gaji, netWorth;
    boolean status;
    String jabatan;

    /**
     Create an employee with name <b>'nama'</b> and specialization <b>'spec'</b>.
     Specialization is one of three titles:
     "Secretary" ,"Engineer", "Manager"

     @param nama the name of the employee
     @param spec specialization of the employee
     */
    public Employee (String nama, String spec) {
        this.nama = nama;
        this.pengalamanKerja = 0;
        this.netWorth = 0;
        this.jabatan = "junior";
        this.status = true;
        this.gaji = switch (spec.toLowerCase()) {
            case "secretary" -> 3000000;
            case "engineer" -> 4000000;
            case "manager" -> 2000000;
            default -> 0;
        };
    }

    /**
     Base string to be added by children of this class.
     @return a string
     */
    public String toString() {
        return String.format("""
            - Nama: %s
            Pengalaman Kerja: %d
            Status: %s
            NetWorth: Rp%.2f
            Jabatan: %s
            """, this.nama, this.pengalamanKerja, this.status, this.netWorth, this.jabatan);
    }

    public void nextYears (int years) {
        // Cek sudah pensiun
        if (! this.status) return;

        for (int i = 0; i < years; i++) {
            // pengalamanKerja ++ 1 tahun
            this.pengalamanKerja++;

            // Update jabatan
            this.jabatan =
                    (this.pengalamanKerja <= 5) ? "Junior" :
                    (this.pengalamanKerja <= 10) ? "Senior" :
                    (this.pengalamanKerja <= 15) ? "Expert" : "Pensiun";

            // Update status
            // Cek status, break jika sudah pensiun
            this.status = ! this.jabatan.equalsIgnoreCase("Pensiun");
            if (! this.status) return;

            // Calculate gaji (x2 if senior, x3 if expert)
            double gajiSekarang = this.gaji *
                switch (this.jabatan.toLowerCase()) {
                    case "junior" -> 1;
                    case "senior" -> 2;
                    case "expert" -> 3;
                    default -> 0;
                };

            // Add gaji to netWorth
            this.netWorth += gajiSekarang;

            // Do role-exclusive stuff
            extraThings();
        }
    }

    /**
     Empty method to be overridden by other classes that extend this class.
     */
    void extraThings () {}
}
