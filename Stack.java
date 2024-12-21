class Stack {
    Node top;

    public void push(File file) {
        Node newNode = new Node(file);
        newNode.next = top;
        top = newNode;
    }

    public File pop() {
        if (top == null) {
            System.out.println("Stack is empty");
            return null;
        }
        Node temp = top;
        top = top.next;
        return temp.file;
    }

    public void display() {
        if (top == null) {
            System.out.println("Stack is empty");
        } else {
            Node current = top;
            while (current != null) {
                System.out.println(current.file);
                current = current.next;
            }
        }
    }

    

    public boolean isEmpty() {
        return top == null;
    }
}
