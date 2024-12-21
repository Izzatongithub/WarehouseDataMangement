class Sorting {
    public static void bubbleSort(Node head, String sortBy) {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                boolean shouldSwap = false;
                
                switch (sortBy.toLowerCase()) {
                    case "size":
                        shouldSwap = current.file.size > current.next.file.size;
                        break;
                    case "name":
                        shouldSwap = current.file.nama.compareToIgnoreCase(current.next.file.nama) > 0;
                        break;
                    case "format":
                        shouldSwap = current.file.format.compareToIgnoreCase(current.next.file.format) > 0;
                        break;
                    case "date":
                        shouldSwap = current.file.tanggalDibuat.compareTo(current.next.file.tanggalDibuat) > 0;
                        break;
                    default:
                        System.out.println("Invalid sorting attribute: " + sortBy);
                        return;
                }
    
                if (shouldSwap) {
                    swapFile(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
    

    public static void sortByCategory(Queue queue) {
        if (queue.front == null || queue.front.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Node current = queue.front;
            while (current.next != null) {
                if (compareCategory(current.file.kategori, current.next.file.kategori) > 0) {
                    swapFile(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    private static int compareCategory(String category1, String category2) {
        int rank1 = getCategoryRank(category1);
        int rank2 = getCategoryRank(category2);
        return Integer.compare(rank1, rank2);
    }

    private static int getCategoryRank(String category) {
        switch (category) {
            case "Member-Gold":
                return 1;
            case "Member-Silver":
                return 2;
            case "Non-Member":
                return 3;
            default:
                return Integer.MAX_VALUE;
        }
    }

    private static void swapFile(Node node1, Node node2) {
        File temp = node1.file;
        node1.file = node2.file;
        node2.file = temp;
    }
}