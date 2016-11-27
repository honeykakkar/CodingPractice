package Algorithms;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/8/2016
 */

// The Slide Puzzle program displays a grid of tiles that must be arranged in ascending order.
// The user can click on any tile next to the empty space to move the tile to that space.
// The game starts with the tiles in a random arrangement.

public class TilesPuzzle {
    
    int[] board;
    int n;
    int dimension;

    TilesPuzzle(int N){
        this.dimension = N;
        this.n = N*N;
        this.board = new int[n];
    }

    void displayBoard() {
        System.out.println("\nDisplaying current configuration of board:\n");
        for (int i = 0; i < n; ++i){
            if(i%dimension != dimension-1)
                System.out.print(board[i] + " ");
            else
                System.out.println(board[i]);
        }
    }

    // In efficient method to calculate the inversions. Complexity is O(n*n).
    // Inversions can be calculated in O(nlogn) using divide and conquer like merge sort

    int getInversions(){
        int invCount = 0;
        for (int i=0; i<n; ++i){
            for (int j=i+1; j<n; ++j){
                if(board[i] != 0 && board[j] !=0 && board[j] < board[i])
                    ++invCount;
            }
        }
        return invCount;
    }

    int getEmptyPosition(){
        int position = 1;
        for (int i=n-1; i>=0; i -= dimension, ++position){
            for(int j=0; j<dimension; ++j){
                if(board[i - j] == 0)
                    return position;
            }
        }
        return 0;
    }

    boolean checkSolvability(){
        int inversions = getInversions();
        int emptyPosition = getEmptyPosition();

        if(dimension % 2 != 0 && inversions % 2 == 0)
            return true;

        if(dimension % 2 == 0 && emptyPosition % 2 == 0 && inversions % 2 != 0)
            return true;

        return dimension % 2 == 0 && emptyPosition % 2 != 0 && inversions % 2 == 0;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dimension of puzzle board:");
        int N = scanner.nextInt();
        TilesPuzzle puzzle = new TilesPuzzle(N);
        System.out.println("Enter the current state of puzzle:\nBlank:'0'");
        for (int i=0; i<N; ++i){
            for (int j=0; j<N; ++j){
                System.out.println("Enter the value of tile at position ["+ i + "]["+ j + "]:");
                puzzle.board[(i*N) + j] = scanner.nextInt();
            }
        }

        puzzle.displayBoard();
        System.out.println("\nThe puzzle can " + (puzzle.checkSolvability() ? "" : "not ") + "be solved.");
    }
}
