package queue;

public class _main {
    public static void main(String[] args) {
        Queue_Array qArray = new Queue_Array(5);
        qArray.enqueue("a");
        qArray.enqueue("b");
        qArray.enqueue("c");
        qArray.enqueue("d");
        qArray.enqueue("e");
        qArray.enqueue("w");
        qArray.dequeue();
        qArray.dequeue();
        qArray.enqueue("x");

        System.out.println(qArray.toString());
    }
}
