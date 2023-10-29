package lab3;

public class wheeled extends vehicle {
    final static double ASPHALT_SPEED = 1;
    final static double DIRT_SPEED = 0.75;
    final static double STONE_SPEED = 0.75;

    public static int wheeled_count = 0;
    
    // constructor
    public wheeled(float speed, float current_position, int fuel) {
        super(speed, current_position, fuel);

        wheeled.wheeled_count++;
        this.name = "W" + wheeled.wheeled_count;
    }

    // getters
    public double[] get_speed_multipliers() {double[] array = {wheeled.ASPHALT_SPEED, wheeled.DIRT_SPEED, wheeled.STONE_SPEED}; return array;}

    // methods
    public void move(int roadType) {
        if (this.fuel > 0) {
            switch (roadType) {
                case 0:
                    this.current_position += this.speed * wheeled.ASPHALT_SPEED;
                    break;
                case 1:
                    this.current_position += this.speed * wheeled.DIRT_SPEED;
                    break;
                case 2: 
                    this.current_position += this.speed * wheeled.STONE_SPEED;
                    break;
            }

            this.fuel--;
        }
    }
}
