package exceptions;

public class test {
    public static void main(String[] args) {
        try {
            System.out.println(3/0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("hello world");
        }
        System.out.println("hello world again");
     }
}
