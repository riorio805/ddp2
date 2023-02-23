import java.util.Scanner;

public class PromoFotokopi {
    private static final Scanner input = new Scanner(System.in);

    public static void main (String[] args) {
        int select = 0;

        while (select != 3) {
            System.out.print("""
                
                Halo! Apa yang ingin kamu lakukan?
                [1] Buat kupon
                [2] Validasi kupon
                [3] Keluar
                Pilihan:\s""");
            try {
                select = Integer.parseInt(input.next());
            } catch (Exception e) {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                select = 0;
                continue;
            }

            switch (select) {
                case 1 -> {
                    System.out.print("Nama kupon: ");
                    String kupon = input.next();
                    System.out.printf("Kode kupon adalah: %s\n", generateKupon(kupon));
                }
                case 2 -> {
                    System.out.print("Kupon: ");
                    String kupon = input.next();
                    if (checkKupon(kupon)) System.out.println("Kupon yang diberikan valid");
                    else System.out.println("Kupon yang diberikan tidak valid");
                }
            }
        }
    }


    // the fucking recursion method that you absolutely "need"
    private static int Checksum (String str) {
        if (str.isEmpty()) return 0;
        return ( str.charAt(0) - 'A' + Checksum(str.substring(1))) % 26;
    }

    private static String generateKupon (String kupon) {
        kupon = kupon.toUpperCase();
        int total = Checksum(kupon);
        return kupon + (char) ('A' + total) + (char) ('A' + (2 * total % 26));
    }

    private static boolean checkKupon (String kupon) {
        int l = kupon.length();
        return kupon.equals(generateKupon(kupon.substring(0, l-2)));
    }
}
