package StringManipulation;

import java.util.Scanner;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/11/2016
 */
public class LexicographicalSequence {

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

    String previousLexicographical(String input){

        char[] array = input.toCharArray();
        int i = array.length - 1;
        while (i > 0 && array[i - 1] <= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return input;

        // Let array[i - 1] be the pivot
        // Find rightmost element that is less than the pivot
        int j = i - 1;
        while (j + 1 < array.length && array[j + 1] <= array[i - 1])
            j++;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        char temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        int n = array.length - 1;
        while (i < n) {
            temp = array[i];
            array[i] = array[n];
            array[n] = temp;
            i++;
            n--;
        }
        return new String(array);
    }

    public static void main(String[] args) {
        LexicographicalSequence currObj = new LexicographicalSequence();
        System.out.println("Enter the string to find its permutations:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.printf("\nThe next lexicographical permutation of %s is: %s", input, currObj.nextLexicographical(input));
        System.out.printf("\nThe previous lexicographical permutation of %s is: %s", input, currObj.previousLexicographical(input));
    }
}
