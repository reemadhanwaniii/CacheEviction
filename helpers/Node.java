package helpers;

public class Node {
    public int key;
    public int value;
    public int freq;

    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }


}
