package ddp2.lab5.other;
import java.util.ArrayList;
import java.util.Scanner;

public class PacilWorks {
    static final Scanner input = new Scanner(System.in);
    static ArrayList<Employee> daftarEmployee = new ArrayList<>();
    static int tahunSekarang = 1;

    public static void main (String[] args) {
        System.out.println("Selamat datang di PacilWorks!");
        for ( boolean quit = false; !quit;) {
            System.out.printf("Sekarang tahun %d!\n", tahunSekarang);
            printMenu();
            printBatas();

            System.out.print("Input: ");
            String pilihan = input.nextLine();

            switch (pilihan.trim()) {
                case "*" -> {
                    System.out.println("Terima kasih telah menggunakan layanan PacilWorks ~ !");
                    quit = true;
                }
                case "1" -> printEmployees();
                case "2" -> addEmployee();
                case "3" -> simulateYears();
                default -> System.out.println("Input tidak valid.");
            }

            printBatas();
        }

        input.close();
    }

    public static void printMenu () {
        System.out.println("""
            Silakan pilih salah satu opsi berikut:
            [1] Daftar Karyawan
            [2] Tambah Karyawan
            [3] Simulasi n-tahun berikutnya
            [*] Keluar""");
    }

    public static void printBatas () {
        System.out.println("=".repeat(64));
    }

    private static void printEmployees() {
        if (daftarEmployee.size() == 0) {
            System.out.println("PacilWorks tidak memiliki karyawan :(");
            return;
        }

        for (Employee e : daftarEmployee) {
            System.out.println(e);
        }
    }

    private static void addEmployee () {
        String role = null, nama = "";

        boolean validRole = false;
        while (!validRole) {
            System.out.print("Masukkan role employee: ");
            role = input.nextLine().toLowerCase();
            switch (role) {
                case "?" -> System.out.println("Roles: Engineer, Secretary, Manager");
                case "engineer", "secretary", "manager" -> validRole = true;
                default -> System.out.println("Not a valid role (\"?\" for roles)");
            }
        }

        while (nama.isEmpty()) {
            System.out.print("Nama: ");
            nama = input.nextLine();
        }

        Employee employee;
        switch (role) {
            case "engineer" -> {
                int banyakSideJobs;
                while (true) {
                    System.out.print("Banyak Side Jobs: ");
                    try {
                        banyakSideJobs = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                        continue;
                    }
                    if (banyakSideJobs <= 0) {
                        System.out.println("Invalid input.");
                        continue;
                    }

                    break;
                }
                employee = new Engineer(nama, banyakSideJobs);
            }
            case "secretary" -> {
                double tunjangan;
                while (true) {
                    System.out.print("Banyak Tunjangan: ");
                    try {
                        tunjangan = Double.parseDouble(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                        continue;
                    }
                    if (tunjangan < 0) {
                        System.out.println("Invalid input.");
                        continue;
                    }

                    break;
                }
                employee = new Secretary(nama, tunjangan);
            }
            case "manager" -> {
                employee = new Manager(nama, 1.25);
            }
            default -> {
                return;
            }
        }

        daftarEmployee.add(employee);
    }

    private static void simulateYears () {
        int tahun;
        while (true) {
            System.out.print("Masukkan banyak tahun yang ingin disimulasikan: ");
            try {
                tahun = Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }
            if (tahun < 1 || tahun > 7) {
                System.out.println("Invalid input.");
                continue;
            }
            break;
        }
        System.out.printf("%d tahun telah berlalu...\n", tahun);
        tahunSekarang += tahun;
        for (Employee e: daftarEmployee) {
            e.nextYears(tahun);
        }
    }

}
