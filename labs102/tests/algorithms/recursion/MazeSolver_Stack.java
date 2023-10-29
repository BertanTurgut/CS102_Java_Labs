package algorithms.recursion;

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver_Stack {
    // maze[row][column]
    // 0 -> movable location
    // 1 -> barrier
    // 2 -> trail

    public static void main(String[] args) {
        
    }

    public static boolean Solver(int[][] maze, int start_x, int start_y) {
        Stack<String> stack = new Stack<>();

        for (int index=0; index < validDirections(maze, start_x, start_y).size(); index++) {
            int[] new_cooridnates = new int[2];
            stack.push(validDirections(maze, start_x, start_y).get(index));
            switch (stack.pop()) {
                case "north":  
                    new_cooridnates = move(maze, start_x, start_y, "north");
                    break;
                case "south":
                    new_cooridnates = move(maze, start_x, start_y, "south");
                    break;
                case "east":
                    new_cooridnates = move(maze, start_x, start_y, "east");
                    break;
                case "west":
                    new_cooridnates = move(maze, start_x, start_y, "west");
                    break;
            }
            int x_new = new_cooridnates[1];
            int y_new = new_cooridnates[0];
            if (isWin(maze, x_new, y_new)) {
                System.out.println(mazeString(maze));
                return true;
            }
            if (!validDirections(maze, x_new, y_new).isEmpty()) {
                if (Solver(maze, x_new, y_new)) {
                    return true;
                }
                else {
                    // should clear the last trailer
                }
            }       
        }
        return false;
    }

    public static int[] move(int[][] maze, int x, int y, String direction) {
        int[] new_coordinates = new int[2];
        while (!isIntersectionPoint(maze, x, y)) {
            switch (direction) {
                case "north":
                    y--;
                    maze[y][x] = 2;
                    break;
                case "south":
                    y++;
                    maze[y][x] = 2;
                    break;
                case "east":
                    x++;
                    maze[y][x] = 2;
                    break;   
                case "west":
                    x--;
                    maze[y][x] = 2;
                    break;
            }
        }
        maze[y][x] = 2;
        new_coordinates[0] = y;
        new_coordinates[1] = x;
        return new_coordinates;
        
    }

    public static boolean isIntersectionPoint(int[][] maze, int x, int y) {
        if (validDirections(maze, x, y).size() > 1) 
            return true;
        else 
            return false;
    }

    public static ArrayList<String> validDirections(int[][] maze, int x, int y) {
        ArrayList<String> directions = new ArrayList<>();
        if (isValidCoordinates(maze, x, y-1)) {
            directions.add("north");
        }
        else if (isValidCoordinates(maze, x, y+1)) {
            directions.add("south");
        }
        else if (isValidCoordinates(maze, x+1, y)) {
            directions.add("east");
        }
        else if (isValidCoordinates(maze, x-1, y)) {
            directions.add("west");
        }
        return directions;
    }

    public static boolean isValidCoordinates(int[][] maze, int x, int y) {
        return y < maze.length && x < maze[y].length && maze[y][x] == 0;
    }

    public static boolean isWin(int[][] maze, int x, int y) {
        return y == maze.length || x == maze[y].length;
    }

    public static String mazeString(int[][] maze) {
        String str = "";
        for (int i=0; i < maze.length; i++) {
            str += "\n";
            for (int j=0; j < maze[i].length; j++) {
                str += maze[i][j] + " ";
            }
        }
        return str;
    }
}
