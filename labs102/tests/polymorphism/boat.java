package polymorphism;

public class boat extends vehicle implements engine{
    public boat(String ID, int price, type _type) {
        super(ID, price, _type);
    }

    @Override
    public void start() {
        System.out.println("Boat engine has been started.");
    }
    
    @Override
    public void running() {
        System.out.println("Boat engine is running.");
    }

    @Override
    public void stop() {
        System.out.println("Boat engine has been stopped.");
    }

    public static void main(String[] args) {
        vehicle x = new car("c1", 100, type.CAR);
        System.out.println(x.getClass());
        // x.stop();
    }
}
