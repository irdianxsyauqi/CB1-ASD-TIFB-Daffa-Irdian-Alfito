public class DoublyLinkedList implements LinkedList{
    private Node2P head,tail;
    private int size=0;

    public DoublyLinkedList(){
        head = tail = null;
    }

    public boolean isEmpty(){
        return(size==0);
    }

    public int size(){
        return size;
    }

    // O(1)
    public void addFirst(Object inputData){
        Node2P baru = new Node2P(inputData);
        if (isEmpty()){
            head = tail = baru;
        }
        else{
            baru.next = head;
            head.prev = baru;
            head = baru;
        }
        size++;
    }

    // O(1)
    public void addLast(Object inputData){
        Node2P baru = new Node2P(inputData);
        if (isEmpty()){
            head = tail = baru;
        }
        else{
            tail.next = baru;
            baru.prev = tail;
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
            System.out.println("Index lebih besar atau sama dari ukuran list");
        }
        else if (index == size-1){
            addLast(inputData);
        }
        else{
            Node2P temp = nodeAt(index);
            Node2P baru = new Node2P(inputData);
            baru.prev = temp;
            baru.next = temp.next;
            temp.next.prev = baru;
            temp.next = baru;
            size++;
        }
    }

    // O(1)
    public void deleteFirst(){
        if (isEmpty()){
            System.out.println("List kosong!");
            return;
        }
        unlink(head);
    }

    // O(1) berkat pointer prev
    public void deleteLast(){
        if (isEmpty()){
            System.out.println("List kosong!");
            return;
        }
        unlink(tail);
    }

    // Menghapus node yang berada setelah node pada index ke-i
    public void deleteAfter(int index){
        if (index < 0 || index >= size-1){
            System.out.println("Index lebih besar atau sama dari ukuran list");
            return;
        }
        unlink(nodeAt(index).next);
    }

    public void print(){
        for (Node2P cur = head; cur != null; cur = cur.next){
            System.out.println(cur.data);
        }
    }
    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return nodeAt(index).data;
    }
    @Override
    public int indexOf(Object targetData) {
        int index = 0;
        for (Node2P cur = head; cur != null; cur = cur.next, index++) {
            if (java.util.Objects.equals(cur.data, targetData)) {
                return index;
            }
        }
        return -1;
    }
    @Override
    public void printReverse() {
        for (Node2P cur = tail; cur != null; cur = cur.prev) {
            System.out.println(cur.data);
        }
    }
    @Override
    public boolean remove(Object targetData) {
        for (Node2P cur = head; cur != null; cur = cur.next) {
            if (java.util.Objects.equals(cur.data, targetData)) {
                unlink(cur);
                return true;
            }
        }
        return false;
    }
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node2P cur = head; cur != null; cur = cur.next) {
            result[i++] = cur.data;
        }
        return result;
    }
    private Node2P nodeAt(int index) {
        Node2P cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        return cur;
    }
    private void unlink(Node2P node) {
        if (node.prev == null) head = node.next;
        else node.prev.next = node.next;

        if (node.next == null) tail = node.prev;
        else node.next.prev = node.prev;

        node.prev = node.next = null;
        size--;
    }
}