package Sorting;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/7/2016
 */
public class QuickSort<T extends Comparable<T>> {

    public void quickSort(ArrayList<T> array, int l, int r){
        if(l<r){
            int pivot = partition(array, l, r);
            quickSort(array, l, pivot - 1);
            quickSort(array, pivot + 1, r);
        }
    }

    // using Lomuto partition scheme
    public int partition(ArrayList<T> array, int l, int r){
        T pivot = array.get(r);
        int i = l;
        for(int j = l; j<=r-1; ++j){
            if(array.get(j).compareTo(pivot) <= 0){
                swap(array, i, j);
                ++i;
            }
        }
        swap(array, i, r);
        return i;
    }

    public void swap(ArrayList<T> array, int first, int second){
        T temp = array.get(first);
        array.set(first, array.get(second));
        array.set(second, temp);
    }

    public void display(ArrayList<T> array){
        for (T element : array)
            System.out.print(element + " ");
        System.out.println();
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
        QuickSort<Integer> intQS = new QuickSort<>();
        intQS.quickSort(intArray, 0, n - 1);
        System.out.println("\nDisplaying elements in the array after performing quicksort:");
        intQS.display(intArray);

        System.out.println("\nEnter the size of list of strings:");
        n = sc.nextInt();
        sc.nextLine();
        ArrayList<String> stringArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            System.out.println("Enter element " + (i + 1) + " of list:");
            stringArray.add(i, sc.nextLine());
        }
        QuickSort<String> stringQS = new QuickSort<>();
        stringQS.quickSort(stringArray, 0, n-1);
        System.out.println("\nDisplaying elements in the array after performing quicksort:");
        stringQS.display(stringArray);
    }
}
