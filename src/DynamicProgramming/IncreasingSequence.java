package DynamicProgramming;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/11/2016
 */

public class IncreasingSequence {

    public int[] sequence;
    public int length;

    IncreasingSequence(int n){
        length = n;
        sequence = new int[n];
    }

    int getSequenceLength(){
        int[] subLength = new int[length];
        for(int i=0; i<length; ++i){
            subLength[i] = 1;
            for (int j=0; j<i; ++j){
                if((sequence[i] > sequence[j]) && (subLength[j] + 1 > subLength[i]))
                    subLength[i] = subLength[j] + 1;
            }
        }

        int maxLength = Integer.MIN_VALUE;
        for(int i=0; i<length; ++i){
            if(subLength[i] > maxLength)
                maxLength = subLength[i];
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of sequence: ");
        int n = sc.nextInt();
        IncreasingSequence currObject = new IncreasingSequence(n);
        for (int i=0; i<n; ++i){
            System.out.printf("Enter the element %d of the sequence : ",i+1);
            currObject.sequence[i] = sc.nextInt();
        }
        System.out.println(currObject.getSequenceLength());
    }
}
