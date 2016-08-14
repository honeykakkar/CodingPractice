package Algorithms;

/*
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/2/2016
 */

// You have three stacks of cylinders where each cylinder has the same diameter, but they may vary in height.
// You can change the height of a stack by removing and discarding its topmost cylinder any number of times.

// Find the maximum possible height of the stacks such that all of the stacks are exactly the same height.

import java.util.*;

public class EqualStacks {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of first stack:");
        int n1 = in.nextInt();
        System.out.println("Enter the size of second stack:");
        int n2 = in.nextInt();
        System.out.println("Enter the size of third stack:");
        int n3 = in.nextInt();
        int[] heights = new int[3];
        int sum = 0, temp;

        System.out.println("Enter the elements of first stack (from top to bottom):");
        Stack<Integer> stack1 = new Stack<>();
        for(int h1_i=0; h1_i < n1; h1_i++){
            temp = in.nextInt();
            stack1.insertElementAt(temp, 0);
            sum += temp;
        }
        heights[0] = sum; sum = 0;

        System.out.println("Enter the elements of second stack (from top to bottom):");
        Stack<Integer> stack2 = new Stack<>();
        for(int h2_i=0; h2_i < n2; h2_i++){
            temp = in.nextInt();
            stack2.insertElementAt(temp, 0);
            sum += temp;
        }
        heights[1] = sum; sum = 0;

        System.out.println("Enter the elements of third stack (from top to bottom):");
        Stack<Integer> stack3 = new Stack<>();
        for(int h3_i=0; h3_i < n3; h3_i++){
            temp = in.nextInt();
            stack3.insertElementAt(temp, 0);
            sum += temp;
        }
        heights[2] = sum;

        // Find which stack has the maximum height. Pop the top from that stack.
        // Repeat the above process until the heights of all stacks are same.

        while(!(heights[0] == heights[1] && heights[1] == heights[2])){
            int maxIndex = 0;
            if(heights[0]>=heights[1] && heights[0]>=heights[2])
                maxIndex = 0;
            if(heights[1]>=heights[2] && heights[1]>=heights[0])
                maxIndex = 1;
            if(heights[2]>=heights[1] && heights[2]>=heights[0])
                maxIndex = 2;

            int pop;
            switch(maxIndex){
                case 0: pop = stack1.pop(); heights[0] -= pop; break;
                case 1: pop = stack2.pop(); heights[1] -= pop; break;
                case 2: pop = stack3.pop(); heights[2] -= pop; break;
                default: break;
            }
        }
        System.out.println("\nThe maximum height at which all stacks will be of equal height is " + heights[0]);
    }
}