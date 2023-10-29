import java.util.LinkedList;
import java.util.ListIterator;

public class java_tests {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7};
        int a = 5;
        String x = "abc";

        test(arr, a, x);

        System.out.println(arr[0] + " " + a + " " + x);

        System.out.println("\n" + a++);
        System.out.println(a);
        System.out.println(++a);
        int b = 8;
        System.out.println(b == a++);
        System.out.println(b == a);
        System.out.println(b++ == ++a);
        System.out.println(b == a);

        String str = "bertan";
        System.out.println("\n" + str.substring(0, 0));

        LinkedList<String> list = new LinkedList<String>();
        list.add("a");
        ListIterator<String> iterator = list.listIterator();
        //list.add("a");
        if (iterator.hasNext()) {
            System.out.println("Iterator has next");
        }
        iterator.next();
        iterator.previous();
        iterator.set("b");
        if (iterator.hasNext()) {
            System.out.println("Iterator has next");
            iterator.next();
        }
        if (iterator.hasNext()) {
            System.out.println("Iterator has next");
        }

    }

    public static void test(int[] array, int b, String y) {
        array[0] = 100;
        b = 10;
        y = "def";
    }
}
