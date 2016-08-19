package Algorithms;

import java.util.*;
/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/18/2016
 */

/*
A sequence of brackets is considered to be balanced if the following conditions are met:
    It contains no unmatched brackets.
    The subset of brackets enclosed within the confines of a matched pair of brackets is
    also a matched pair of brackets.

Given a string of brackets, determine whether it is balanced.*/

public class BalancedBrackets {

    public static String isBalanced(String input){
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<input.length(); ++i) {
            char currChar = input.charAt(i);
            switch (currChar) {
                case '{':
                case '(':
                case '[':
                    stack.push(currChar);
                    break;
                case '}':
                    if (stack.empty() || (stack.peek() != '{'))
                        return "NO";
                    stack.pop();
                    break;
                case ')':
                    if (stack.empty() || (stack.peek() != '('))
                        return "NO";
                    stack.pop();
                    break;
                case ']':
                    if (stack.empty() || (stack.peek() != '['))
                        return "NO";
                    stack.pop();
                    break;
            }
        }

        return stack.empty() ? "YES" : "NO";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(isBalanced(input));
    }
}

