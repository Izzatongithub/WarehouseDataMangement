public class LinkedListFile {
    private File head;

    public static class File {
        public File next;
        public File prev;

        private String nama;
        private String tanggalDibuat;
        private int size;
        private String format;

        public File(String nama, String tanggalDibuat, int size, String format) {
            this.nama = nama;
            this.tanggalDibuat = tanggalDibuat;
            this.size = size;
            this.format = format;
        }

        public boolean isEqual(String n) {
            return this.nama.equals(n);
        }

        public String toFormattedString(int index) {
            return String.format(
                "%d | %s " + "   Dibuat: %s " + "   Size: %d MB " + "   Format: %s\n" +
                "____________________________________________________",
                index, nama, tanggalDibuat, size, format
            );
        }
    }

    public void addFile(String nama, String tanggalDibuat, int size, String format) {
        File newFile = new File(nama, tanggalDibuat, size, format);
        if (head == null) {
            head = newFile;
        } else {
            File current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newFile;
            newFile.prev = current;
        }
    }

    public void bubbleSortBySize() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            File current = head;
            while (current.next != null) {
                if (current.size > current.next.size) {
                    swapFile(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void bubbleSortByName() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            File current = head;
            while (current.next != null) {
                if (current.nama.compareToIgnoreCase(current.next.nama) > 0) {
                    swapFile(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void bubbleSortByFormat() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            File current = head;
            while (current.next != null) {
                if (current.format.compareToIgnoreCase(current.next.format) > 0) {
                    swapFile(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void bubbleSortByDate() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            File current = head;
            while (current.next != null) {
                if (current.tanggalDibuat.compareTo(current.next.tanggalDibuat) > 0) {
                    swapFile(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void linearSearch(String nama) {
        File current = head;
        int index = 1;
        while (current != null) {
            if (current.nama.equalsIgnoreCase(nama)) {
                System.out.println("File ditemukan:");
                System.out.println(current.toFormattedString(index));
                return;
            }
            current = current.next;
            index++;
        }
        System.out.println("File dengan nama '" + nama + "' tidak ditemukan.");
    }

    public void searchByFormat(String format) {
        File current = head;
        int index = 1;
        boolean found = false;
        System.out.println("Hasil pencarian untuk format '" + format + "':");
        while (current != null) {
            if (current.format.equalsIgnoreCase(format)) {
                System.out.println(current.toFormattedString(index));
                found = true;
            }
            current = current.next;
            index++;
        }
        if (!found) {
            System.out.println("Tidak ada file dengan format '" + format + "'.");
        }
    }
    public void removeFile(String nama) {
        File current = head;
        while (current != null) {
            if (current.isEqual(nama)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                System.out.println("File dengan nama '" + nama + "' berhasil dihapus.");
                return;
            }
            current = current.next;
        }
        System.out.println("File dengan nama '" + nama + "' tidak ditemukan.");
    }

    public void printAll() {
        File current = head;
        int index = 1;
        while (current != null) {
            System.out.println(current.toFormattedString(index));
            current = current.next;
            index++;
        }
    }

    private void swapFile(File file1, File file2) {
        String tempNama = file1.nama;
        file1.nama = file2.nama;
        file2.nama = tempNama;

        String tempTanggal = file1.tanggalDibuat;
        file1.tanggalDibuat = file2.tanggalDibuat;
        file2.tanggalDibuat = tempTanggal;

        int tempSize = file1.size;
        file1.size = file2.size;
        file2.size = tempSize;

        String tempFormat = file1.format;
        file1.format = file2.format;
        file2.format = tempFormat;
    }

    public static void main(String[] args) {
        LinkedListFile daftarFile = new LinkedListFile();

        // Menambahkan file ke dalam daftar
        daftarFile.addFile("Dokumen1.txt", "2024-12-15", 120, "txt");
        daftarFile.addFile("Gambar1.png", "2024-12-14", 450, "png");
        daftarFile.addFile("Video1.mp4", "2024-12-13", 10240, "mp4");
        daftarFile.addFile("Presentasi.pdf", "2024-12-12", 300, "pdf");
        daftarFile.addFile("File1.pdf", "2024-12-13", 1478, "pdf");
        daftarFile.addFile("File2.pdf", "2024-12-13", 1814, "pdf");
        daftarFile.addFile("File3.pdf", "2024-12-17", 8835, "pdf");
        daftarFile.addFile("File4.png", "2024-12-15", 5633, "png");
        daftarFile.addFile("File5.pdf", "2024-12-10", 2262, "pdf");
        daftarFile.addFile("File6.mp4", "2024-12-22", 5518, "mp4");
        daftarFile.addFile("File7.xlsx", "2024-12-12", 2733, "xlsx");
        daftarFile.addFile("File8.pdf", "2024-12-18", 1485, "pdf");
        daftarFile.addFile("File9.txt", "2024-12-17", 1194, "txt");
        daftarFile.addFile("File10.pdf", "2024-12-15", 7395, "pdf");
        daftarFile.addFile("File11.mp4", "2024-12-06", 1569, "mp4");
        daftarFile.addFile("File12.docx", "2024-12-09", 2118, "docx");
        daftarFile.addFile("File13.pdf", "2024-12-14", 7105, "pdf");
        daftarFile.addFile("File14.txt", "2024-12-20", 4397, "txt");
        daftarFile.addFile("File15.mp4", "2024-12-06", 5048, "mp4");
        daftarFile.addFile("File16.mp4", "2024-12-21", 7962, "mp4");
        daftarFile.addFile("File17.mp4", "2024-12-24", 8685, "mp4");
        daftarFile.addFile("File18.txt", "2024-12-30", 5215, "txt");
        daftarFile.addFile("File19.pdf", "2024-12-17", 8450, "pdf");
        daftarFile.addFile("File20.pdf", "2024-12-26", 8873, "pdf");
        daftarFile.addFile("File21.docx", "2024-12-30", 9823, "docx");
        daftarFile.addFile("File22.mp4", "2024-12-21", 6923, "mp4");
        daftarFile.addFile("File23.mp4", "2024-12-21", 571, "mp4");
        daftarFile.addFile("File24.docx", "2024-12-10", 1403, "docx");
        daftarFile.addFile("File25.mp4", "2024-12-26", 4340, "mp4");
        daftarFile.addFile("File26.pdf", "2024-12-15", 5570, "pdf");
        daftarFile.addFile("File27.png", "2024-12-17", 1250, "png");
        daftarFile.addFile("File28.xlsx", "2024-12-19", 6401, "xlsx");
        daftarFile.addFile("File29.txt", "2024-12-29", 5556, "txt");
        daftarFile.addFile("File30.pdf", "2024-12-11", 9654, "pdf");
        daftarFile.addFile("File31.mp4", "2024-12-25", 6005, "mp4");
        daftarFile.addFile("File32.pdf", "2024-12-16", 3804, "pdf");
        daftarFile.addFile("File33.pdf", "2024-12-22", 8258, "pdf");
        daftarFile.addFile("File34.xlsx", "2024-12-06", 4763, "xlsx");
        daftarFile.addFile("File35.mp4", "2024-12-29", 10371, "mp4");
        daftarFile.addFile("File36.xlsx", "2024-12-12", 8965, "xlsx");
        daftarFile.addFile("File37.mp4", "2024-12-10", 2569, "mp4");
        daftarFile.addFile("File38.png", "2024-12-19", 6252, "png");
        daftarFile.addFile("File39.mp4", "2024-12-06", 216, "mp4");
        daftarFile.addFile("File40.pdf", "2024-12-19", 3215, "pdf");
        daftarFile.addFile("File41.pdf", "2024-12-13", 5817, "pdf");
        daftarFile.addFile("File42.docx", "2024-12-13", 7165, "docx");
        daftarFile.addFile("File43.docx", "2024-12-18", 55, "docx");
        daftarFile.addFile("File44.mp4", "2024-12-24", 1075, "mp4");
        daftarFile.addFile("File45.png", "2024-12-16", 1504, "png");
        daftarFile.addFile("File46.mp4", "2024-12-18", 190, "mp4");
        daftarFile.addFile("File47.png", "2024-12-17", 1253, "png");
        daftarFile.addFile("File48.png", "2024-12-27", 10455, "png");
        daftarFile.addFile("File49.pdf", "2024-12-05", 5333, "pdf");
        daftarFile.addFile("File50.pdf", "2024-12-20", 591, "pdf");
        

        // Menampilkan daftar file
        System.out.println("Daftar File Sebelum Sorting:");
        daftarFile.printAll();

        // Sorting berdasarkan ukuran
        System.out.println("\nSorting Berdasarkan Ukuran:");
        daftarFile.bubbleSortBySize();
        daftarFile.printAll();

        // Sorting berdasarkan nama
        System.out.println("\nSorting Berdasarkan Nama:");
        daftarFile.bubbleSortByName();
        daftarFile.printAll();

        // Sorting berdasarkan format
        System.out.println("\nSorting Berdasarkan Format:");
        daftarFile.bubbleSortByFormat();
        daftarFile.printAll();

        // Sorting berdasarkan tanggal
        System.out.println("\nSorting Berdasarkan Tanggal Dibuat:");
        daftarFile.bubbleSortByDate();
        daftarFile.printAll();

        // Pencarian file tertentu
        System.out.println("\nMencari file 'Gambar1.png':");
        daftarFile.linearSearch("Gambar1.png");
        
        // Pencarian berdasarkan format
        System.out.println("\nMencari file dengan format 'pdf':");
        daftarFile.searchByFormat("pdf");

        // Menghapus file tertentu
        System.out.println("\nMenghapus file 'Gambar1.png':");
        daftarFile.removeFile("Gambar1.png");

        // Menampilkan daftar setelah dihapus
        System.out.println("\nDaftar File Setelah Penghapusan:");
        daftarFile.printAll();
    }
}
