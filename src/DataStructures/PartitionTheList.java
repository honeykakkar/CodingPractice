package DataStructures;

import DataStructures.LinkedList.LinkedListNode;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/15/2016
 */

/*Write code to partition a linked list around a value x, such that all nodes less than x
        come before all nodes greater than or equal to x.*/

public class PartitionTheList {

    private LinkedListNode<Integer> partitionAround(LinkedListNode<Integer> listHead, int K){
        LinkedListNode<Integer> current = listHead;
        LinkedListNode<Integer> startOfLesser = null;
        LinkedListNode<Integer> startOfGreater = null;
        while(current != null){
            LinkedListNode<Integer> currentNext = current.next;
            if(current.getValue() < K){
                current.next = startOfLesser;
                startOfLesser = current;
            }
            else {
                current.next = startOfGreater;
                startOfGreater = current;
            }
            current = currentNext;
        }
        if(startOfLesser == null)
            return startOfGreater;
        LinkedListNode<Integer> head = startOfLesser;
        while(startOfLesser.next != null)
            startOfLesser = startOfLesser.next;
        startOfLesser.next = startOfGreater;
        return head;
    }

    public static void main(String[] args) {
        LinkedList<Integer> intList = new LinkedList<>();
        System.out.println("Enter nodes of list below. Type \"end\" when done:");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
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
        LinkedListNode<Integer> head = currentObject.partitionAround(intList.head, K);
        long endTime = System.nanoTime();
        intList.setHead(head);
        System.out.println("Displaying the list after being processed:");
        intList.displayList();
        System.out.println("\nThe algorithm took " + (endTime-startTime) + " nanoseconds.");
    }
}
