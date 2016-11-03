package Sorting;

import java.util.ArrayList;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/22/2016
 */
public class MedianofSortedLists {

    // brute force method to merge two sorted lists and calculate the median - inefficient
    Double getMedianofLists_bruteForce(ArrayList<Integer> list1, ArrayList<Integer> list2){
        Double resultMedian;
        int size1 = list1.size(), size2 = list2.size();
        ArrayList<Integer> mergedList = new ArrayList<>(size1 + size2);
        int pointer1 = 0, pointer2 = 0;
        while(pointer1 < size1 && pointer2 < size2){
            if(list1.get(pointer1).compareTo(list2.get(pointer2)) < 0){
                mergedList.add(list1.get(pointer1));
                ++pointer1;
            }
            else{
                mergedList.add(list2.get(pointer2));
                ++pointer2;
            }
        }

        while(pointer1 < size1){
            mergedList.add(list1.get(pointer1));
            ++pointer1;
        }

        while(pointer2 < size2){
            mergedList.add(list2.get(pointer2));
            ++pointer2;
        }
        int n = mergedList.size();
        resultMedian = (mergedList.get((n-1)/2) + mergedList.get(n/2))/2.0;
        return resultMedian;
    }

    // efficient method to merge two sorted lists and calculate the median
    double getMedianofLists_efficient(ArrayList<Integer> list1, ArrayList<Integer> list2){
        int size1 = list1.size(), size2 = list2.size();
        if(size1 > size2)
            return getMedianofLists_efficient(list2, list1);

        int iMinIndex = 0, iMaxIndex = size1;
        int i=0, j=0, midElement = (size1 + size2 + 1)/2;

        int med1 = 0, med2;
        while(iMinIndex <= iMaxIndex){
            i = (iMinIndex + iMaxIndex)/2;
            j = midElement - i;

            if (i < size1 && j > 0 && list1.get(i) < list2.get(j-1))
                iMinIndex = i + 1;
            else{
                if (i > 0 && j < size2 && list2.get(j) < list1.get(i-1))
                    iMaxIndex = i - 1;
                else{
                    if (i == 0)
                        med1 = list2.get(j-1);
                    else{
                        if (j == 0)
                            med1 = list1.get(i-1);
                        else
                            med1 = Math.max(list1.get(i-1), list2.get(j-1));
                    }
                    break;
                }
            }
        }

        if((size1 + size2)%2 == 1)
            return med1;

        if ( i == size1 )
            med2 = list2.get(j);
        else{
            if (j == size2)
                med2 = list1.get(i);
            else
                med2 = Math.min(list1.get(i), list2.get(j));
        }
        return (med1 + med2) / 2.0;
    }

    private void display(ArrayList<Integer> array){
        for (Integer element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        MedianofSortedLists medianFinder = new MedianofSortedLists();
        ArrayList<Integer> list1 = new ArrayList<>();
        for(int i=1; i<=10; ++i)
            list1.add(10 * i);
        medianFinder.display(list1);
        ArrayList<Integer> list2 = new ArrayList<>();
        for(int j=5; j<25; ++j)
            list2.add(7*j);
        medianFinder.display(list2);
        Double median  = medianFinder.getMedianofLists_bruteForce(list1, list2);
        System.out.println(median);
        median = medianFinder.getMedianofLists_efficient(list1, list2);
        System.out.println(median);

        list1 = new ArrayList<>();
        for(int i=1; i<=40; ++i)
            list1.add(i * i);
        medianFinder.display(list1);
        list2 = new ArrayList<>();
        for(int j=1; j<25; ++j)
            list2.add(j*j + 10*j);
        medianFinder.display(list2);
        median  = medianFinder.getMedianofLists_bruteForce(list1, list2);
        System.out.println(median);
        median = medianFinder.getMedianofLists_efficient(list1, list2);
        System.out.println(median);
    }
}
