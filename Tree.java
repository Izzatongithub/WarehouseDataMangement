package WarehouseDataMangement;

public class Tree {
    static class FileNode {
        String name;
        int size;
        String dateModified;
        LinkedList files;

        // Ini untuk file
        FileNode(String name, int size, String dateModified) {
            this.name = name;
            this.size = size;
            this.dateModified = dateModified;
            this.files = new LinkedList();
        }

        // Ini untuk folder
        FileNode(String name) {
            this(name, 0, null);
        }
    }

    static class Node {
        FileNode data;
        Node next;

        Node(FileNode data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;

        // Tambahin node baru ke LL
        void add(FileNode data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        // Untuk traversal dari node ke node berikutnya
        void traverse() {
            Node current = head;
            while (current != null) {
                System.out.println(current.data.name);
                current = current.next;
            }
        }
    }

    static class Drive {
        FileNode root;
        // Ini ngebuild root nya
        Drive() {
            root = new FileNode("root");
        }
        // Buat nambahin folder
        void addFolder(String name, String targetFolderName) {
            FileNode folder = new FileNode(name);
            if (addNode(root, folder, targetFolderName)) {
                System.out.println("Folder '" + name + "' berhasil ditambahkan ke folder '" + targetFolderName + "'.");
            } else {
                System.out.println("Folder target tidak ditemukan.");
            }
        }

        // Buat nambahin file
        void addFile(String name, int size, String dateModified, String targetFolderName) {
            FileNode file = new FileNode(name, size, dateModified);
            if (addNode(root, file, targetFolderName)) {
                System.out.println("File '" + name + "' berhasil ditambahkan ke folder '" + targetFolderName + "'.");
            } else {
                System.out.println("Folder target tidak ditemukan.");
            }
        }

        // Nambahin node folder atau file
        boolean addNode(FileNode current, FileNode node, String targetFolderName) {
            if (current.name.equals(targetFolderName)) {
                current.files.add(node);
                return true;
            }
            Node temp = current.files.head;
            while (temp != null) {
                if (temp.data.dateModified == null) {
                    if (addNode(temp.data, node, targetFolderName)) {
                        return true;
                    }
                }
                temp = temp.next;
            }
            return false;
        }

        // Nampilin tree dari data yang sudah ada
        void displayTree() {
            displayTree(root, 0);
        }

        // Rekursif buat nampilin
        void displayTree(FileNode current, int depth) {
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }
            if (current.dateModified == null) {
                System.out.println("[Folder] " + current.name);
            } else {
                System.out.println("[File] " + current.name + " (" + current.size + "MB, " + current.dateModified + ")");
            }
            Node temp = current.files.head;
            while (temp != null) {
                displayTree(temp.data, depth + 1);
                temp = temp.next;
            }
        }
    }

    public static void main(String[] args) {
        Drive drive = new Drive();

        // Nambahin folder atau file
        drive.addFolder("Documents", "root");
        drive.addFolder("Photos", "root");
        drive.addFile("file1.txt", 10, "2024-12-01", "Documents");
        drive.addFile("file2.txt", 20, "2024-12-02", "Documents");
        drive.addFile("photo1.jpg", 5, "2024-12-03", "Photos");

        // Display
        drive.displayTree();
    }
}

