package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/12/2016
 */

// Given  integers, count the number of pairs of integers whose difference is K.
public class PairsofDifference {

    private void pairsOfDiff(ArrayList<Integer> array, int diff){
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        int i =0;
        for(int element : array)
            hashMap.put(element, false);

        for(int element : array) {
            if( element+diff < Integer.MAX_VALUE && hashMap.containsKey(element + diff) && !hashMap.get(element + diff)) {
                ++i;
                System.out.println("Pair " + i + ": " + element + ", " + (element + diff));
                hashMap.put(element, true);
            }

            if(element>=diff && hashMap.containsKey(element - diff) && !hashMap.get(element - diff)) {
                ++i;
                System.out.println("Pair " + i + ": " + element + ", " + (element - diff));
                hashMap.put(element, true);
            }
        }
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
        System.out.println("\nEnter a value which all pairs of integers should result as a difference to:");
        int diff = scanner.nextInt();
        PairsofDifference currentObject = new PairsofDifference();
        System.out.println("\nShowing all pairs of integers within an array whose difference is " + diff + ":");
        long startTime = System.nanoTime();
        currentObject.pairsOfDiff(array, diff);
        long endTime = System.nanoTime();
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");
    }
}
