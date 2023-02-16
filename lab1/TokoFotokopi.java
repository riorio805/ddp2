package ddp2.lab1;
import java.util.Scanner;

public class TokoFotokopi {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);

        // input jumlah mahasiswa
        System.out.print("""
        Selamat datang di Toko Fotokopi Dek Depe!
        --------------------------------------------------------
        Masukkan jumlah mahasiswa yang ingin melakukan fotokopi:\s""");

        int jumlahMahasiswa = input.nextInt();
        double totalSemua = 0;

        // loop semua mahasiswa
        for (int c = 1; c <= jumlahMahasiswa; c++) {
            System.out.printf("--------------------DATA MAHASISWA %d--------------------\n", c);

            // input data dari user (nama, ipk, dan jumlah lembar)
            System.out.print("Nama: ");
            String namaMahasiswa = input.next();
            /*
            eksplanasi:
            ipkGroup adalah group seorang mahasiswa berdasarkan ipknya
            2* adalah untuk membuat batasnya semua integer ie. 3-4, 4-5, dst.
            Math.ceil adalah untuk menentukan group untuk switch case
            Math.min(8, ...) adalah untuk memastikan IPK <= 4
            (int) untuk type casting karena Math.ceil hasilnya long
            Contoh:
            IPK = 3.1 -> 6.2 -> 7 = ipkGroup
            IPK = 2.8 -> 5.6 -> 6 = ipkGroup
            IPK = 4.3 -> 8.6 -> 9 -> 8 = ipkGroup
            */
            System.out.print("IPK: ");
            int ipkGroup = (int) Math.min(8, Math.ceil(2* input.nextDouble()));

            System.out.print("Jumlah lembar: ");
            int jumlahLembar = input.nextInt();

            double totalSeorang = 555 * jumlahLembar; // harga sebelum diskon

            int diskon; // init nilai diskon yang diprint

            switch (ipkGroup){  // Dijamin input IPK selalu valid antara 0.0 - 4.0
                case 8 ->  {totalSeorang *= 0.5 ; diskon = 50;}  // 3.5 < IPK <= 4.0
                case 7 ->  {totalSeorang *= 0.65; diskon = 35;}  // 3.0 < IPK <= 3.5
                case 6 ->  {totalSeorang *= 0.75; diskon = 25;}  // 2.5 < IPK <= 3.0
                default -> {totalSeorang *= 0.9 ; diskon = 10;}  // IPK <= 2.5
            }
            System.out.printf("%s membayar seharga %.2f dengan diskon sebesar %d%%\n", namaMahasiswa, totalSeorang, diskon);

            totalSemua += totalSeorang;
        }

        // output terakhir
        System.out.printf("""
        ---------------------RINGKASAN DATA---------------------
        Hasil pendapatan yang diperoleh Toko Fotokopi dari %d mahasiswa adalah %.2f\s""", jumlahMahasiswa, totalSemua);
    }
}
