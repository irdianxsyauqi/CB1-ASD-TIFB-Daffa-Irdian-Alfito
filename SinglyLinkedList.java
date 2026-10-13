/**
 * SinglyLinkedList
 */
public class SinglyLinkedList implements LinkedList{

    public Node head,tail;
    private int size=0;

    public SinglyLinkedList(){
        head=tail=null;
    }
    public boolean isEmpty(){
        return(size==0);
    }
    public int size(){
        return size;
    }

    // O(1)
    public void addFirst(Object inputData){
        Node baru = new Node(inputData);
        if (isEmpty()){
            head = tail = baru;
        }
        else {
            baru.pointer = head;
            head = baru;
        }
        size++;
    }

    // O(1)
    public void addLast(Object inputData){
        Node baru = new Node(inputData);
        if (isEmpty()){
            head = tail = baru;
        }
        else {
            tail.pointer = baru;
            tail = baru;
        }
        size++;
    }

    // Menyisipkan data setelah node pada index ke-i
    public void addAfter(int index,Object inputData){
        if (isEmpty()){
            addFirst(inputData);
        }
        else if (index < 0 || index >= size){
            System.err.println("Index di luar ukuran list");
        }
        else if (index == size-1){
            addLast(inputData);
        }
        else {
            Node C = head;
            for(int i=0;i<index;i++){
                C = C.pointer;
            }
            Node baru = new Node(inputData);
            baru.pointer = C.pointer;
            C.pointer = baru;
            size++;
        }
    }

    // O(1)
    public void deleteFirst(){
        if(isEmpty()){
            System.err.println("Linked list kosong");
            return;
        }
        head = head.pointer;
        if (head == null) tail = null;
        size--;
    }

    // O(n): singly linked list harus mencari node sebelum tail
    public void deleteLast(){
        if(isEmpty()){
            System.err.println("Linked list kosong");
        }
        else if(size==1){
            head = tail = null;
            size--;
        }
        else{
            Node temp = head;
            for(int i=0;i<size-2;i++){
                temp = temp.pointer;
            }
            temp.pointer = null;
            tail = temp;
            size--;
        }
    }

    // Menghapus node yang berada setelah node pada index ke-i
    public void deleteAfter(int index){
        if(isEmpty()){
            System.err.println("Linked list kosong");
        }
        else if(index < 0 || index >= size-1){
            System.err.println("Tidak ada node setelah index tersebut");
        }
        else{
            Node temp = head;
            for(int i=0;i<index;i++){
                temp = temp.pointer;
            }
            if (temp.pointer == tail) tail = temp;
            temp.pointer = temp.pointer.pointer;
            size--;
        }
    }

    public void print(){
        for(Node cur = head; cur != null; cur = cur.pointer){
            System.out.println(cur.data);
        }
    }
    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size-1) return tail.data;
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.pointer;
        }
        return cur.data;
    }
    @Override
    public int indexOf(Object targetData) {
        int index = 0;
        for (Node cur = head; cur != null; cur = cur.pointer, index++) {
            if (java.util.Objects.equals(cur.data, targetData)) {
                return index;
            }
        }
        return -1;
    }
    @Override
    public void printReverse() {
        Node prev = null, cur = head;
        while (cur != null) {
            Node next = cur.pointer;
            cur.pointer = prev;
            prev = cur;
            cur = next;
        }
        cur = prev;
        prev = null;
        while (cur != null) {
            System.out.println(cur.data);
            Node next = cur.pointer;
            cur.pointer = prev;
            prev = cur;
            cur = next;
        }
    }
    @Override
    public boolean remove(Object targetData) {
        Node prev = null, cur = head;
        while (cur != null) {
            if (java.util.Objects.equals(cur.data, targetData)) {
                if (prev == null) head = cur.pointer;
                else prev.pointer = cur.pointer;
                if (cur == tail) tail = prev;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.pointer;
        }
        return false;
    }
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node cur = head; cur != null; cur = cur.pointer) {
            result[i++] = cur.data;
        }
        return result;
    }
}