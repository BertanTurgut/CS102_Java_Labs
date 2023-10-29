package polymorphism;

public class vehicle {
    enum type {CAR, BOAT, PLANE}

    protected String ID;
    protected int price;
    protected type _type;

    public vehicle(String ID, int price, type _type) {
        this.ID = ID;
        this.price = price;
        this._type = _type;
    }

    protected void x() {
        System.out.println("hello world");
    }
}
