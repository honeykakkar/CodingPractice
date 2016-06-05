package DataStructures;

import java.util.Scanner;

/**
 * Created by honey on 6/4/2016.
 */

  /*Implement a method to perform basic string compression using the counts of
    repeated characters. For example, the string aabcccccaaa would become
    a2blc Sa3. If the "compressed" string would not become smaller than the original
    string, your method should return the original string.*/

public class CompressString
{
    private CharSequence compressString(CharSequence input)
    {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<input.length(); ++i)
        {
            int count = 1;
            while(i<input.length())
            {
                if(i + 1 == input.length() || input.charAt(i) != input.charAt(i+1))
                    break;
                else
                    ++count;
                ++i;
            }
            result.append(input.charAt(i));
            result.append(count);
        }
        return result;
    }

    public static void main(String[] args) {
        CompressString currentObject = new CompressString();
        System.out.println("Enter the string to be compressed:");
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder(scanner.nextLine());
        long startTime = System.nanoTime();
        System.out.println("The compressed input string is: " + currentObject.compressString(input));
        long timeTaken = System.nanoTime() - startTime;
        System.out.println("\nThe algorithm took " + timeTaken + " nanoseconds.");
    }
}
