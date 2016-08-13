package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/11/2016
 */
public class CommonSequence<T extends Comparable<T>> {

    ArrayList<T> sequenceA, sequenceB;
    int lengthA, lengthB;
    int[][] subSequence;

    CommonSequence(ArrayList<T> seqA, ArrayList<T> seqB){
        lengthA = seqA.size();
        lengthB = seqB.size();
        sequenceA = seqA;
        sequenceB = seqB;
        subSequence = new int[lengthA + 1][lengthB + 1];
    }

    int getSequenceLength(){
        for (int i=1; i<=lengthA; ++i){
            for (int j=1; j<=lengthB; ++j){
                if(sequenceA.get(i-1) == sequenceB.get(j-1))
                    subSequence[i][j] = subSequence[i-1][j-1] + 1;
                else
                    subSequence[i][j] = Math.max(subSequence[i-1][j], subSequence[i][j-1]);
            }
        }
        return subSequence[lengthA][lengthB];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> intSequenceA = new ArrayList<>(Arrays.asList(5,3,1,7,6,2,4));
        ArrayList<Integer> intSequenceB = new ArrayList<>(Arrays.asList(7,3,1,5,6,4,2));
        CommonSequence<Integer> commonSequence = new CommonSequence<>(intSequenceA, intSequenceB);
        System.out.println(commonSequence.getSequenceLength());
    }
}
