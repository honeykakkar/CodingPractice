package Algorithms;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by honey on 6/11/2016.
 */

/*You are given an array of integers (both positive and negative). Find the contiguous
        sequence with the largest sum. Return the sum.*/

public class ContiguousLargestSum {

    private int contiguousLargestSum(ArrayList<Integer> array){
        int currentMax = array.get(0);
        int optimumMax = currentMax;

        for (int i=1; i<array.size(); ++i){
            if(array.get(i) + currentMax > array.get(i))
                currentMax += array.get(i);
            else
                currentMax = array.get(i);

            if(currentMax>optimumMax)
                optimumMax = currentMax;
        }
        return optimumMax;
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
        ContiguousLargestSum currentObject = new ContiguousLargestSum();
        long startTime = System.nanoTime();
        int result = currentObject.contiguousLargestSum(array);
        long endTime = System.nanoTime();
        System.out.println("\n\nThe largest sum is : " + result);
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");
    }
}
