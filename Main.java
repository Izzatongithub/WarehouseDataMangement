// Main Program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedListFile fileManager = new LinkedListFile();
        FileQueue fileQueue = new FileQueue();
        FileStack fileStack = new FileStack();
        FileTree fileTree = new FileTree();
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah File");
            System.out.println("2. Hapus File");
            System.out.println("3. Tampilkan Semua File");
            System.out.println("4. Cari File Berdasarkan Nama");
            System.out.println("5. Urutkan File Berdasarkan Nama");
            System.out.println("6. Tambahkan ke Antrian Pengiriman");
            System.out.println("7. Kirim File dari Antrian");
            System.out.println("8. Tampilkan Riwayat File yang Telah Dikirim");
            System.out.println("9. Tambah Kategori");
            System.out.println("10. Tambah File ke Kategori");
            System.out.println("11. Tampilkan Struktur Kategori dan File");
            System.out.println("12. Keluar");
            System.out.print("Pilih menu: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan ID File: ");
                    int idFile = scanner.nextInt();
                    scanner.nextLine(); // Konsumsi newline
                    System.out.print("Masukkan Nama File: ");
                    String namaFile = scanner.nextLine();
                    System.out.print("Masukkan Tanggal Dibuat (format bebas): ");
                    String tglFileDibuat = scanner.nextLine();
                    fileManager.addFile(idFile, namaFile, tglFileDibuat);
                    break;

                case 2:
                    System.out.print("Masukkan ID File yang akan dihapus: ");
                    int deleteId = scanner.nextInt();
                    fileManager.deleteFile(deleteId);
                    break;

                case 3:
                    fileManager.displayFiles();
                    break;

                case 4:
                    System.out.print("Masukkan Nama File yang dicari: ");
                    String searchName = scanner.nextLine();
                    fileManager.searchFile(searchName);
                    break;

                case 5:
                    fileManager.sortFilesByName();
                    break;

                case 6:
                    System.out.print("Masukkan ID File untuk antrian pengiriman: ");
                    int queueId = scanner.nextInt();
                    scanner.nextLine();
                    FileNode fileToQueue = fileManager.searchFileById(queueId);
                    if (fileToQueue != null) {
                        fileQueue.enqueue(new FileNode(fileToQueue.idFile, fileToQueue.namaFile, fileToQueue.tglFileDibuat));
                    }
                    break;

                case 7:
                    FileNode sentFile = fileQueue.dequeue();
                    if (sentFile != null) {
                        fileStack.push(new FileNode(sentFile.idFile, sentFile.namaFile, sentFile.tglFileDibuat));
                    }
                    break;

                case 8:
                    fileStack.displayStack();
                    break;

                case 9:
                    System.out.print("Masukkan Nama Kategori Baru: ");
                    String categoryName = scanner.nextLine();
                    System.out.print("Masukkan Nama Kategori Induk: ");
                    String parentCategory = scanner.nextLine();
                    fileTree.addCategory(parentCategory, categoryName);
                    break;

                case 10:
                    System.out.print("Masukkan Nama File untuk Kategori: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Masukkan Tanggal File Dibuat: ");
                    String fileDate = scanner.nextLine();
                    System.out.print("Masukkan Nama Kategori: ");
                    String categoryParent = scanner.nextLine();
                    fileTree.addFile(categoryParent, fileName, fileDate);
                    break;

                case 11:
                    fileTree.displayTree();
                    break;

                case 12:
                    System.out.println("Keluar dari program.");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }
}

