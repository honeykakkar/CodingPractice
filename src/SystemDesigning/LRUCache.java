package SystemDesigning;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/29/2016
 */
class LRUCache {

    private final Map<Integer, DoubleNode<Integer>> cache;
    private final DoubleLinkedList<Integer> itemList;
    private final int capacity;

    LRUCache(int capacity) {
        this.cache = new HashMap<>(capacity);
        this.capacity = capacity;
        itemList = new DoubleLinkedList<>();
    }

    public void accessItem(int key) {
        DoubleNode<Integer> recentNode = cache.get(key);
        if (recentNode == null)
            return;

        itemList.moveToHead(recentNode);
    }

    public void addItem(int key) {
        DoubleNode<Integer> newItem = new DoubleNode<>(key);
        if (capacity == 0)
            return;
        if (cache.size() == capacity) {
            DoubleNode<Integer> lastItem = itemList.dummyTail.previous;
            itemList.removeNode(lastItem);
            cache.remove(lastItem.key);
        }
        cache.put(key, newItem);
        itemList.addToHead(newItem);
    }


    class DoubleNode<T> {
        final T key;
        DoubleNode<T> previous;
        DoubleNode<T> next;

        DoubleNode() {
            key = null;
            previous = null;
            next = null;
        }

        DoubleNode(T newKey) {
            key = newKey;
            previous = null;
            next = null;
        }
    }

    class DoubleLinkedList<T> {

        final DoubleNode<T> dummyHead;
        final DoubleNode<T> dummyTail;

        DoubleLinkedList() {
            dummyHead = new DoubleNode<>();
            dummyTail = new DoubleNode<>();

            dummyHead.next = dummyTail;
            dummyTail.previous = dummyHead;
        }

        void addToHead(DoubleNode<T> newNode) {
            newNode.next = dummyHead.next;
            newNode.previous = dummyHead;
            dummyHead.next = newNode;
        }

        void removeNode(DoubleNode<T> deleteNode) {
            if (deleteNode.previous == dummyHead)
                return;

            DoubleNode<T> prevNode = deleteNode.previous;
            DoubleNode<T> nextNode = deleteNode.next;
            prevNode.next = nextNode;
            nextNode.previous = prevNode;
            deleteNode.previous = null;
            deleteNode.next = null;
        }

        void moveToHead(DoubleNode<T> node) {
            removeNode(node);
            addToHead(node);
        }
    }
}
