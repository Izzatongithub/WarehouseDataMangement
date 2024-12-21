class Searching {
    public static void searchByName(TreeNode categoryNode, String fileName) {
        if (categoryNode == null || categoryNode.files == null || categoryNode.files.head == null) {
            System.out.println("List in category " + categoryNode.name + " is empty.");
            return;
        }

        Node current = categoryNode.files.head;
        boolean found = false;
        System.out.println("Hasil pencarian file dengan nama '" + fileName + "' di kategori '" + categoryNode.name + "':");

        while (current != null) {
            if (current.file.nama.equalsIgnoreCase(fileName)) {
                System.out.println(current.file);
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("File dengan nama '" + fileName + "' tidak ditemukan di kategori '" + categoryNode.name + "'.");
        }
    }

    public static void searchByFormat(TreeNode categoryNode, String format) {
        if (categoryNode == null || categoryNode.files == null || categoryNode.files.head == null) {
            System.out.println("List in category " + categoryNode.name + " is empty.");
            return;
        }

        Node current = categoryNode.files.head;
        boolean found = false;
        System.out.println("Hasil pencarian file dengan format '" + format + "' di kategori '" + categoryNode.name + "':");

        while (current != null) {
            if (current.file.format.equalsIgnoreCase(format)) {
                System.out.println(current.file);
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("File dengan format '" + format + "' tidak ditemukan di kategori '" + categoryNode.name + "'.");
        }
    }

    public static void searchByCustomer(TreeNode categoryNode, String namaPemesan) {
        if (categoryNode == null || categoryNode.files == null || categoryNode.files.head == null) {
            System.out.println("List in category " + categoryNode.name + " is empty.");
            return;
        }

        Node current = categoryNode.files.head;
        boolean found = false;
        System.out.println("Hasil pencarian file dengan nama pemesan '" + namaPemesan + "' di kategori '" + categoryNode.name + "':");

        while (current != null) {
            if (current.file.namaPemesan.equalsIgnoreCase(namaPemesan)) {
                System.out.println(current.file);
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("File dengan nama pemesan '" + namaPemesan + "' tidak ditemukan di kategori '" + categoryNode.name + "'.");
        }
    }
}
