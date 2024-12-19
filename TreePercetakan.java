class File {
    // Buat inisialisasi file
    String type;
    String fileName;
    double fileSize;
    String fileFormat;
    String orderDate;

    File(String type, String fileName, double fileSize, String fileFormat, String orderDate) {
        this.type = type;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileFormat = fileFormat;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", FileName: " + fileName + ", FileSize: " + fileSize + "MB, Format: " + fileFormat + ", OrderDate: " + orderDate;
    }
}

class Node {
    // untuk node
    File file;
    Node next;

    Node(File file) {
        this.file = file;
        this.next = null;
    }
}

// Konstruktor LL nya
class LinkedList {
    Node head;

    void add(File file) {
        Node newNode = new Node(file);
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

    void display() {
        if (head == null) {
            System.out.println("No files available.");
        } else {
            Node current = head;
            while (current != null) {
                System.out.println(current.file);
                current = current.next;
            }
        }
    }
}

// Ini untuk bagian queue
class Queue {
    Node front;
    Node rear;

    void enqueue(File file) {
        Node newNode = new Node(file);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    void display() {
        if (front == null) {
            System.out.println("Print queue is empty.");
        } else {
            Node current = front;
            while (current != null) {
                System.out.println(current.file);
                current = current.next;
            }
        }
    }
}

// ini konstruktor tree nya
class TreeNode {
    String name;
    LinkedList files;

    TreeNode(String name) {
        this.name = name;
        this.files = new LinkedList();
    }

    void addFile(File file) {
        files.add(file);
    }

    void displayFiles() {
        files.display();
    }
}

// ini untuk tree utamanya
class PrintingSystem {
    TreeNode root;
    TreeNode nonMember;
    TreeNode memberGold;
    TreeNode memberSilver;
    Queue printQueue;

    PrintingSystem() {
        root = new TreeNode("Percetakan");
        nonMember = new TreeNode("Non-Member");
        memberGold = new TreeNode("Member-Gold");
        memberSilver = new TreeNode("Member-Silver");
        printQueue = new Queue();
    }

    // ini nge add file kedalam tree
    void addFile(String category, File file) {
        switch (category) {
            case "Non-Member":
                nonMember.addFile(file);
                break;
            case "Member-Gold":
                memberGold.addFile(file);
                break;
            case "Member-Silver":
                memberSilver.addFile(file);
                break;
            default:
                System.out.println("Invalid category.");
                return;
        }
        printQueue.enqueue(file);
        System.out.println("File added to " + category + " and added to the print queue.");
    }

    // ini untuk nampilin struktur tree nya
    void displayTree() {
        System.out.println(root.name);
        System.out.println("  " + nonMember.name);
        nonMember.displayFiles();
        System.out.println("  " + memberGold.name);
        memberGold.displayFiles();
        System.out.println("  " + memberSilver.name);
        memberSilver.displayFiles();
    }

    // ini nampilin queue tadi, dia manggil ulang function sendiri
    void displayQueue() {
        printQueue.display();
    }
}

public class TreePercetakan {
    public static void main(String[] args) {
        PrintingSystem system = new PrintingSystem();

        File file1 = new File("Non-Member", "Document1", 2.5, "pdf", "2024-12-20");
        File file2 = new File("Member-Gold", "Presentation1", 4.0, "ppt", "2024-12-20");
        File file3 = new File("Member-Silver", "Report1", 3.0, "docx", "2024-12-20");

        system.addFile("Non-Member", file1);
        system.addFile("Member-Gold", file2);
        system.addFile("Member-Silver", file3);

        System.out.println("\nTree Structure:");
        system.displayTree();

        System.out.println("\nPrint Queue:");
        system.displayQueue();
    }
}
