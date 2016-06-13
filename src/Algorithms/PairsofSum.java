package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by honey on 6/12/2016.
 */
public class PairsofSum {

    private void pairsOfSum(ArrayList<Integer> array, int sum){
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        int i =0;
        for(int element : array) {
            if (hashMap.get(sum - element) == null)
                hashMap.put(element, true);
            else {
                ++i;
                System.out.println("Pair " + i + ": " + element + ", " + (sum - element));
            }
        }
        System.out.println("\n" + i + " pairs found.");
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
        System.out.println("\nEnter a value which all pairs of integers within an array should sum to:");
        int sum = scanner.nextInt();
        PairsofSum currentObject = new PairsofSum();
        System.out.println("Showing all pairs of integers within an array which sum to " + sum + ":\n");
        long startTime = System.nanoTime();
        currentObject.pairsOfSum(array, sum);
        long endTime = System.nanoTime();
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");
    }
}
