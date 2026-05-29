package helpers;



public class DoublyLinkedList {
    Node head;
    Node tail;
    public int size;

    public DoublyLinkedList() {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.size = 0;
    }

    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }
    public void moveAtHead(Node node) {
        remove(node);
        addAtHead(node);
    }

    public void addAtHead(Node node) {
        Node temp = head.next;
        temp.prev = node;
        node.next = temp;
        node.prev = head;
        head.next = node;

        size++;
    }

    public int removeAtTail() {
        Node node  = tail.prev;
        remove(node);
        size--;
        return  node.value;
    }

    public void addAtTail(Node node) {
        Node temp = tail.prev;
        temp.next = node;
        node.next = tail;
        node.prev = temp;
        tail.prev = node;

        size++;
    }

    public int removeAtHead() {
        Node node = head.next;
        remove(node);
        size--;
        return node.value;
    }
}
