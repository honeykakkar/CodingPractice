package DataStructures;

import java.util.HashSet;

/**
 * Created by honey on 6/4/2016.
 */

// Write code to remove duplicates from an unsorted linked list.

    // Approach is to use Hashset if auxillary space is allowed.

public class RemoveDuplicates {

    private void removeDuplicates(DataStructures.LinkedList.LinkedListNode head){
        if(head == null || head.getNext() == null)
            return;
        DataStructures.LinkedList.LinkedListNode tempNode = head;
        DataStructures.LinkedList.LinkedListNode previousNode = null;
        HashSet nodeSet = new HashSet();
        while (tempNode != null){
            if(!nodeSet.contains(tempNode.getValue()))
                nodeSet.add(tempNode.getValue());
            else{
                if (previousNode != null) {
                    previousNode.setNext(tempNode.getNext());
                }
                tempNode.setNext(null);
                tempNode = previousNode;
            }
            previousNode = tempNode;
            tempNode = tempNode.getNext();
        }
    }

    public static void main(String[] args) {
        DataStructures.LinkedList<Integer> integerLinkedList = new DataStructures.LinkedList<>();
        integerLinkedList.pushNodes(61, 59, 19, 23, 23, 11, 34, 56, 7, 11, 97, 3, 19, 17, 11, 97, 47, 61, 19, 7, 59);
        integerLinkedList.displayList();
        RemoveDuplicates currentObject = new RemoveDuplicates();
        long startTime = System.nanoTime();
        currentObject.removeDuplicates(integerLinkedList.head);
        long timeTaken = System.nanoTime() - startTime;
        integerLinkedList.displayList();
        System.out.println("\nThe algorithm took " + timeTaken + " nanoseconds.");
    }
}
