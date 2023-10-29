package polymorphism;

public class car extends vehicle implements engine{
    public car(String ID, int price, type _type) {
        super(ID, price, _type);
    }

    @Override
    public void start() {
        System.out.println("Car engine has been started.");
    }
    
    @Override
    public void running() {
        System.out.println("Car engine is running.");
    }

    @Override
    public void stop() {
        System.out.println("Car engine has been stopped.");
    }
}
