package Algorithms;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/8/2016
 */
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

        if(dimension % 2 == 0 && emptyPosition % 2 != 0 && inversions % 2 == 0)
            return true;

        return false;
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
