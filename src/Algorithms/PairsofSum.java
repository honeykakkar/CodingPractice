package Algorithms;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/12/2016
 */

public class PairsofSum {

    private List<List<Integer>> pairsOfSum_Unsorted(ArrayList<Integer> array, int sum){
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        List<List<Integer>> pairs = new LinkedList<>();
        int i =0;
        for(int element : array) {
            if (hashMap.get(sum - element) == null)
                hashMap.put(element, true);
            else {
                pairs.add(new LinkedList<>());
                pairs.get(i).add(element);
                pairs.get(i).add(sum - element);
                ++i;
            }
        }
        System.out.println("\n" + pairs.size() + " pairs found.");
        return pairs;
    }

    private List<List<Integer>> pairsOfSum_Sorted(ArrayList<Integer> array, int sum){
        List<List<Integer>> pairs = new LinkedList<>();
        int low = 0, high = array.size() - 1;
        List<Integer> newPair;
        while (low < high) {
            if (array.get(low) + array.get(high) == sum) {
                newPair = new LinkedList<>();
                newPair.add(array.get(low));
                newPair.add(array.get(high));
                pairs.add(newPair);
                while (low < high && Objects.equals(array.get(low), array.get(low + 1)))
                    low++;
                while (low < high && Objects.equals(array.get(high), array.get(high - 1)))
                    high--;
                low++;
                high--;
            } else {
                if (array.get(low) + array.get(high) < sum)
                    low++;
                else
                    high--;
            }
        }
        return pairs;
    }

    public void displayPairs(List<List<Integer>> pairs){
        System.out.println();
        for(List<Integer> pair : pairs){
            System.out.print("[" + pair.get(0) + ", " + pair.get(1) + "]  ");
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
        System.out.println("\nEnter a value which all pairs of integers within an array should sum to:");
        int sum = scanner.nextInt();
        PairsofSum currentObject = new PairsofSum();
        System.out.println("\nShowing all pairs of integers within an array which sum to " + sum + ":");
        long startTime = System.nanoTime();
        List<List<Integer>> pairs = currentObject.pairsOfSum_Sorted(array, sum);
        long endTime = System.nanoTime();
        currentObject.displayPairs(pairs);
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");
    }
}
