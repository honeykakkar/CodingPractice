package DataStructures;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/4/2016
 */

// Given two strings, write a method to decide if one is a permutation of the other.

public class Anagrams
{
    private boolean isAnagram(CharSequence input1, CharSequence input2)
    {
        if(input1.length() != input2.length())
            return false;
        int[] char_flags = new int[256];
        for(int i=0; i<input1.length(); ++i)
            ++char_flags[input1.charAt(i)];

        for (int i=0; i<input2.length(); ++i)
        {
            if(--char_flags[input2.charAt(i)] < 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        Anagrams currentObject = new Anagrams();
        System.out.println("Enter the strings to be checked for anagrams:");
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        String input2 = scanner.nextLine();
        long startTime = System.nanoTime();
        boolean result = currentObject.isAnagram(input1, input2);
        long timeTaken = System.nanoTime() - startTime;
        if(result)
            System.out.println("Both strings are anagrams to each other.");
        else
            System.out.println("Both strings are not anagrams to each other.");
        System.out.println("\nThe algorithm took " + timeTaken + " nanoseconds.");
    }
}
