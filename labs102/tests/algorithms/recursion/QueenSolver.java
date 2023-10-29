package algorithms.recursion;

public class QueenSolver {
    final private static int default_number = 8;
    final public static int[][] default_board = new int[default_number][default_number];
    final public static int default_queen_count = default_number;
    // board[row][column]

    public static void main(String[] args) {
        solver(default_board, default_queen_count);
    }

    public static boolean solver(int[][] board, int queenCount) {
        for (int i=0; i < board.length; i++) {
            for (int j=0; j < board[i].length; j++) {
                if (isValidPosition(board, j, i)) {
                    if (queenCount <= 1) {
                        board[i][j] = 1;
                        System.out.println(boardString(board));
                        return true;
                    }
                    else {
                        board[i][j] = 1;
                        if (solver(board, queenCount-1)) {
                            return true;
                        }
                        else {
                            board[i][j] = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isValidPosition(int[][] board, int x, int y) {
        return isValidRow(board, x, y) && isValidColumn(board, x, y) && isValidDiagonal(board, x, y) && board[y][x] == 0;
    }

    public static boolean isValidColumn(int[][] board, int x, int y) {
        for (int j=0; j < board.length; j++) {
            if (board[j][x] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidRow(int[][] board, int x, int y) {
        for (int i=0; i < board[y].length; i++) {
            if (board[y][i] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDiagonal(int[][] board, int x, int y) {
        for (int i=0; i < board.length; i++) {
            for (int j=0; j < board[i].length; j++) {
                if (board[i][j] == 1 && (i-y == j-x || y-i == j-x)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String boardString(int[][] board) {
        String str = "";
        for (int i=0; i < board.length; i++) {
            str += "\n";
            for (int j=0; j < board[i].length; j++) {
                str += board[i][j] + " ";
            }
        }
        return str;
    }
}
