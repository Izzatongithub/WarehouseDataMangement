import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintingSystem system = new PrintingSystem();
        Stack stackHistory = new Stack();
        // Searching search = new Searching();
        Sorting sort = new Sorting();

        while (true) {
            System.out.println("\n================================================");
            System.out.println("||       DATANEST WAREHOUSE PERCETAKAN        ||");
            System.out.println("================================================");
            System.out.println("1. Tambah File");
            System.out.println("2. Display Tree");
            System.out.println("3. Display Antrian Percetakan");
            System.out.println("4. Process Percetakan");
            System.out.println("5. Display History Percetakan");
            System.out.println("6. Searching");
            System.out.println("7. Sorting");
            System.out.println("8. Exit");
            System.out.println("================================================");
            System.out.print("Pilih menu berdasarkan angka: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan Tipe Customer (Non-Member/Member-Gold/Member-Silver): ");
                    String type = scanner.nextLine();
                    System.out.print("Masukkan Nama Pemesan: ");
                    String namaPemesan = scanner.nextLine(); 
                    System.out.print("Masukkan Nama File: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Masukkan Ukuran File: ");
                    double fileSize = scanner.nextDouble();
                    scanner.nextLine(); // Konsumsi newline
                    System.out.print("Masukkan Format File: ");
                    String fileFormat = scanner.nextLine();
                    System.out.print("Masukkan Tanggal Order (DD-MM-YYYY): ");
                    String orderDate = scanner.nextLine();
                
                    // Membuat objek File dengan atribut baru
                    File file = new File(type, fileName, fileSize, fileFormat, orderDate, namaPemesan);
                    system.addFile(type, file); // Menambahkan file ke sistem
                    break;
            
                case 2:
                    System.out.println("Data File:");
                    system.displayTree();
                    break;

                case 3:
                    System.out.println("Hasil Antrian:");
                    system.displayQueue();
                    break;

                case 4:
                    System.out.println("\n=== Memproses Print Queue ===");
                        while (!system.printQueue.isEmpty()) {
                            File result = system.printQueue.dequeue();
                            stackHistory.push(result);
                            System.out.println("Processed: " + result.nama);
                        }
                    break;

                case 5:
                    System.out.println("\nPrint History:");
                        stackHistory.display();
                    break;
                
                // case 6:
                //     System.out.println("\n=== Pencarian File ===");
                //     System.out.print("Masukkan nama file untuk mencari: ");
                //     String searchName = scanner.nextLine();
                //     search.linearSearch(system.nonMember.files.head, searchName);
                //     break;

                case 6:
                    System.out.println("\n=== Pencarian File ===");
                    while (true) {
                        System.out.println("Pilih kategori untuk pencarian:");
                        System.out.println("1. Non-Member");
                        System.out.println("2. Member-Gold");
                        System.out.println("3. Member-Silver");
                        System.out.println("4. Kembali ke Menu Utama");
                        System.out.print("Pilihan Anda: ");
                        int categoryChoice = scanner.nextInt();
                        scanner.nextLine(); // Konsumsi newline
                    
                        TreeNode selectedCategory = null;
                        switch (categoryChoice) {
                            case 1:
                                selectedCategory = system.nonMember;
                                break;
                            case 2:
                                selectedCategory = system.memberGold;
                                break;
                            case 3:
                                selectedCategory = system.memberSilver;
                                break;
                            case 4:
                                System.out.println("Kembali ke Menu Utama.");
                                break; 
                            default:
                                System.out.println("Pilihan kategori tidak valid. Silakan coba lagi.");
                                continue;
                        }

                        if (categoryChoice == 4) {
                            break; // Keluar dari loop sorting dan kembali ke menu utama
                        }

                        System.out.println();
                        System.out.println("\n=== Pencarian Berdasarkan ===");
                        System.out.println("1. Nama File");
                        System.out.println("2. Format File");
                        System.out.println("3. Nama Pemesan");
                        System.out.println("4. Kembali ke Pilihan Kategori");
                        System.out.print("Pilihan Anda: ");
                        int searchChoice = scanner.nextInt();
                        scanner.nextLine(); 
                                                         
                        
                        switch (searchChoice) {
                            case 1:
                            System.out.print("Masukkan kata kunci pencarian: ");
                            String keyword = scanner.nextLine();
                            Searching.searchByName(selectedCategory, keyword);
                            break;
                            case 2:
                            System.out.print("Masukkan kata kunci pencarian: ");
                            String keywordformat = scanner.nextLine();
                            Searching.searchByFormat(selectedCategory, keywordformat);
                            break;
                            case 3:
                            System.out.print("Masukkan kata kunci pencarian: ");
                            String keywordcustomer = scanner.nextLine();
                                Searching.searchByCustomer(selectedCategory, keywordcustomer);
                                break;
                            case 4:
                                System.out.println("Kembali ke Menu Kategori.");
                                System.out.println();
                                break;
                            default:
                                System.out.println("Pilihan pencarian tidak valid.");
                        }
                        if (searchChoice == 4) {
                            continue; 
                        }
                    }
                    break;
                
            case 7:
                while (true) {
                    System.out.println("\n=== Sortir File (Member-Gold) ===");
                    System.out.println("1. By Size");
                    System.out.println("2. By Name");
                    System.out.println("3. By Format");
                    System.out.println("4. By Date");
                    System.out.println("5. Kembali ke Menu Utama");
                    System.out.print("Pilih metode sortir: ");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine(); // Konsumsi newline
                
                    switch (sortChoice) {
                        case 1:
                            sort.bubbleSort(system.memberGold.files.head, "size");
                            System.out.println("File telah disortir berdasarkan ukuran:");
                            system.memberGold.displayFiles();
                            break;
                        case 2:
                            sort.bubbleSort(system.memberGold.files.head, "name");
                            System.out.println("File telah disortir berdasarkan nama:");
                            system.memberGold.displayFiles();
                            break;
                        case 3:
                            sort.bubbleSort(system.memberGold.files.head, "format");
                            System.out.println("File telah disortir berdasarkan format:");
                            system.memberGold.displayFiles();
                            break;
                        case 4:
                            sort.bubbleSort(system.memberGold.files.head, "date");
                            System.out.println("File telah disortir berdasarkan tanggal:");
                            system.memberGold.displayFiles();
                            break;
                        case 5:
                            System.out.println("Kembali ke Menu Utama.");
                            break;
                        default:
                            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                            continue; // Kembali ke menu sorting
                    }
                
                    if (sortChoice == 5) {
                        break; // Keluar dari loop sorting dan kembali ke menu utama
                    }
                }
                break;
                                
                case 8:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
