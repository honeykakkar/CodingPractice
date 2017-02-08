package BitManipulation;

import java.util.ArrayList;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/1/2016
 */

/*
    Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num,
    calculate the number of 1's in their binary representation and return them as an array.

    Example:
    For num = 5 you should return [0,1,1,2,1,2].
 */
class CountBits {

    // Counting the number of set bits in each number
    // For a 32-bit integer, perform AND on number and 1 set on each bit. If the out put is 0, means that bit was not set

    // Example : 11010
    //      AND  00001
    //           00000

     //          11010
    //      AND  00010
    //           00010  and so on....

    ArrayList<Integer> countBits_inefficient(int input){
        ArrayList<Integer> bitCounts = new ArrayList<>();
        bitCounts.add(0);
        for(int i=1; i<=input; ++i){
            int count =0;
            for (int j=0; j<32; ++j){
                if ((i & (1 << j)) != 0)
                    ++count;
            }
            bitCounts.add(count);
        }
        return bitCounts;
    }

    // In the method, the idea is to use the previously computed results
    // The number of bits set in a number are dependent whether the number is divisible by 2 or not
    //  and on the number/2.

    // Why on number/2? Because multiplying a number by 2 just shifts the set bits to the left by 1.
    // Example: 14 = 1110, 7 = 0111
    //          12 = 1100, 6 = 0110, 3 = 0011

    // If the number is not divisible by 2, the LSB is also set.

    ArrayList<Integer> countBits_efficient(int input){
        ArrayList<Integer> bitCounts = new ArrayList<>();
        int[] f = new int[input + 1];
        for (int i=1; i<=input; i++)
            f[i] = f[i >> 1] + (i & 1); // f[i] = f[i / 2] + i % 2.

        for (int aF : f) bitCounts.add(aF);
        return bitCounts;
    }
}