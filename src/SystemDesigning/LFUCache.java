package SystemDesigning;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/30/2016
 */
public class LFUCache {

    class Item implements Comparable<Item> {
        final int id;
        final int key;
        int value;
        int freq;

        Item(int k, int value, int freq, int id) {
            key = k;
            this.freq = freq;
            this.value = value;
            this.id = id;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getFreq() {
            return freq;
        }

        public void setFreq(int freq) {
            this.freq = freq;
        }

        @Override
        public boolean equals(Object object) {
            return object instanceof Item && key == ((Item) object).key;
        }

        public int hashCode() {
            return key;
        }

        @Override
        public int compareTo(Item o) {
            return (this.key == o.key) ? 0 : ((this.freq == o.freq) ? this.id - o.id : this.freq - o.freq);
        }
    }

    private final int capacity;
    private int id;
    private final HashMap<Integer, Item> cache;
    private final TreeSet<Item> treeSet;

    private LFUCache(int capacity) {
        this.capacity = capacity;
        id = 0;
        cache = new HashMap<>();
        treeSet = new TreeSet<>();
    }

    private int get(int key) {
        id++;
        if (cache.containsKey(key)) {
            updateFrequency(key);
            return cache.get(key).value;
        }
        return -1;
    }

    private void set(int key, int value) {
        if (capacity == 0)
            return;
        id++;
        if (cache.containsKey(key)) {
            updateItem(key, value);
            return;
        }

        if (cache.size() == capacity) {
            Item leastFrequent = treeSet.pollFirst();
            cache.remove(leastFrequent != null ? leastFrequent.key : 0);
        }

        Item item = new Item(key, value, 1, id);
        cache.put(key, item);
        treeSet.add(item);
    }

    private void updateFrequency(int key) {
        Item item = cache.get(key);
        treeSet.remove(item);
        Item newItem = new Item(item.key, item.value, item.freq + 1, id);
        treeSet.add(newItem);
        cache.put(key, newItem);
    }

    // Tree set doesn't order itself with mutable objects. Meaning that if value gets updated, the set doesn't
    //  reorder itself.

    private void updateItem(int key, int value) {
        Item item = cache.get(key);
        treeSet.remove(item);
        Item newItem = new Item(item.key, value, item.freq + 1, id);
        treeSet.add(newItem);
        cache.put(key, newItem);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.set(1, 1);
        cache.set(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.set(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.set(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
