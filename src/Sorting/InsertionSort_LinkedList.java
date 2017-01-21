package Sorting;

import DataStructures.LinkedList.SingleLinkedList;
import DataStructures.LinkedList.SingleListNode;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/4/2016
 */

/*

 1. Insert a dummy node to point to the start of new sorted list
 2. Traverse the list from the head until the current node != null
    2A. Check for all nodes from the start if they are less than the current node
    2B. Stop before the first node which is greater to current node
    2C. Insert the current node between this node and its next.

 */
public class InsertionSort_LinkedList {

    private <T extends Comparable<T>> SingleListNode<T> insertionSort(SingleLinkedList<T> linkedList) {
        SingleListNode<T> head = linkedList.head;
        if (head == null)
            return null;

        SingleListNode<T> dummy = new SingleListNode<>(); //new starter of the sorted list
        SingleListNode<T> current = head; //the node will be inserted
        SingleListNode<T> previous = dummy; //insert node between previous and previous.next
        SingleListNode<T> next; //the next node will be inserted
        //not the end of input list
        while (current != null) {
            next = current.next;
            //find the right place to insert
            while (previous.next != null && previous.next.value.compareTo(current.value) < 0) {
                previous = previous.next;
            }

            //insert between previous and previous.next
            current.next = previous.next;
            previous.next = current;

            previous = dummy;
            current = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        InsertionSort_LinkedList sorter = new InsertionSort_LinkedList();
        SingleLinkedList<Integer> intLinkedList = new SingleLinkedList<>();
        intLinkedList.pushNodes(1,3,13,5,8,21,2);
        intLinkedList.displayList();

        SingleListNode<Integer> intSortedHead = sorter.insertionSort(intLinkedList);
        intLinkedList.setHead(intSortedHead);
        System.out.print("\nAfter sorting the nodes in linked list:");
        intLinkedList.displayList();

        SingleLinkedList<String> stringLinkedList = new SingleLinkedList<>();
        stringLinkedList.pushNodes("G","A","F","J","B","E","D","C","E");
        stringLinkedList.displayList();

        SingleListNode<String> stringSortedHead = sorter.insertionSort(stringLinkedList);
        stringLinkedList.setHead(stringSortedHead);
        System.out.print("\nAfter sorting the nodes in linked list:");
        stringLinkedList.displayList();
    }
}
