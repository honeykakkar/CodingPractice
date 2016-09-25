package Algorithms;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/2/2016
 */

// Given a set, S, of n distinct integers, print the size of a maximal subset, S`, of S
//  where the sum of any 2 numbers in S` are not evenly divisible by k.

public class NonDivisibleSet {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] modCount = new int[k];
        int input;
        for(int i=0; i<n; ++i){
            input = sc.nextInt();
            ++modCount[input % k];
        }

        if(k%2 == 0)
            modCount[k/2] = Math.min(modCount[k/2], 1);

        int max = 0;
        for (int mod=1; mod<=k/2; ++mod)
            max += Math.max(modCount[mod], modCount[k-mod]);

        max += Math.min(1, modCount[0]);
        System.out.println(max);
    }
}
