package strategies;

import java.util.HashMap;
import java.util.Map;

class DLL {
    int key;
    int value;
    DLL prev;
    DLL next;

    DLL(int key,int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

public class LRUCache implements CacheStrategy{

    private final int capacity;
    private int size;
    private DLL head;
    private DLL tail;
    private Map<Integer, DLL> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();
        head = new DLL(-1,-1);
        tail = new DLL(-1,-1);

        head.next = tail;
        tail.prev = head;
    }

    private void remove(DLL node) {
        DLL prev = node.prev;
        DLL next = node.next;

        prev.next = next;
        next.prev = prev;
    }
    private void moveAtHead(DLL node) {
        remove(node);
        addAtHead(node);
    }

    private void addAtHead(DLL node) {
        DLL temp = head.next;
        temp.prev = node;
        node.next = temp;
        node.prev = head;
        head.next = node;
    }

    private int removeAtTail() {
        DLL node  = tail.prev;
        remove(node);
        return  node.value;
    }

    @Override
    public void put(int key, int value) {
        if(!cache.containsKey(key)) {
            DLL node = new DLL(key,value);
            cache.put(key,node);
            addAtHead(node);
            size++;
            if(size > capacity) {
                int tmp = removeAtTail();
                size--;
                cache.remove(tmp);
            }

        }else{
            DLL node = cache.get(key);
            node.value = value;
            moveAtHead(node);
        }
    }

    @Override
    public int get(int key) {
        if(!cache.containsKey(key)) return -1;

        DLL node = cache.get(key);
        moveAtHead(node);
        return node.value;
    }
}


