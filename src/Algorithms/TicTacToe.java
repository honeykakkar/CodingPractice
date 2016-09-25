package Algorithms;

import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/5/2016
 */

// Design an algorithm to figure out if someone has won a game of tic-tac-toe.

public class TicTacToe {

    private String whoIsWinner(char[][] board, int N){
        String winner = "No one has won the game yet.";
        int row, column;
        for(row=0; row<N; ++row){
            for (column=1; column<N; ++column){
                if(board[row][column] != board[row][column-1])
                    break;
            }
            if(column==N)
                return "Player " + board[row][0] + " has won the game.";
        }

        for(column=0; column<N; ++column){
            for (row=1; row<N; ++row){
                if(board[row][column] != board[row-1][column])
                    break;
            }
            if(row==N)
                return "Player " + board[0][column] + " has won the game.";
        }

        for(row=1; row<N; ++row){
            if(board[row-1][row-1] != board[row][row])
                break;
        }
        if(row==N)
            return "Player " + board[0][0] + " has won the game.";

        for(row=1; row<N; ++row){
            if(board[row-1][N-row] != board[row][N-row-1])
                break;
        }
        if(row==N)
            return "Player " + board[0][N-1] + " has won the game.";
        return winner;
    }

    private void displayBoard(char[][] board, int N)
    {
        System.out.println();
        for(int i=0; i< N; ++i)
        {
            for(int j=0; j<N; ++j)
                System.out.print(board[i][j] + "\t");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dimension of TicTacToe board:");
        int N = scanner.nextInt();
        System.out.println("Enter the current state of board:\nBlank:'-'\tNought:'X'\tCross:'O'");
        char[][] board = new char[N][N];
        for (int i=0; i<N; ++i){
            for (int j=0; j<N; ++j){
                System.out.println("Enter the peice at position ["+ i + "]["+ j + "]:");
                board[i][j] = scanner.next().charAt(0);
            }
        }

        TicTacToe currentObject = new TicTacToe();
        System.out.println("\nCurrent state of the board:");
        currentObject.displayBoard(board, N);
        long startTime = System.nanoTime();
        String winner = currentObject.whoIsWinner(board, N);
        long timeTaken = System.nanoTime() - startTime;
        System.out.println(winner);
        System.out.println("\nThe algorithm took " + timeTaken + " nanoseconds.");

    }
}
