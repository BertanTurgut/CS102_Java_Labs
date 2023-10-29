package lab3;

public class flying extends vehicle {
    final static double ASPHALT_SPEED = 1;
    final static double DIRT_SPEED = 1;
    final static double STONE_SPEED = 1;

    public static int flying_count = 0;

    // constructor
    public flying(float speed, float current_position, int fuel) {
        super(speed, current_position, fuel);

        flying.flying_count++;
        this.name = "F" + flying.flying_count;
    }

    // getters
    public double[] get_speed_multipliers() {double[] array = {flying.ASPHALT_SPEED, flying.DIRT_SPEED, flying.STONE_SPEED}; return array;}

    // methods
    public void move(int roadType) {
        if (this.fuel > 0) {
            switch (roadType) {
                case 0:
                    this.current_position += this.speed * flying.ASPHALT_SPEED;
                    break;
                case 1:
                    this.current_position += this.speed * flying.DIRT_SPEED;
                    break;
                case 2: 
                    this.current_position += this.speed * flying.STONE_SPEED;
                    break;
            }

            this.fuel--;
        }
    }
}
