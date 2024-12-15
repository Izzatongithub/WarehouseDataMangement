public class LinkedListFile
 {
    private File head;

    public static class File {
        public File next;
        public File prev;

        private String nama;
        private String dateModified;
        private int size;

        public File(String nama, String dateModified, int size) {
            this.nama = nama;
            this.dateModified = dateModified;
            this.size = size;
        }

        public boolean isEqual(String n) {
            return this.nama.equals(n);
        }

        public String toFormattedString(int index) {
            return String.format(
                "%d | %s " + "   Modified: %s " + "   Size: %d KB\n" +
                "____________________________________________________",
                index, nama, dateModified, size
            );
        }
    }

    public void addFile(String nama, String dateModified, int size) {
        File newFile = new File(nama, dateModified, size);
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
        if (head == null || head.next == null) {
            System.out.println("Daftar file kosong atau hanya memiliki satu elemen.");
            return;
        }
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
        if (head == null || head.next == null) {
            System.out.println("Daftar file kosong atau hanya memiliki satu elemen.");
            return;
        }
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

    public int getSize() {
        int size = 0;
        File current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
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

    // Menukar data antar dua file
    private void swapFile(File file1, File file2) {
        String tempNama = file1.nama;
        file1.nama = file2.nama;
        file2.nama = tempNama;

        String tempDate = file1.dateModified;
        file1.dateModified = file2.dateModified;
        file2.dateModified = tempDate;

        int tempSize = file1.size;
        file1.size = file2.size;
        file2.size = tempSize;
    }

    public static void main(String[] args) {
        LinkedListFile daftarFile = new LinkedListFile();

        // Menambahkan file ke dalam daftar
        daftarFile.addFile("Dokumen1.txt", "2024-12-15", 120);
        daftarFile.addFile("Gambar1.png", "2024-12-14", 450);
        daftarFile.addFile("Video1.mp4", "2024-12-13", 10240);

        // Menampilkan semua file
        System.out.println("Daftar File:");
        daftarFile.printAll();

        // Mengurutkan file berdasarkan size
        System.out.println("\nMengurutkan file berdasarkan size:");
        daftarFile.bubbleSortBySize();
        daftarFile.printAll();

        // Mengurutkan file berdasarkan nama
        System.out.println("\nMengurutkan file berdasarkan nama:");
        daftarFile.bubbleSortByName();
        daftarFile.printAll();

        // Pencarian file tertentu
        System.out.println("\nMencari file 'Gambar1.png':");
        daftarFile.linearSearch("Gambar1.png");

        // Menghapus file tertentu
        System.out.println("\nMenghapus file 'Gambar1.png':");
        daftarFile.removeFile("Gambar1.png");

        // Menampilkan daftar setelah penghapusan
        System.out.println("\nDaftar File Setelah Penghapusan:");
        daftarFile.printAll();
    }
}
