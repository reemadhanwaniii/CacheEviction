package strategies;

import helpers.DoublyLinkedList;
import helpers.Node;

import java.util.HashMap;
import java.util.Map;

public class FIFOCache implements CacheStrategy{

    int capacity;
    Map<Integer, Node> cache;
    DoublyLinkedList list;

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        list = new DoublyLinkedList();
    }

    @Override
    public void put(int key, int value) {
        if(capacity == 0) return;
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            return;
        }
        Node node = new Node(key,value);
        cache.put(key,node);
        list.addAtTail(node);

        if(cache.size() == capacity) {
            int nodeToRemove = list.removeAtHead();
            cache.remove(nodeToRemove);
        }
        return;
    }

    @Override
    public int get(int key) {
        if(!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        return node.value;
    }
}
