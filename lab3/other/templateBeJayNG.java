import java.util.Scanner;

class templateBeJayNG {
    private static final Scanner input = new Scanner(System.in);
    private static String[] kumpulanNamaMahasiswa;
    private static String[] kumpulanNamaMatkul;
    private static int[][] score;
    private static int banyakMatkul;

    // main from template
    public static void main(String[] args) {
        init();

        while (true) {
            printMenu();
            System.out.print("Masukkan pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1 -> menambahMahasiswa();
                case 2 -> menghapusMahasiswa();
                case 3 -> mencetakTabel();
                case 4 -> mencetakNilai();
                case 0 -> {
                    System.out.println("Terima kasih telah menggunakan BeJayNG!");
                    System.exit(0);
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void insertRow (int[] insertedRow, String namaMahasiswa) {
        // Insert row baru ke dalam array 2D score dan kumpulanNamaMahasiswa

        // copy score with 1 extra array and insert insertedRow to the end
        int[][] newScore = new int[score.length+1][banyakMatkul];
        for (int i = 0; i < score.length; i++) {
            newScore[i] = score[i];
        }
        newScore[newScore.length-1] = insertedRow;

        // copy kumpulannamaMahasiswa with 1 extra element and insert namaMahasiswa to the end
        String[] newMahasiswa = new String[kumpulanNamaMahasiswa.length+1];
        for (int i = 0; i < kumpulanNamaMahasiswa.length; i++) {
            newMahasiswa[i] = kumpulanNamaMahasiswa[i];
        }
        newMahasiswa[newMahasiswa.length-1] = namaMahasiswa;

        // update score and kumpulanNamaMahasiswa
        score = newScore;
        kumpulanNamaMahasiswa = newMahasiswa;
    }

    static void init() {
        System.out.println("Selamat datang di BeJayNG!");
        System.out.println("==============Initial Input==============");

        // input
        System.out.print("Masukkan jumlah mata kuliah: ");
        banyakMatkul = input.nextInt();
        input.nextLine();

        // initializer kumpulanNamaMatkul
        // put names in kumpulanNamaMatkul
        kumpulanNamaMatkul = new String[banyakMatkul];
        for (int i = 0; i < banyakMatkul; i++) {
            System.out.print("Masukkan nama mata kuliah: ");
            String next = input.nextLine();
            kumpulanNamaMatkul[i] = next;
        }

        // init score and kumpulanNamaMahasiswa
        score = new int[0][banyakMatkul];
        kumpulanNamaMahasiswa = new String[0];
    }

    static void printMenu() {
        System.out.println("""
        ==============Menu==============
        [1] Menambah Mahasiswa
        [2] Menghapus Mahasiswa
        [3] Mencetak Tabel
        [4] Mencetak Nilai
        [0] Keluar""");
    }

    static void menambahMahasiswa() {
        System.out.println("==============Menambah Mahasiswa==============");
        // Menambahkan mahasiswa baru ke dalam array 2D score dan kumpulanNamaMahasiswa

        // initialize array nilai dan string
        int[] nilaiMahasiswa = new int[banyakMatkul];
        String namaMahasiswa;

        // input nama
        System.out.print("Masukkan nama mahasiswa: ");
        namaMahasiswa = input.nextLine();

        // input nilai
        for (int i = 0; i < banyakMatkul; i++) {
            System.out.printf("Masukkan nilai %s: ", kumpulanNamaMatkul[i]);
            nilaiMahasiswa[i] = input.nextInt();
            input.nextLine();
        }

        // add to score
        insertRow(nilaiMahasiswa, namaMahasiswa);

        // output message
        System.out.printf("Nilai mahasiswa bernama %s berhasil diinput ke BeJayNG\n", namaMahasiswa);
    }

    static void menghapusMahasiswa() {
        System.out.println("==============Menghapus Mahasiswa==============");

        // Menghapus mahasiswa dari array 2D score dan kumpulanNamaMahasiswa
        System.out.print("Masukkan nama mahasiswa: ");
        String mahasiswaHapus = input.nextLine();

        // set idx to invalid
        int idx = -1;
        // find idx of nama mahasiswa, case insensitive
        for (int i = 0; i < kumpulanNamaMahasiswa.length; i++) {
            if (mahasiswaHapus.equalsIgnoreCase(kumpulanNamaMahasiswa[i])) {idx = i; break;}
        }
        // check if idx is valid, remove from database if so.
        if (idx != -1) {
            // valid
            removeElement(idx);
        }
        else{
            // invalid
            System.out.println("Mahasiswa tersebut tidak ada di database.");
        }
    }

    public static void removeElement(int index) {
        // Kamu bisa gunakan method ini untuk menghapus mahasiswa dari array 2D score dan kumpulanNamaMahasiswa

        // print output
        System.out.printf("Mahasiswa bernama %s telah dihapus dari BeJayNG\n", kumpulanNamaMahasiswa[index]);

        // create copy of score with length-1 -> newScore
        int[][] newScore = new int[score.length-1][banyakMatkul];
        // copy everything from score except for index
        // up until index
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < banyakMatkul; j++){
                newScore[i][j] = score[i][j];
            }
        }
        // index + 1 until end
        for (int i = index; i < newScore.length; i++) {
            for (int j = 0; j < banyakMatkul; j++){
                newScore[i][j] = score[i+1][j];
            }
        }
        score = newScore;

        // same thing for kumpulanNamaMahasiswa
        String[] newMahasiswa = new String[kumpulanNamaMahasiswa.length-1];
        for (int i = 0; i < index; i++) {
            newMahasiswa[i] = kumpulanNamaMahasiswa[i];
        }
        for (int i = index; i < newScore.length; i++) {
            newMahasiswa[i] = kumpulanNamaMahasiswa[i+1];
        }
        kumpulanNamaMahasiswa = newMahasiswa;
    }

    static void mencetakTabel() {
        System.out.println("==============Mencetak Tabel==============");

        // Mencetak tabel nilai berdasarkan urutan mahasiswa ketika diinput

        // print top row
        System.out.printf("%-20s", "Nama");
        for (int i = 0; i < banyakMatkul; i++) {
            System.out.printf("%-20s", kumpulanNamaMatkul[i]);
        }
        System.out.println();

        // print the rest
        for (int m = 0; m < kumpulanNamaMahasiswa.length; m++) {
            for (int i = -1; i < banyakMatkul; i++) {
                if (i == -1) {
                    System.out.printf("%-20s", kumpulanNamaMahasiswa[m]);
                }
                else {
                    System.out.printf("%-20d", score[m][i]);
                }
            }
            System.out.println();
        }
    }

    static void mencetakNilai() {
        System.out.println("==============Mencetak Nilai==============");
        // Mencetak nilai matakuliah seorang siswa

        System.out.print("Masukkan nama mahasiswa: ");
        String mahasiswaCetak = input.nextLine();

        int idxNama = -1;
        for (int i = 0; i < kumpulanNamaMahasiswa.length; i++) {
            if (mahasiswaCetak.equalsIgnoreCase(kumpulanNamaMahasiswa[i])) {idxNama = i; break;}
        }
        if (idxNama == -1) {
            System.out.println("Mahasiswa tersebut tidak ada di database.");
            return;
        }

        System.out.print("Masukkan nama mata kuliah: ");
        String matkulCetak = input.nextLine();

        int idxMatkul = -1;
        for (int i = 0; i < kumpulanNamaMatkul.length; i++) {
            if (matkulCetak.equalsIgnoreCase(kumpulanNamaMatkul[i])) {idxMatkul = i; break;}
        }
        if (idxMatkul == -1) {
            System.out.println("Mata kuliah tersebut tidak ada di database.");
            return;
        }

        System.out.printf("Nilai %s di mata kuliah %s adalah %d\n",
                kumpulanNamaMahasiswa[idxNama], kumpulanNamaMatkul[idxMatkul], score[idxNama][idxMatkul]);
    }
}