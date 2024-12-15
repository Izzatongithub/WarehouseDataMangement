public class Queue {
    FileNode front, rear;

    public void enqueue(String namaFile, String tglFileDibuat, String size) {
        // FileNode newFile = new FileNode(namaFile, tglFileDibuat, size);
        FileNode newFile = new FileNode(namaFile, tglFileDibuat, size);
        if (this.rear == null) {
            this.front = this.rear = newFile;
            return;
        }
        this.rear.next = newFile;
        this.rear = newFile;
    }

    public String dequeue() {
        if (this.front == null) {
            System.out.println("Queue is empty, no one to serve.");
            return null;
        }
        FileNode temp = this.front;
        this.front = this.front.next;

        if (this.front == null) {
            this.rear = null;
        }

        return temp.namaFile;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Antrian Kosong");
            return;
        }

        System.out.println("Displaying queue:");
        FileNode current = front;
        while (current != null) {
            System.out.println("Nama: " + current.namaFile + ", Tanggal Dibuat: " + current.tglFileDibuat + ", Size: " + current.size);
            current = current.next;
        }
    }
}
