package lab3;

public class vehicle {
    // instance data
    protected String name;
    protected float speed;
    protected float current_position;
    protected int fuel;

    // constructor
    public vehicle(float speed, float current_position, int fuel) {
        this.speed = speed;
        this.current_position = current_position;
        this.fuel = fuel;
    }

    // getters
    public String get_name() {return this.name;}
    public float get_speed() {return this.speed;}
    public float get_current_position() {return this.current_position;}
    public int get_fuel() {return this.fuel;}
    public double[] get_speed_multipliers() {return null;}

    // methods
    public void move(int roadType) {
        // this method is completely useless and will be overriden by vehicle subclasses
    }
}
