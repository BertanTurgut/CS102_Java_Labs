package lab5;

public class Player {
    private static int score;
    private static int move;
    private static int grid_count;

    public static int getScore() {return score;}
    public static int getMove() {return move;}
    public static int get_grid_count() {return grid_count;}

    public static void setScore(int number) {score = number;}
    public static void setMove(int number) {move = number;}
    public static void set_grid_count(int number) {grid_count = number;}

    public static void manageScore (boolean correct_move) {
        if (correct_move) {
            score += 10;
        }
        else {
            score--;
        }
    }

    public static void increaseMove (int number) {
        move += number;
    }

    public static void increase_grid_count(int number) {
        grid_count += number;
    }
}
