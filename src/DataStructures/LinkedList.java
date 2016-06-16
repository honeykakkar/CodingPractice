package DataStructures;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/4/2016
 */

// Code to implement a singly linked list

public class LinkedList<T>
{
    LinkedListNode<T> head;

    LinkedList() {
        head = null;
    }

    LinkedList(LinkedListNode<T> head) {
        this.head = head;
    }

    void displayList(){
        if(head == null){
            System.out.println("\nOops !! Linked list is empty.");
            return;
        }
        System.out.print("\nDisplaying nodes in the current list:");
        LinkedListNode<T> temp = this.head;
        System.out.println();
        while(temp.next != null){
            System.out.print(temp.value + " --> ");
            temp = temp.next;
        }
        System.out.println(temp.value);
    }

    void pushNode(T data){
        LinkedListNode<T> newNode = new LinkedListNode<>(data, null);
        if(this.head == null)
            head = newNode;
        else
        {
            LinkedListNode<T> tempNode = head;
            while(tempNode.next!=null)
                tempNode = tempNode.next;
            tempNode.next = newNode;
        }
    }

    void pushNodes(T... data){
        if(data.length == 0)
            return;
        LinkedListNode<T> headNode = new LinkedListNode<>(data[0], null);
        head = headNode;
        LinkedListNode<T> tempNode = headNode;
        for(int i=1; i<data.length; ++i){
            tempNode.next = new LinkedListNode<>(data[i],null);
            tempNode = tempNode.next;
        }
    }

    void removeNode(T value){
        LinkedListNode<T> tempNode = head;
        if(head.getValue().equals(value))
            head = head.next;
        else{
            while(tempNode.next != null){
                if(tempNode.next.value.equals(value)){
                    tempNode.next = tempNode.next.next;
                    return;
                }
                tempNode = tempNode.next;
            }
        }
    }

    void clearAll(){
        this.head = null;
    }

    static class LinkedListNode<T>
    {
        private T value;
        private LinkedListNode<T> next;

        public LinkedListNode(T value) {
            this.value = value;
            this.next = null;
        }

        LinkedListNode(T value, LinkedListNode<T> next) {
            this.value = value;
            this.next = next;
        }

        T getValue() {
            return value;
        }

        LinkedListNode<T> getNext() {
            return next;
        }

        public void setValue(T value) {
            this.value = value;
        }

        void setNext(LinkedListNode<T> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.pushNode("C");
        stringLinkedList.pushNode("C++");
        stringLinkedList.pushNode("JAVA");
        stringLinkedList.pushNode("C#");
        stringLinkedList.pushNode("JAVASCRIPT");
        stringLinkedList.displayList();
        LinkedList<Integer> intLinkedList = new LinkedList<>();
        intLinkedList.pushNodes(1,2,3,5,8,13,21);
        intLinkedList.displayList();
    }
}
