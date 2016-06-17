package DataStructures;

import DataStructures.LinkedList.LinkedListNode;

import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/16/2016
 */

/*You have two numbers represented by a linked list, where each node contains a
        single digit. The digits are stored in reverse order, such that the 7 's digit is at the head
        of the list. Write a function that adds the two numbers and returns the sum as a
        linked list.*/

public class AddLists {

    private LinkedListNode<Integer> addLists(LinkedListNode<Integer> firstList, LinkedListNode<Integer> secondList) {
        if (firstList == null)
            return secondList;
        if (secondList == null)
            return firstList;
        int sum, carry = 0;
        LinkedListNode<Integer> firstHead = firstList;
        LinkedListNode<Integer> secondHead = secondList;
        LinkedListNode<Integer> resultTail = null;
        LinkedList<Integer> resultList = new LinkedList<>();

        while (firstHead != null && secondHead != null) {
            sum = firstHead.value + secondHead.value + carry;
            carry = sum / 10;
            LinkedListNode<Integer> newNode = new LinkedListNode<>(sum % 10, null);
            if (resultList.head == null) {
                resultList.head = newNode;
                resultTail = resultList.head;
            } else {
                if (resultTail != null)
                    resultTail.next = newNode;
                resultTail = newNode;
            }
            firstHead = firstHead.next;
            secondHead = secondHead.next;
        }

        while (firstHead != null) {
            sum = carry + firstHead.value;
            carry = sum / 10;
            LinkedListNode<Integer> newNode = new LinkedListNode<>(sum % 10 , null);
            resultTail.next = newNode;
            resultTail = newNode;
            firstHead = firstHead.next;
        }

        while (secondHead != null) {
            sum = carry + secondHead.value;
            carry = sum / 10;
            LinkedListNode<Integer> newNode = new LinkedListNode<>(sum % 10 , null);
            resultTail.next = newNode;
            resultTail = newNode;
            secondHead = secondHead.next;
        }

        if(carry != 0){
            resultTail.next = new LinkedListNode<>(carry , null);
        }


        return resultList.head;
    }

    public static void main(String[] args) {
        LinkedList<Integer> firstList = new LinkedList<>();
        System.out.println("Enter the first number:");
        Scanner scanner = new Scanner(System.in);
        String firstNumber = scanner.nextLine();
        for(int i=firstNumber.length()-1; i >= 0; --i){
            Integer current = new Integer(firstNumber.substring(i,i+1));
            firstList.pushNode(current);
        }
        firstList.displayList();

        LinkedList<Integer> secondList = new LinkedList<>();
        System.out.println("\nEnter the second number:");
        String secondNumber = scanner.nextLine();
        for(int i=secondNumber.length()-1; i >= 0; --i){
            Integer current = new Integer(secondNumber.substring(i,i+1));
            secondList.pushNode(current);
        }
        secondList.displayList();
        AddLists currentObject = new AddLists();
        LinkedListNode<Integer> resultHead = currentObject.addLists(firstList.head, secondList.head);
        LinkedList.displayList(resultHead);
    }
}