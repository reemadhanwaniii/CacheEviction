package strategies;

import helpers.DoublyLinkedList;
import helpers.Node;

import java.util.HashMap;
import java.util.Map;

public class LIFOCache implements CacheStrategy{

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final DoublyLinkedList list;

    public LIFOCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DoublyLinkedList();
    }

    @Override
    public void put(int key, int value) {
        if(capacity == 0) return;

        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            return;
        }

        if(cache.size() == capacity) {
            int nodeToRemove = list.removeAtHead();
            cache.remove(nodeToRemove);
        }

        Node node = new Node(key,value);
        cache.put(key,node);
        list.addAtHead(node);
        return;
    }

    @Override
    public int get(int key) {

        if(!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        return node.value;
    }
}
