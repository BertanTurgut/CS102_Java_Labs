package algorithms;

public class sort_algorithms {
    public static void main(String[] args) {
        int[] array = {14, 8, 23, 34, 7, 11, 25, 21, 9};

        for (int element : selectionSort(array)) {
            System.out.println(element);
        }

        System.out.println("******");

        for (int element : mergeSort(array)) {
            System.out.println(element);
        }

        System.out.println("******");

    }

    public static void copyElements(int[] arrayOriginal, int[] arrayCopy) {
        for (int i = 0; i < arrayOriginal.length; i++) {
            arrayCopy[i] = arrayOriginal[i];
        }
    } 

    public static void swapElements(int[] array, int firstIndex, int secondIndex) {
        int temporary_data = array[firstIndex];
        array[firstIndex] = secondIndex;
        array[secondIndex] = temporary_data;
    }

    public static int[] selectionSort(int[] arrayOriginal) {
        int[] array = new int[arrayOriginal.length];
        copyElements(arrayOriginal, array);

        int i = 0;
        while(i < array.length) {
            int smallest = array[i];
            int smallestIndex = i;

            for (int j = i; j < array.length; j++) {
                if (array[j] < smallest) {
                    smallestIndex = j;
                    smallest = array[j];
                }
            }

            array[smallestIndex] = array[i];
            array[i] = smallest;
            i++;
        }

        return array;
    }

    public static int[] mergeSort(int[] arrayOriginal) {
        int[] array = new int[arrayOriginal.length];
        copyElements(arrayOriginal, array);
         
        if (array.length == 1) {
            return array;
        } 
        else {
            int middle = array.length / 2;
            int[] firstHalf = new int[middle];
            int[] secondHalf = new int[array.length - middle];
            for (int i = 0; i < middle; i++)
                firstHalf[i] = array[i];
            for (int i = middle; i < array.length; i++) 
                secondHalf[i - middle] = array[i];

            return merge(mergeSort(firstHalf), mergeSort(secondHalf));
        }
    }

    public static int[] merge(int[] firstArray, int[] secondArray) {
        int[] mergedArray = new int[firstArray.length + secondArray.length];

        int i = 0;
        int j = 0;
        while (i != firstArray.length || j != secondArray.length) {
            if (j == secondArray.length || (i != firstArray.length && firstArray[i] < secondArray[j])) {
                mergedArray[i + j] = firstArray[i];
                i++;
            }
            else {
                mergedArray[i + j] = secondArray[j];
                j++;
            }
        }

        return mergedArray;
    }

    public static void quickSort(int[] array, int start, int end) {

    }

    public static int partition(int[] array, int start, int end) {
        return 0;
    }

    // from geeksforgeeks
    public static int binarySearch(int[] sortedArray, int searchedElement) {
        int l = 0;
        int r = sortedArray.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (sortedArray[m] == searchedElement) {
                return m;
            }
            if (sortedArray[m] < searchedElement) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }

        return -1;
    }
}  
