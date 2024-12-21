class Queue {
    Node front;
    Node rear;

    void enqueue(File file) {
        Node newNode = new Node(file);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        Sorting.sortByCategory(this);
    }

    public File dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, no one to serve.");
            return null;
        }
        
        Node temp = front;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return temp.file;
    }

    public boolean isEmpty() {
        return front == null;
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Print queue is empty.");
            return;
        }
        
        Node current = front;
        int index = 1;
        while (current != null) {
            System.out.println(current.file.toFormattedString(index));
            current = current.next;
            index++;
        }
    }
}