package polymorphism;

public class plane extends vehicle implements engine{
    public plane(String ID, int price, type _type) {
        super(ID, price, _type);
    }

    @Override
    public void start() {
        System.out.println("Plane engine has been started.");
    }
    
    @Override
    public void running() {
        System.out.println("Plane engine is running.");
    }

    @Override
    public void stop() {
        System.out.println("Plane engine has been stopped.");
    }
}
