package DataStructures;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/6/2016
 */

/*Given an array of integers, write a method to find indices m and n such that if you
sorted elements m through n, the entire array would be sorted. Minimize n - m (that
is, find the smallest such sequence).
*/

public class MinimumSorting {

    private static class Result{
        int startIndex;
        int endIndex;

        Result(){
           startIndex =0;
            endIndex = 0;
        }

        void displayIndices(){
            System.out.println("\nStart index is " + startIndex + "\nEnd index is " + endIndex);
        }
    }

    private void findIndices(Result result, ArrayList<Integer> array) {
        int tempStart = 0, tempEnd = array.size() - 1;

        // find the naive starting index by checking the increasing sequence of elements from the start of array
        for (int i = 1; i < array.size(); ++i) {
            if (array.get(i) >= array.get(i - 1))
                ++tempStart;
            else
                break;
        }
        ++tempStart;

        // find the naive ending index by checking the decreasing sequence of elements from the end of array
        for (int i = array.size() - 1; i >= 0; --i) {
            if (array.get(i) >= array.get(i - 1))
                --tempEnd;
            else
                break;
        }
        --tempEnd;

        // Now, we know the naive start and end indices.
        // Expand the window according to the elements contained in the window, and comparing them with the rest of elements
        int windowMin = Integer.MAX_VALUE, windowMax = Integer.MIN_VALUE;

        // find the elements with minimum and maximum value in the window
        for(int i=tempStart; i<=tempEnd; ++i){
            if(array.get(i)>windowMax)
                windowMax = array.get(i);
            if(array.get(i)<windowMin)
                windowMin = array.get(i);
        }

        for(int i= tempStart-1; i>=0; --i){
            if(array.get(i)<=windowMin)
            {
                tempStart = i;
                break;
            }
        }

        for(int i= tempEnd+1; i<array.size(); ++i){
            if(array.get(i)>=windowMax)
            {
                tempEnd = i;
                break;
            }
        }
        result.startIndex = tempStart;
        result.endIndex = tempEnd - 1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        System.out.println("Enter elements of array below. Type \"end\" when done:");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("end"))
                break;
            array.add(new Integer(input));
        }
        System.out.println("Showing elements in the array (insertion order):");
        for (int current : array)
            System.out.print(current + "\t");
        Result result = new Result();
        MinimumSorting currentObject = new MinimumSorting();
        long startTime = System.nanoTime();
        currentObject.findIndices(result, array);
        long timeTaken = System.nanoTime() - startTime;
        result.displayIndices();
        System.out.println("\nThe algorithm took " + timeTaken + " nanoseconds.");
    }
}
