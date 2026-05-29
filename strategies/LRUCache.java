package strategies;

import helpers.DoublyLinkedList;
import helpers.Node;

import java.util.HashMap;
import java.util.Map;



public class LRUCache implements CacheStrategy{

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final DoublyLinkedList doublyLinkedList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.doublyLinkedList = new DoublyLinkedList();
    }



    @Override
    public void put(int key, int value) {
        if(!cache.containsKey(key)) {
            Node node = new Node(key,value);
            cache.put(key,node);
            doublyLinkedList.addAtHead(node);
            doublyLinkedList.size++;
            if(doublyLinkedList.size > capacity) {
                int tmp = doublyLinkedList.removeAtTail();
                doublyLinkedList.size--;
                cache.remove(tmp);
            }

        }else{
            Node node = cache.get(key);
            node.value = value;
            doublyLinkedList.moveAtHead(node);
        }
    }

    @Override
    public int get(int key) {
        if(!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        doublyLinkedList.moveAtHead(node);
        return node.value;
    }
}


