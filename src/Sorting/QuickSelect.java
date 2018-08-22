package Sorting;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 8/6/2016
 */
public class QuickSelect<T extends Comparable<T>> {

    T kthSmallest(ArrayList<T> array, int k) {
        int left = 0;
        int right = array.size() - 1;
        while (left < right) {
            int j = partition(array, left, right);
            if (j < k) {
                left = j + 1;
            } else if (j > k) {
                right = j - 1;
            } else
                break;
        }
        return array.get(k);
    }

    T kthLargest(ArrayList<T> array, int k) {
        return kthSmallest(array, array.size() - k);
    }

    // using Lomuto partition scheme
    private int partition(ArrayList<T> array, int l, int r) {
        T pivot = array.get(r);
        int dest = l;
        for (int src = l; src < r; ++src) {
            if (array.get(src).compareTo(pivot) <= 0) {
                swap(array, dest, src);
                ++dest;
            }
        }
        swap(array, dest, r);
        return dest;
    }

    // Picks a random pivot element between l and r and
    // partitions array list [l..r] around the randomly picked
    // element using partition()
    int randomPartition(ArrayList<T> array, int l, int r) {
        int n = r - l + 1;
        int pivot = (int) (Math.random()) % n;  // generates a random number between 0 and n for this partition
        swap(array, l + pivot, r);
        return partition(array, l, r);
    }

    private void swap(ArrayList<T> array, int first, int second) {
        T temp = array.get(first);
        array.set(first, array.get(second));
        array.set(second, temp);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of list of integers:");
        int n = sc.nextInt();

        ArrayList<Integer> intArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            System.out.println("Enter element " + (i + 1) + " of list:");
            intArray.add(i, sc.nextInt());
        }
        System.out.println("\nEnter the value of K to find the Kth smallest and Kth largest element in the list:");
        int K = sc.nextInt();
        QuickSelect<Integer> intQS = new QuickSelect<>();
        System.out.println("\nKth smallest element in list is: " + intQS.kthSmallest(intArray, K - 1));
        System.out.println("\nKth largest element in list is: " + intQS.kthLargest(intArray, K));

        System.out.println("\nEnter the size of list of strings:");
        n = sc.nextInt();
        sc.nextLine();
        ArrayList<String> stringArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            System.out.println("Enter element " + (i + 1) + " of list:");
            stringArray.add(i, sc.nextLine());
        }
        System.out.println("\nEnter the value of K to find the Kth smallest element in the list:");
        K = sc.nextInt();
        QuickSelect<String> stringQS = new QuickSelect<>();
        System.out.println("\nKth smallest element in list is: " + stringQS.kthSmallest(stringArray, K - 1));
        System.out.println("\nKth largest element in list is: " + stringQS.kthLargest(stringArray, K));
    }
}

