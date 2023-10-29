import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class solver {
    /**
     * This method returns an ArrayList<Integer>, which contains the prime number between X and Y.
     * @param x
     * @param y
     * @return
     */
    public static ArrayList<Integer> findPrimesBetween(int x, int y) {
        int biggest = 0;
        int smallest = 0;
        if (x != y) {
            biggest = x < y ? y : x;
            smallest = x < y ? x : y;
        }
        else {
            return null;
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = smallest + 1; i < biggest; i++) {
            boolean isPrime = true;
            for (int a = 2; a <= i / 2; a++) {
                if (i % a == 0) {
                    isPrime = false;
                }
            }

            if (isPrime && i > 1) {
                primes.add(i);
            }
        }
        
        return primes;
    }

    /**
     * This method returns the minimum number of equal cubes to fill the volume with the given sizes, and the cube side length.
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static int[] findCubeNumberForVolume(int x, int y, int z) {
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        ArrayList<Integer> zList = new ArrayList<>();

        for (int i = 1; i <= x; i++) {
            if (x % i == 0) {
                xList.add(i);
            }
        }

        for (int i = 1; i <= y; i++) {
            if (y % i == 0) {
                yList.add(i);
            }
        }

        for (int i = 1; i <= z; i++) {
            if (z % i == 0) {
                zList.add(i);
            }
        }

        int greatestCommonDivider = 1; // default
        for (int i = 0; i < xList.size(); i++) {
            boolean match1 = false;
            boolean match2 = false;
            for (int u = 0; u < yList.size(); u++) {
                if (xList.get(i) == yList.get(u)) {
                    match1 = true;
                }
            }

            for (int u = 0; u < zList.size(); u++) {
                if (xList.get(i) == zList.get(u)) {
                    match2 = true;
                }
            }

            if (match1 && match2 && xList.get(i) > greatestCommonDivider) {
                greatestCommonDivider = xList.get(i);
            }
        }

        int[] arr = {(x / greatestCommonDivider) * (y / greatestCommonDivider) * (z / greatestCommonDivider), greatestCommonDivider};
        return arr;
    }

    /**
     * This method returns the union area of two rectangles with the given coordinates and sizes, and the intersection area.
     * @param leftCornerX
     * @param widthX
     * @param heightX
     * @param leftCornerY
     * @param widthY
     * @param heightY
     * @return
     */
    public static int[] findUnionArea(int leftCornerAbscissaX, int leftCornerOrdinateX, int widthX, int heightX, 
        int leftCornerAbscissaY, int leftCornerOrdinateY, int widthY, int heightY) {
        int topX = leftCornerOrdinateX;
        int bottomX = leftCornerOrdinateX - heightX;
        int leftX = leftCornerAbscissaX;
        int rightX = leftCornerAbscissaX + widthX;

        int topY = leftCornerOrdinateY;
        int bottomY = leftCornerOrdinateY - heightY;
        int leftY = leftCornerAbscissaY;
        int rightY = leftCornerAbscissaY + widthY;
        
        int intersection = 0; // default
        if (((leftY >= leftX && leftY <= rightX) || (rightY >= leftX && rightY <= rightX)) && 
            ((topY >= bottomX && topY <= topX) || (bottomY >= bottomX && bottomY <= topX))) {
            int[] abscissa = {leftX, rightX, leftY, rightY};
            int[] ordinate = {topX, bottomX, topY, bottomY};
            
            boolean orderedX = false;
            boolean orderedY = false;

            while (!orderedX) {
                int shift = 0; // shift counter
                for (int i = 0; i < abscissa.length - 1; i++) {
                    if (abscissa[i] > abscissa[i + 1]) {
                        int a = abscissa[i];
                        int b = abscissa[i + 1];
                        abscissa[i] = b;
                        abscissa[i + 1] = a;
                        
                        shift++;
                    }
                }

                orderedX = shift == 0 ? true : false;
            }

            while (!orderedY) {
                int shift = 0; // shift counter
                for (int i = 0; i < ordinate.length - 1; i++) {
                    if (ordinate[i] > ordinate[i + 1]) {
                        int a = ordinate[i];
                        int b = ordinate[i + 1];
                        ordinate[i] = b;
                        ordinate[i + 1] = a;
                        
                        shift++;
                    }
                }

                orderedY = shift == 0 ? true : false;
            }

            intersection = (abscissa[2] - abscissa[1]) * (ordinate[2] - ordinate[1]);
        }
        
        int[] arr = {widthX * heightX + widthY * heightY - intersection, intersection};
        return arr;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int userChoice = 0;
        do
        {
            System.out.print("\n\n1. Prime Numbers\n" +
            "2. Volume Filling\n" +
            "3. Union Area\n" +
            "4. Random Questions\n" +
            "5. Exit\n\n" +
            "Please enter your choice: ");
            userChoice = input.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.print("\n- Find the prime numbers in the range between X and Y.\n" +
                        "Please enter X, Y: ");
                    int X = input.nextInt();
                    int Y = input.nextInt();

                    ArrayList<Integer> primes = findPrimesBetween(X, Y);
                    String list = "";
                    for (int i = 0; i < primes.size(); i++) {
                        if (i == primes.size() - 1) {
                            list += primes.get(i);
                        }
                        else {
                            list += primes.get(i) + ", ";
                        }
                    }

                    if (list.equals("")) {
                        System.out.print("\nThere are not any prime numbers in range [" + X + ", " + Y  + "].");
                    }
                    else {
                        System.out.print("\nResult: Prime numbers in range [" + X + ", " + Y  + "] are " + list + ".");
                    }

                    break;
                case 2:
                    System.out.print("\n- A rectangular prism volume of dimensions X, Y and Z is to be filled using cube blocks. What is the minimum number of cubes required?\n" + 
                        "Please enter X, Y, Z: ");
                    X = input.nextInt();
                    Y = input.nextInt();
                    int Z = input.nextInt();

                    int[] arr = findCubeNumberForVolume(X, Y, Z);
                    System.out.print("\nResult: Using cubes of edge length " + arr[1] + " you need " + arr[0] + " blocks minimum.");

                    break;
                case 3:
                    System.out.print("\n- What is the area of the union of two rectangles R1 and R2, where top left corner of R1 is (X1,Y1) and its size is (W1,H1), and top left corner of R2 is (X2,Y2) and its size is (W2,H2)?\n" + 
                        "Please enter X1, Y1, W1, H1, X2, Y2, W2, H2: ");
                        int X1 = input.nextInt();
                        int Y1 = input.nextInt();
                        int W1 = input.nextInt();
                        int H1 = input.nextInt();
                        int X2 = input.nextInt();
                        int Y2 = input.nextInt();
                        int W2 = input.nextInt();
                        int H2 = input.nextInt();

                        if (W1 > 0 && W2 > 0 && H1 > 0 && H2 > 0) {
                            int[] arr2 = findUnionArea(X1, Y1, W1, H1, X2, Y2, W2, H2);
                            System.out.print("\nResult: Intersection area is " + arr2[1] + " thus the total area of the union is " + arr2[0] + ".");
                        }
                        else {
                            System.out.print("\nCannot compute with the given inputs.");
                        }
                        
                    break;
                case 4:
                        System.out.print("\nPlease enter the number of questions you want: ");
                        int X_ = input.nextInt();
                        System.out.print("");
                        Random random = new Random();
                        for (int i_ = 1; i_ <= X_; i_++) {
                            int randomQuestion = random.nextInt(3);
                            System.out.print(i_ + ") ");
        
                            switch (randomQuestion) {
                                case 0:
                                    X = random.nextInt(1000); // boundary
                                    Y = random.nextInt(1000); // boundary

                                    if (X > Y) {
                                        int a = X;
                                        int b = Y;
                                        Y = a;
                                        X = b;
                                    }

                                    System.out.print("Find the prime numbers in the range between " + X + " and " + Y + ".\n");
                
                                    if (X != Y) {
                                        ArrayList<Integer> primes_ = findPrimesBetween(X, Y);
                                        String list_ = "";
                                        for (int a = 0; a < primes_.size(); a++) {
                                            if (a == primes_.size() - 1) {
                                                list_ += primes_.get(a);
                                            }
                                            else {
                                                list_ += primes_.get(a) + ", ";
                                            }
                                        }
                    
                                        if (list_.equals("")) {
                                            System.out.print("\nThere are not any prime numbers in range [" + X + ", " + Y  + "].\n\n");
                                        }
                                        else {
                                            System.out.print("\nResult: Prime numbers in range [" + X + ", " + Y  + "] are " + list_ + ".\n\n");
                                        }
                                    }
                                    else {
                                        System.out.print("\nThere are not any prime numbers in range [" + X + ", " + Y  + "].\n\n");
                                    }
                
                                    break;
                                case 1:
                                    X = random.nextInt(1000); // boundary
                                    Y = random.nextInt(1000); // boundary
                                    Z = random.nextInt(1000); // boundary
                                    System.out.print("A rectangular prism volume of dimensions " + X + ", " + Y + " and " + Z + " is to be filled using cube blocks. What is the minimum number of cubes required?\n");

                                    int[] arr_ = findCubeNumberForVolume(X, Y, Z);
                                    System.out.print("\nResult: Using cubes of edge length " + arr_[1] + " you need " + arr_[0] + " blocks minimum.\n\n");
                
                                    break;
                                case 2:
                                        X1 = random.nextInt();
                                        Y1 = random.nextInt();
                                        W1 = random.nextInt(1000); // boundary
                                        H1 = random.nextInt(1000); // boundary
                                        X2 = random.nextInt();
                                        Y2 = random.nextInt();
                                        W2 = random.nextInt(1000); // boundary
                                        H2 = random.nextInt(1000); // boundary
                                        System.out.print("What is the area of the union of two rectangles R1 and R2, where top left corner of R1 is (" + X1 + "," + Y1 + ") and its size is (" + W1 + "," + H1 + "), and top left corner of R2 is (" + X2 + "," + Y2 + ") and its size is (" + W2 + "," + H2 + ")?\n");
                
                                        if (W1 > 0 && W2 > 0 && H1 > 0 && H2 > 0) {
                                            int[] arr2_ = findUnionArea(X1, Y1, W1, H1, X2, Y2, W2, H2);
                                            System.out.print("\nResult: Intersection area is " + arr2_[1] + " thus the total area of the union is " + arr2_[0] + ".\n\n");
                                        }
                                        else {
                                            System.out.print("\nCannot compute with the given inputs.\n\n");
                                        }
                                        
                                    break;
                            } 
                        }
                    break;
            }
        }
        while (userChoice != 5);

        System.out.print("\nGoodbye!");
        input.close();
    }
}
