import java.util.Comparator;

public class DoublyLinkedList<T extends Comparable<T>> implements Container<T> {
    private static int size;
    private Node<T> first;
    private Node<T> last;

    private class Node<T> {
        private T data;
        private Node<T> pri;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getPri() {
            return pri;
        }

        public void setPri(Node<T> pri) {
            this.pri = pri;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

    }

    @Override
    public int getSize() {
        return size;
    }


    public boolean isEmpty(){
        return size == 0;
    }

    public boolean addFirst(T value){
        Node<T> node = new Node<>(value);
        if (size == 0) {
            first = node;
            last = first;
        }else {
            node.next = first;
            first.pri = node;
            first = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean addElement(T value){
        return addLast(value);
    }

    public boolean addLast(T value){
        if (size == 0){
            return addFirst(value);
        }else {
            Node<T> node = new Node<>(value);
            last.next = node;
            node.pri = last;
            last = node;
        }
        size++;
        return true;
    }


    public boolean add(int index,T value){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index out of bound: Index:" + index + "\tsize:" + size);
        } else if(index == 0){
            return addFirst(value);
        }else if (index == size){
            return addLast(value);
        }else {
            Node<T> node = new Node<>(value);
            Node<T> head = first;
            for (int i = 0; i < index-1; i++) {
                head = head.getNext();
            }
            node.next = head.next;
            head.next = node;
            node.pri = head;
            node.next.pri = node;
        }
        size++;
        return true;
    }



    public T removeFirst(){
        if (size == 0){
            throw new RuntimeException("null！");
        }
        T data = first.getData();
        Node<T> node = first.next;
        node.setPri(null);
        first = node;
        return data;
    }


    public T removeLast(){
        if (size == 0){
            throw new RuntimeException("null！");
        }
        T data = last.getData();
        Node<T> node = last.pri;
        node.setNext(null);
        last = node;
        return data;
    }

    @Override
    public void removeBy(T t){
        Node<T> node = first;
        for (int i = 0; i < size; i++) {
            if (t.compareTo(node.data) == 0) {
                remove(i);
                return;  ///hugh bug!!if not add this
            }
            node = node.next;
        }
    }


    public T remove(int index){
        if (size == 0){
            throw new RuntimeException("null！");
        }
        //Note that when adding, the index cannot be in 'size', but the added position can be in 'size', but not when deleting
        if (index < 0 || index > size-1){
            throw new IndexOutOfBoundsException("Index out of bound, Index:" + index + "\tsize:" + size);
        } else if(index == 0){
            return removeFirst();
        }else {
            Node<T> node = first;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            Node<T> temp = node.next;
            if (temp != last){
                T data = temp.getData();
                node.next = temp.next;
                temp.next.pri = node;
                temp.setNext(null);
                return data;
            }else {
                return removeLast();
            }
        }
    }

    @Override
    public T searchBy(T t){
        Node<T> node = first;
        while (node.next!=null){
            if (t.compareTo(node.data)==0){
                return node.data;
            }
            node=node.next;
        }
        return null;
    }



    public T getData(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("index out of bound, Index:" + index + "\tsize:" + size);
        }else if (size == 0){
            throw new RuntimeException("null!");
        }else if (size == 1){
            return first.data;
        }else {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.data;
        }
    }



    public void clear(){
        first.next = null;
        last = first;
    }


    @Override
    public String print(){
        if (size == 0) {
            System.out.println("null!");
        }
        StringBuilder sb=new StringBuilder();
        Node<T> node = first;
        while (node != null) {
            sb.append(node.getData()).append("  ");
            node = node.next;
        }
        return sb.toString();
    }

    /**
     * Detailed traversal output:
     * Precursor node value
     * Current node value
     * Subsequent node value
     * */
    public void detailPrint(){
        if (size == 0) {
            System.out.println("null!");
        }
        Node<T> node = first;
        while (node != null) {
            System.out.print("Precursor node value:");
            System.out.printf("%-5s",node.pri == null ? "null\t" : node.pri.getData()+"\t");
            System.out.print("Current node value:");
            System.out.printf("%-6s",node.getData() + "\t");
            System.out.print("Current node value:");
            System.out.printf("%-5s",node.next == null ? "null\t" : node.next.getData()+"\t");
            System.out.println();
            node = node.getNext();
        }
        System.out.println();
    }

    @Override
    public void sort() {
        Node<T> node = first;
        while (node.next != null) {
            Node<T> min = getMin(node);
            swap(node, min);
            node = node.next;
        }
    }

    private Node<T> getMin(Node<T> node) {
        Node<T> min = node;
        Node<T> current = node.next;

        while (current != null) {
            if (current.data.compareTo(min.data)<0) {
                min = current;
            }
            current = current.next;
        }

        return min;
    }

    private void swap(Node<T> a, Node<T> b) {
        T tmp = a.data;
        a.data = b.data;
        b.data = tmp;
    }

}
