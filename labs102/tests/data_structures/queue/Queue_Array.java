package queue;

public class Queue_Array {
    private String[] queue;
    private int rearIndex;
    private int frontIndex;

    public Queue_Array(int size) {
        this.queue = new String[size];
        this.rearIndex = 0;
        this.frontIndex = size - 1;
    }

    public void enqueue(String element) {
        for (int i = frontIndex; i > 0; i--) {
            this.queue[i] = this.queue[i - 1];
        }

        this.queue[rearIndex] = element;
    }

    public void dequeue() {
        for (int i = frontIndex; i > 0; i--) {
            this.queue[i] = this.queue[i - 1];
        }

        this.queue[rearIndex] = null;
    }

    public Boolean isEmpty() {
        for (String element : this.queue) {
            if (element != null) {
                return false;
            }
        }

        return true;
    } 

    public String toString() {
        String queue = "";

        for (String elemenet : this.queue) {
            queue += elemenet + " ";
        }

        return queue;
    }

    public static void swap(String[] array, int firstIndex, int secondIndex) {
        String temporary = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temporary;
    }
}
