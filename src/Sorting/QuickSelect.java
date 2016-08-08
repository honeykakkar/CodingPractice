package Sorting;
import java.util.*;
/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/6/2016
 */
public class QuickSelect<T extends Comparable<T>> {

    public T kthSmallest(ArrayList<T> array, int l, int r, int k){
        if(k>0 && k<= r-l+1){
            int pivot = partition(array, l, r);
            if(pivot - l == k - 1)
                return array.get(pivot);
            if(pivot - l > k - 1)
                return kthSmallest(array, l, pivot - 1, k);
            return kthSmallest(array, pivot + 1, r, k - 1 - (pivot - l));
        }
        return null;
    }

    public T kthLargest(ArrayList<T> array, int l, int r, int k){
        return kthSmallest(array, l, r, array.size() - (k - 1));
    }

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of list of integers:");
        int n = sc.nextInt();

        ArrayList<Integer> intArray = new ArrayList<>(n);
        for(int i=0; i<n; ++i) {
            System.out.println("Enter element " + (i + 1) + " of list:");
            intArray.add(i, sc.nextInt());
        }
        System.out.println("\nEnter the value of K to find the Kth smallest and Kth largest element in the list:");
        int K = sc.nextInt();
        QuickSelect<Integer> intQS = new QuickSelect<>();
        System.out.println("\nKth smallest element in list is: " + intQS.kthSmallest(intArray, 0, n-1, K));
        System.out.println("\nKth largest element in list is: " + intQS.kthLargest(intArray, 0, n-1, K));

        System.out.println("\nEnter the size of list of strings:");
        n = sc.nextInt(); sc.nextLine();
        ArrayList<String> stringArray = new ArrayList<>(n);
        for(int i=0; i<n; ++i) {
            System.out.println("Enter element " + (i + 1) + " of list:");
            stringArray.add(i, sc.nextLine());
        }
        System.out.println("\nEnter the value of K to find the Kth smallest element in the list:");
        K = sc.nextInt();
        QuickSelect<String> stringQS = new QuickSelect<>();
        System.out.println("\nKth smallest element in list is: " + stringQS.kthSmallest(stringArray, 0, n-1, K));
        System.out.println("\nKth largest element in list is: " + stringQS.kthLargest(stringArray, 0, n-1, K));
    }
}
