package Algorithms;

import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/6/2016
 */

/*
* The computer has four slots, and each slot will contain a ball that is red (R), yellow
(Y), green (G) or blue (B). For example, the computer might have RGGB (Slot# 1 is red,
Slots #2 and #3 are green, Slot #4 is blue).
You, the user, are trying to guess the solution. You might, for example, guess YRGB.
When you guess the correct color for the correct slot, you get a "hit." If you guess a
color that exists but is in the wrong slot, you get a "pseudo-hit." Note that a slot that
is a hit can never count as a pseudo-hit.
For example, if the actual solution is RGBY and you guess GGRR, you have one hit
and one pseudo-hit.
Write a method that, given a guess and a solution, returns the number of hits and
pseudo-hits.*/


public class MasterMind {

    private static class Result{
        int hits;
        int pseudoHits;

        Result(){
            hits = 0;
            pseudoHits = 0;
        }

        void incrementHits(){
            ++hits;
        }

        void incrementPseudoHits(){
            ++pseudoHits;
        }
    }

    private int colorRepresentation(char color){
        switch (color){
            case 'R':return 0;
            case 'G':return 1;
            case 'Y':return 2;
            case 'B':return 3;
            default: return -1;
        }
    }

    private void getResult(Result result, String guess, String solution) {
        int[] occurences = new int[solution.length()];
        for (int i = 0; i < guess.length(); ++i) {
            if (guess.charAt(i) == solution.charAt(i))
                result.incrementHits();
            else
                ++occurences[colorRepresentation(solution.charAt(i))];
        }

        for (int i = 0; i < guess.length(); ++i) {
            char currentColor = guess.charAt(i);
            if (currentColor != solution.charAt(i) && colorRepresentation(currentColor)>=0
                    && occurences[colorRepresentation(currentColor)] > 0) {
                result.incrementPseudoHits();
                --occurences[colorRepresentation(currentColor)];
            }
        }
    }

    public static void main(String[] args) {
        Result result = new Result();
        MasterMind currentObject = new MasterMind();
        System.out.println("The computer has four slots, and each slot will contain a ball that is red (R), yellow" +
                "(Y), green (G) or blue (B).\nEnter your guess:");
        Scanner scanner = new Scanner(System.in);
        String guess = scanner.nextLine();
        String solution = "BYYG";
        long startTime = System.nanoTime();
        currentObject.getResult(result, guess, solution);
        long endTime = System.nanoTime();
        System.out.println("\nTotal Hits: " + result.hits + "\nTotal Pseudo-hits: " + result.pseudoHits);
        System.out.println("\nThe algorithm took " + (endTime - startTime) +" nanoseconds.");
    }
}
