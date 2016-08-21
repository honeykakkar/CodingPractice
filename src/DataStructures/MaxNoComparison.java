package DataStructures;

import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/5/2016
 */

public class MaxNoComparison {

    private int sign(int i){
        return flip ((i >> 31) & 0x1);
    }

    private int flip(int i){
        return  1^i;
    }

    private int getLarger(int a, int b){
        int signA = sign(a);
        int signB = sign(b);
        int signC = sign(a-b);
        int selectSignA = signA^signB;
        int selectSignC = flip(signA^signB);
        int selectA = signA*selectSignA + signC*selectSignC;
        int selectB = flip(selectA);
        return a*selectA + b*selectB;
    }

    public static void main(String[] args) {
        MaxNoComparison currentObject = new MaxNoComparison();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number:");
        int a = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter second number:");
        int b = scanner.nextInt();
        long startTime = System.nanoTime();
        int larger = currentObject.getLarger(a,b);
        long timeTaken = System.nanoTime() - startTime;
        System.out.println("Larger of two is: " + larger);
        System.out.println("\nThe algorithm took " + timeTaken +" nanoseconds.");
    }
}
