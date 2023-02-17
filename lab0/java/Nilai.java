import java.util.Scanner;

class Nilai {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama Mahasiswa: ");
        String nama = input.nextLine();
        System.out.print("Masukkan lab0.java.Nilai Asli: ");
        int nilai_awal = input.nextInt();
        System.out.print("Masukkan Durasi: ");
        int durasi = input.nextInt();

        float nilai_akhir =
            (durasi < 60) ? 1.2f * nilai_awal :
            (durasi <= 70) ? nilai_awal :
            (durasi < 90) ? 0.75f * nilai_awal :
            (durasi <= 100) ? 0.5f * nilai_awal : 0.2f * nilai_awal;

        System.out.print(nama + " mendapatkan nilai akhir " + nilai_akhir);
    }
}
