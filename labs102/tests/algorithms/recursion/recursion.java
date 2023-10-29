package algorithms.recursion;

public class recursion {
    public static void main(String[] args) {
        recursion.sequence_recursion(4);

        System.out.println("******");

        int factorial = recursion.factorial_recursion(6);
        System.out.println(factorial);

        System.out.println("******");

        int fibonacci = recursion.fibonacci_recursion(10);
        System.out.println(fibonacci);

        System.out.println("******");

        recursion.collatz_conjecture_recursion(13);

        System.out.println("******");

    }

    public static void sequence_recursion(int number) {
        System.out.println(number);
        if (number > 0) {
            sequence_recursion(number - 1);
        }
    }

    public static int factorial_recursion(int number) {
        if (number > 0) {
            int x = number * factorial_recursion(number - 1);
            return x;
        }
        else {
            return 1;
        }
    }

    public static int fibonacci_recursion(int step) {
        if (step > 3) {
            return fibonacci_recursion(step - 1) + fibonacci_recursion(step - 2);
        }
        else if (step == 3) {
            return 1 + 1;   
        }
        else {
            return 1;
        }
    }

    public static int collatz_conjecture_recursion(int number) {
        System.out.println(number);
        if (number % 2 == 0) {
            return collatz_conjecture_recursion(number / 2);
        }
        else if (number == 1) {
            return 1;
        }
        else {
            return collatz_conjecture_recursion(3 * number + 1);
        }
    }
}
