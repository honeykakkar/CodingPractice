package Sorting;

import DataStructures.LinkedList;
import DataStructures.LinkedList.LinkedListNode;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 10/8/2016
 */

// Program to implement the merge sort for linked lists.
// Merge sort performs in O(nlogn) time irrespective of ordering of the elements

    // Merge sort is an efficient sorting algorithm when it comes to linked lists.
    // As it doesn't require any extra space to merge two sorted halves

public class MergeSort_LinkedList<T extends Comparable<T>> {

    LinkedListNode<T> mergeSort(LinkedListNode<T> head) {
        if(head == null || head.next == null)
            return head;

        LinkedListNode<T> turtle = head, hare = head.next;

        // Using turtle-hare approach (slow-fast pointer) to split the linked list in two equal halves
        while ((hare != null) && hare.next != null)
        {
            turtle = turtle.next;
            hare = hare.next.next;
        }

        // Splitting the list in two disconnected halves
        hare = turtle.next;
        turtle.next = null;

        LinkedListNode<T> sortedLeft = mergeSort(head);
        LinkedListNode<T> sortedRight = mergeSort(hare);

        return mergeLists(sortedLeft, sortedRight);
    }

    // Method to merge two sorted linked lists
    // Recursively calling it to find the next element in the final linked list
    LinkedListNode<T> mergeLists(LinkedListNode<T> listLeft, LinkedListNode<T> listRight) {
        LinkedListNode<T> winnerHead;
        if (listLeft == null)
            return listRight;
        else {
            if (listRight == null)
                return listLeft;
        }

        if(listLeft.value.compareTo(listRight.value) <= 0){
            winnerHead = listLeft;
            winnerHead.next = mergeLists(winnerHead.next, listRight);
        }
        else{
            winnerHead = listRight;
            winnerHead.next = mergeLists(listLeft, winnerHead.next);
        }
        return winnerHead;
    }

    public static void main(String[] args) {
        LinkedList<Integer> intLinkedList = new LinkedList<>();
        intLinkedList.pushNodes(1,3,13,5,8,21,2);
        intLinkedList.displayList();

        MergeSort_LinkedList<Integer> intListSorter = new MergeSort_LinkedList<>();
        LinkedListNode<Integer> intSortedHead = intListSorter.mergeSort(intLinkedList.getHead());
        intLinkedList.setHead(intSortedHead);
        System.out.print("\nAfter sorting the nodes in linked list:");
        intLinkedList.displayList();

        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.pushNodes("G","A","F","J","B","E","D","C","E");
        stringLinkedList.displayList();

        MergeSort_LinkedList<String> stringListSorter = new MergeSort_LinkedList<>();
        LinkedListNode<String> stringSortedHead = stringListSorter.mergeSort(stringLinkedList.getHead());
        stringLinkedList.setHead(stringSortedHead);
        System.out.print("\nAfter sorting the nodes in linked list:");
        stringLinkedList.displayList();
    }
}