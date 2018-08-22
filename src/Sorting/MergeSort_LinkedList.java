package Sorting;

import DataStructures.LinkedList.SingleLinkedList;
import DataStructures.LinkedList.SingleListNode;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 10/8/2016
 */

// Program to implement the merge sort for linked lists.
// Merge sort performs in O(nlogn) time irrespective of ordering of the elements

// Merge sort is an efficient sorting algorithm when it comes to linked lists.
// As it doesn't require any extra space to merge two sorted halves

public class MergeSort_LinkedList<T extends Comparable<T>> {

    private SingleListNode<T> mergeSort(SingleListNode<T> head) {
        if (head == null || head.next == null)
            return head;

        SingleListNode<T> turtle = head, hare = head.next;

        // Using turtle-hare approach (slow-fast pointer) to split the linked list in two equal halves
        while ((hare != null) && hare.next != null) {
            turtle = turtle.next;
            hare = hare.next.next;
        }

        // Splitting the list in two disconnected halves
        hare = turtle.next;
        turtle.next = null;

        SingleListNode<T> sortedLeft = mergeSort(head);
        SingleListNode<T> sortedRight = mergeSort(hare);

        return mergeLists_iterative(sortedLeft, sortedRight);
    }

    // Method to merge two sorted linked lists
    // Recursively calling it to find the next element in the final linked list
    private SingleListNode<T> mergeLists_recursive(SingleListNode<T> listLeft, SingleListNode<T> listRight) {
        SingleListNode<T> winnerHead;
        if (listLeft == null)
            return listRight;
        else {
            if (listRight == null)
                return listLeft;
        }

        if (listLeft.value.compareTo(listRight.value) <= 0) {
            winnerHead = listLeft;
            winnerHead.next = mergeLists_recursive(listLeft.next, listRight);
        } else {
            winnerHead = listRight;
            winnerHead.next = mergeLists_recursive(listLeft, listRight.next);
        }
        return winnerHead;
    }

    private SingleListNode<T> mergeLists_iterative(SingleListNode<T> listLeft, SingleListNode<T> listRight) {
        if (listLeft == null)
            return listRight;
        else {
            if (listRight == null)
                return listLeft;
        }

        SingleListNode<T> sortHead = new SingleListNode<>();
        SingleListNode<T> sorted = sortHead;

        while (listLeft != null && listRight != null) {
            if (listLeft.value.compareTo(listRight.value) <= 0) {
                sorted.next = listLeft;
                listLeft = listLeft.next;
            } else {
                sorted.next = listRight;
                listRight = listRight.next;
            }
            sorted = sorted.next;
        }

        while (listLeft != null) {
            sorted.next = listLeft;
            listLeft = listLeft.next;
            sorted = sorted.next;
        }

        while (listRight != null) {
            sorted.next = listRight;
            listRight = listRight.next;
            sorted = sorted.next;
        }
        return sortHead.next;
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> intLinkedList = new SingleLinkedList<>();
        intLinkedList.pushNodes(1, 3, 13, 5, 8, 21, 2);
        intLinkedList.displayList();

        MergeSort_LinkedList<Integer> intListSorter = new MergeSort_LinkedList<>();
        SingleListNode<Integer> intSortedHead = intListSorter.mergeSort(intLinkedList.getHead());
        intLinkedList.setHead(intSortedHead);
        System.out.print("\nAfter sorting the nodes in linked list:");
        intLinkedList.displayList();

        SingleLinkedList<String> stringLinkedList = new SingleLinkedList<>();
        stringLinkedList.pushNodes("G", "A", "F", "J", "B", "E", "D", "C", "E");
        stringLinkedList.displayList();

        MergeSort_LinkedList<String> stringListSorter = new MergeSort_LinkedList<>();
        SingleListNode<String> stringSortedHead = stringListSorter.mergeSort(stringLinkedList.getHead());
        stringLinkedList.setHead(stringSortedHead);
        System.out.print("\nAfter sorting the nodes in linked list:");
        stringLinkedList.displayList();
    }
}