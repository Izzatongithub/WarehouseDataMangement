package WarehouseDataMangement;

public class TreeBFSDFS {
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

        // BFS untuk mencari file/folder dan menampilkan path
        void bfs(String target) {
            QueueManager queueManager = new QueueManager();
            queueManager.enqueue(root);

            while (!queueManager.isEmpty()) {
                FileNode current = queueManager.dequeue();
                if (current.name.equals(target)) {
                    System.out.println("Found: " + target + " at path: " + getPath(current));
                    return;
                }

                Node temp = current.files.head;
                while (temp != null) {
                    queueManager.enqueue(temp.data);
                    temp = temp.next;
                }
            }
            System.out.println(target + " not found");
        }

        // DFS untuk mencari file/folder dan menampilkan path
        void dfs(String target) {
            if (!dfs(root, target, "root")) {
                System.out.println(target + " not found");
            }
        }

        boolean dfs(FileNode current, String target, String path) {
            if (current.name.equals(target)) {
                System.out.println("Found: " + target + " at path: " + path);
                return true;
            }

            Node temp = current.files.head;
            while (temp != null) {
                if (dfs(temp.data, target, path + "/" + temp.data.name)) {
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }

        // Helper untuk mendapatkan path
        String getPath(FileNode node) {
            StringBuilder path = new StringBuilder(node.name);
            FileNode current = node;
            while (current != root) {
                for (Node temp = root.files.head; temp != null; temp = temp.next) {
                    if (contains(temp.data, current)) {
                        path.insert(0, temp.data.name + "/");
                        current = temp.data;
                        break;
                    }
                }
            }
            return "root/" + path.toString();
        }

        boolean contains(FileNode parent, FileNode child) {
            Node temp = parent.files.head;
            while (temp != null) {
                if (temp.data == child) {
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }

        static class QueueManager {
            private LinkedList queue;

            QueueManager() {
                queue = new LinkedList();
            }

            void enqueue(FileNode data) {
                queue.add(data);
            }

            FileNode dequeue() {
                if (queue.head == null) {
                    return null;
                }
                FileNode data = queue.head.data;
                queue.head = queue.head.next;
                return data;
            }

            boolean isEmpty() {
                return queue.head == null;
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
        System.out.println();
        drive.displayTree();
        System.out.println();

        // Search
        drive.bfs("file1.txt");
        drive.dfs("Photos");
    }
}
