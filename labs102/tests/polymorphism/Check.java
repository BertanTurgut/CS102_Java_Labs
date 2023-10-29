package polymorphism;

public class Check {
    public static void main(String[] args) 
    {
        C2 c2 = new C2();
        System.out.println(c2.x); 
        
        C1 c1 = new C1();
        System.out.println(c1.x);
        
        C1 c3 = new C2();
        System.out.println(c3.x);
    }
}
