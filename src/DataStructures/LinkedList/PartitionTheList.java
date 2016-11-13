package DataStructures.LinkedList;

import java.util.Objects;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/15/2016
 */

/*Write code to partition a linked list around a value x, such that all nodes less than x
        come before all nodes greater than or equal to x.*/

public class PartitionTheList {

    // Maintaining two lists: one for elements whose value < K and the other for elements with value > K
    // Creating two dummy nodes as heads for the lists

    private SingleListNode<Integer> partitionAround(SingleListNode<Integer> listHead, int K) {
        SingleListNode<Integer> smallerHead = new SingleListNode<>(0);
        SingleListNode<Integer> biggerHead = new SingleListNode<>(0);
        SingleListNode<Integer> smaller = smallerHead, bigger = biggerHead;
        while (listHead != null) {
            if (listHead.value < K) {
                smaller.next = listHead;
                smaller = smaller.next;
            } else {
                bigger.next = listHead;
                bigger = bigger.next;
            }
            listHead = listHead.next;
        }
        // no need for extra traversal because of fake heads
        smaller.next = biggerHead.next; // join bigger after smaller
        bigger.next = null; // cut off bigger as it is the end of list
        return smallerHead.next;
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> intList = new SingleLinkedList<>();
        System.out.println("Enter nodes of list below. Type \"end\" when done:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("end"))
                break;
            intList.pushNode(new Integer(input));
        }
        intList.displayList();
        PartitionTheList currentObject = new PartitionTheList();
        System.out.println("Enter the number which you want the linked list to partition around:");
        int K = scanner.nextInt();
        long startTime = System.nanoTime();
        SingleListNode<Integer> listHead = currentObject.partitionAround(intList.head, K);
        long endTime = System.nanoTime();
        intList.setHead(listHead);
        System.out.println("Displaying the list after being processed:");
        intList.displayList();
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");
    }
}
