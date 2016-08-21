package DataStructures;

import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/3/2016
 */

// Implement an algorithm to determine if a string has all unique characters

public class UniqueChars
{
    boolean isUniqueChars(CharSequence input)
    {
        if(input.length()>256)
            return false;
        boolean[] char_flags = new boolean[256];
        for (int i=0; i<input.length(); ++i)
        {
            if(char_flags[input.charAt(i)])
                return false;
            char_flags[input.charAt(i)] = true;
        }
        return true;
    }

    public static void main(String[] args)
    {
        UniqueChars currentObject = new UniqueChars();
        System.out.println("Enter the string to be checked:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        long startTime = System.nanoTime();
        boolean result = currentObject.isUniqueChars(input);
        long timeTaken = System.nanoTime() - startTime;
        if(result)
            System.out.println("Input contains all unique characters.");
        else
            System.out.println("Input does not contain all unique characters.");
        System.out.println("\nThe algorithm took " + timeTaken + " nanoseconds.");
    }
}
