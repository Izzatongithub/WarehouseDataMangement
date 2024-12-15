public class Stack {
    FileNode top;

    public void push(String namaFile, String tglFileDibuat, String size) {
        FileNode newFile = new FileNode(namaFile, tglFileDibuat, size);
        newFile.next = top;
        top = newFile;
    }

    public FileNode pop() {
        if (top == null) {
            System.out.println("Stack is empty");
            return null;
        }
        FileNode temp = top;
        top = top.next;
        return temp;
    }

    public void displayStack() {
        FileNode temp = top;
        if (temp == null) {
            System.out.println("Tidak ada antrian.");
            return;
        }
        
            while (temp != null) {
            System.out.println("Nama File: " + temp.namaFile);
            System.out.println("Tgl File ddibuat: " + temp.tglFileDibuat);
            System.out.println("Size: " + temp.size);
            System.out.println("=============================================");
            temp = temp.next;
            }
        }    
    
}