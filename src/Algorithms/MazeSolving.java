package Algorithms;

import java.util.Scanner;

/**
 * Created by honey on 6/13/2016.
 */

// Check for a path between a source and destination in a maze.

public class MazeSolving {
    private char[][] maze;
    private int rows, cols;
    MazeSolving(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        maze = new char[rows][cols];
    }

    // Note: The following algorithm doesn't find the shortest path from start to destination
    private boolean findpath(int x, int y){
        if(x<0 || x>=rows || y<0 || y>=cols)    // check whether current position is in the boundary of maze
            return false;
        if(this.maze[x][y] == 'D')  // check whether current position is the destination
            return true;
        if(!(maze[x][y] == '.' || maze[x][y] == 'S'))   // check if current position is open or not
            return false;
        this.maze[x][y] = '-';      // Marking the cell for potential path
        if(findpath(x, y + 1))      // move in the east direction
            return true;
        if(findpath(x + 1, y))      // move in the south direction
            return true;
        if(findpath(x, y - 1))      // move in the west direction
            return true;
        if(findpath(x - 1, y))      // move in the north direction
            return true;
        this.maze[x][y] = 'V';      // Backtracking here. It means that earlier, a bad cell (or path) was chosen.
        return false;
    }

    private void startFinding(int startx, int starty){
        findpath(startx, starty);
        maze[startx][starty] = 'S';
    }

    private void displayMaze(){
        System.out.println();
        for(int i=0; i<rows; ++i){
            for(int j=0; j<cols; ++j)
                System.out.print(maze[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of test cases:");
        int tests = scanner.nextInt();
        int MI=0, MJ=0;
        while(tests>0){
            System.out.println("Enter the number of rows:");
            int rows = scanner.nextInt();
            System.out.println("Enter the number of columns:");
            int cols = scanner.nextInt();
            MazeSolving solveMaze = new MazeSolving(rows, cols);
            scanner.nextLine();
            System.out.println("Enter the matrix representation of maze below using following values:\nBlocked = 'X'\nOpen = '.'\nStart = 'S'\nDestination = 'D'");
            for(int i=0; i<rows; ++i){
                System.out.println("Enter the complete row " + (i+1) + " as string:");
                String input = scanner.nextLine();
                for(int j=0; j<cols; ++j){
                    solveMaze.maze[i][j] = input.charAt(j);
                    if(solveMaze.maze[i][j] == 'S')
                    {
                        MI = i; MJ = j;
                    }
                }
            }
            long startTime = System.nanoTime();
            solveMaze.startFinding(MI, MJ);
            long endTime = System.nanoTime();
            System.out.println("\nThe path has been highlighted with '-' character.\nDisplaying the maze after finding the path:");
            solveMaze.displayMaze();
            System.out.println("\nThe algorithm took " + (endTime-startTime) + " nanoseconds.");
            --tests;
        }
    }
}
