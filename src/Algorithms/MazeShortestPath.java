package Algorithms;

/**
 * Author: honey
 * Date created: 6/14/2016
 */
import java.util.*;

public class MazeShortestPath {

    private int rows, cols;
    private char[][] maze;
    private ArrayList<ArrayList<Point>> mazeSolutions;

    MazeShortestPath(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        maze = new char[rows][cols];
        mazeSolutions = new ArrayList<>();
    }

    private void displayMaze(){
        System.out.println();
        for(int i=0; i<rows; ++i){
            for(int j=0; j<cols; ++j)
                System.out.print(maze[i][j]);
            System.out.println();
        }
    }

    private void highlightPath(ArrayList<Point> path){
        for (Point current : path){
            maze[current.x][current.y] = '-';
        }
        Point end = path.get(path.size()-1);
        maze[end.x][end.y] = 'D';
        Point start = path.get(0);
        maze[start.x][start.y] = 'S';
        displayMaze();
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "x = " + x + " y = " + y;
        }
    }

    private void findPaths(int x, int y, ArrayList<Point> path) {
        if (x < 0 || y < 0 || x > rows - 1 || y > cols - 1
                || maze[x][y] == 'X'
                || maze[x][y] == 'V') {
            return;
        } else if (maze[x][y] == 'D') {
            path.add(new Point(x, y));
            mazeSolutions.add(path);
        } else {
            maze[x][y] = 'V';
            path.add(new Point(x, y));
            findPaths(x, y - 1, new ArrayList<>(path));
            findPaths(x, y + 1, new ArrayList<>(path));
            findPaths(x - 1, y, new ArrayList<>(path));
            findPaths(x + 1, y, new ArrayList<>(path));
            maze[x][y] = 'U';
        }
    }

    private int findShortestPath() {
        int shortest = mazeSolutions.get(0).size();
        int index = 0;
        for (int i = 0; i < mazeSolutions.size(); i++) {
            if (mazeSolutions.get(i).size() < shortest) {
                shortest = mazeSolutions.get(i).size();
                index = i;
            }
        }
        return index;
    }

    private boolean isOpen(int x, int y) {
        return (x >= 0 && x < maze.length) && (y >= 0 && y < maze[x].length) && (maze[x][y] == '.' || maze[x][y] == 'S' || maze[x][y] == 'D');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of columns:");
        int cols = scanner.nextInt();
        scanner.nextLine();
        MazeShortestPath solveMaze = new MazeShortestPath(rows, cols);
        System.out.println("Enter the matrix representation of maze below using following values:\nBlocked = 'X'\nOpen = '.'\nStart = 'S'\nDestination = 'D'");
        int startX = 0, startY = 0;
        for (int i = 0; i < rows; ++i) {
            System.out.println("Enter the complete row " + (i + 1) + " as string:");
            String input = scanner.nextLine();
            for (int j = 0; j < cols; ++j) {
                solveMaze.maze[i][j] = input.charAt(j);
                if (solveMaze.maze[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }
        solveMaze.displayMaze();
        solveMaze.findPaths(startX,startY, new ArrayList<>());
        int shortestIndex = solveMaze.findShortestPath();
        if(solveMaze.mazeSolutions.size() == 0)
            System.out.println("Destination or path to destination was not found.");
        else
            System.out.println("Total number of path(s) found: " + solveMaze.mazeSolutions.size());
        System.out.println("The shortest path has " + (solveMaze.mazeSolutions.get(shortestIndex).size() - 1) + " moves.");
        solveMaze.highlightPath(solveMaze.mazeSolutions.get(shortestIndex));
    }
}
