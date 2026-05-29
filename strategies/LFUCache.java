package strategies;

import helpers.DoublyLinkedList;
import helpers.Node;

import java.util.HashMap;
import java.util.Map;

public class LFUCache implements CacheStrategy{

    private final int capacity;
    private int minfreq;

    private Map<Integer, Node> cache;
    private Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    @Override
    public void put(int key, int value) {
        if(capacity == 0) return;

        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            updateFrequency(node);
            return;
        }

        if(cache.size() == capacity) {
            DoublyLinkedList list = freqMap.get(minfreq);
            int nodeToRemove = list.removeAtTail();
            cache.remove(nodeToRemove);
        }
        Node node = new Node(key,value);
        cache.put(key,node);

        minfreq = 1;
        DoublyLinkedList list = freqMap.getOrDefault(1,new DoublyLinkedList());
        list.addAtHead(node);
        freqMap.put(1,list);


    }

    @Override
    public int get(int key) {
        if(!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        updateFrequency(node);
        return node.value;
    }

    private void updateFrequency(Node node) {
        int oldfreq = node.freq;

        DoublyLinkedList dll = freqMap.get(oldfreq);
        dll.remove(node);

//        update minFreq
        if(oldfreq == minfreq && dll.size == 0) {
            minfreq++;
        }
        node.freq++;
        DoublyLinkedList newList = freqMap.getOrDefault(node.freq,new DoublyLinkedList());
        newList.addAtHead(node);
        freqMap.put(node.freq,newList);
    }
}
