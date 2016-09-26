package DataStructures;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/24/2016
 */

/*You have an empty sequence, and you will be given  queries. Each query is one of these three types:

        1 x  -Push the element x into the stack.
        2    -Delete the element present at the top of the stack.
        3    -Print the maximum element in the stack.*/


public class MaximumElement {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();

        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while(q>0){
            int operation = sc.nextInt();
            switch(operation){
                case 1:
                    int element = sc.nextInt();
                    stack.push(element);
                    if(maxStack.size() == 0 || element > maxStack.peek())
                        maxStack.push(element);
                    break;
                case 2:
                    int popped = stack.pop();
                    if(maxStack.size() != 0 && popped == maxStack.peek())
                        maxStack.pop();
                    break;
                case 3:
                    System.out.println(maxStack.peek());
                    break;
                default:
                    break;
            }
            --q;
        }
    }
}
