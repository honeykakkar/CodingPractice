package Algorithms;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 7/28/2016
 */

// The number of permutations of a given string of length n is n!. Repeated letters in a string account for
// duplicate permutations. If a letter repeats itself for l times and another for m times. The
// total unique permutations would be n! / (l! * m!).

public class StringPermutations {

    String nextLexicographical(String input){
        char[] array = input.toCharArray();
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return input;

        // Let array[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        char temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return new String(array);
    }

    // Inefficient approach
    void getAllPerms(ArrayList<String> results, String prefix, String input){
        int length = input.length();
        if(length == 0)
            results.add(prefix);
        else{
            for(int i=0; i<length; ++i)
                getAllPerms(results, prefix + input.charAt(i), input.substring(0,i) + input.substring(i+1, length));
        }
    }

    void swapChars(StringBuilder input, int i, int j){
        char temp = input.charAt(i);
        input.setCharAt(i, input.charAt(j));
        input.setCharAt(j, temp);
    }

    // Relatively more efficient approach
    void getAllPerms(ArrayList<StringBuilder> results, StringBuilder input, int start, int end){
        if(start == end) {
            StringBuilder current = new StringBuilder(input);
            results.add(current);
        }

        for(int i = start; i<= end; ++i){
            swapChars(input, start, i);
            getAllPerms(results, input, start + 1, end);
            swapChars(input, start, i);
        }
    }

    public static void main(String[] args) {
        StringPermutations currObj = new StringPermutations();
        System.out.println("Enter the string to find its permutations:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.printf("\nThe next lexicographical permutation of %s is: %s", input, currObj.nextLexicographical(input));
        ArrayList<String> permutations = new ArrayList<>();
        long startTime = System.nanoTime();
        currObj.getAllPerms(permutations, "", input);
        long endTime = System.nanoTime();
        System.out.printf("\nFollowing are the %d permutations of input string: %s\n", permutations.size(), input);
        //permutations.forEach(System.out::println);
        System.out.println("\nThe algorithm to compute all permutations took " + (endTime - startTime) + " nanoseconds.");

        ArrayList<StringBuilder> perms = new ArrayList<>();
        startTime = System.nanoTime();
        currObj.getAllPerms(perms, new StringBuilder(input), 0, input.length()-1);
        endTime = System.nanoTime();
        System.out.printf("\nFollowing are the %d permutations of input string: %s\n", perms.size(), input);
        //perms.forEach(System.out::println);
        System.out.println("\nThe algorithm to compute all permutations took " + (endTime - startTime) + " nanoseconds.");
    }
}
